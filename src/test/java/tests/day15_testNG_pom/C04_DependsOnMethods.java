package tests.day15_testNG_pom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Driver;

import java.util.List;

public class C04_DependsOnMethods {


    // https://www.testotomasyonu.com/ adresine gidin.
    // Olusturacaginiz 3 farkli test method’unda asagida verilen görevleri yapin.
    //	1. Test : Testotomasyonu ana sayfaya gittiginizi test edin
    //	2. Test : search Box’i kullanarak “phone” icin arama yapin \
    //            ve arama sonucunda urun bulunabildigini test edin
    //  3.Test : ilk urunu tiklayin ve urun isminin case sensitive olmaksizin phone icerdigini test edin

    /*
            Priority test methodlarının çalışma sırasını belirler
            ancak bir önceki method çalışmadıysa veya faild olduysa
            sonraki test methodunun boşuna çalışmasına engel olmaz
            Çalıştırmaz faild yapar.


            1- dependsonMethod kullandıgımızda
            bağlı olan method (b), bağlı oldugu method u (a ) kontrol eder
            Eger a methodu çalışmadıysa veya faild oldu ise b yi hiç çalıştırmaz ignore eder

            2- Test methodları bagımsız olarak çalıştırılabilir
            ANCAK eger bir method dependsOnMethods() ile başka bir methoda
            bağlanmış ise direkt bağlı olan methodu b yi çalıştırmak isteseniz de
            kendisi direkt çalışmaz önce bağlı oldugu method(a ) methodunu çalıştırır o çalışıp PAssed olursa
            kendisi(b) methodu da çalışır

            Ama bu kural sadece 2 method için geçerlidir .. 3 veya daha fazla methodun bağlı olması durumunda
            hata verir Test methodlarını çalıştırmaz

            3- İsim sırası veya Praority sebebi ile önce bağlı olan method çalıştırılmak istenirse
            bağlı olan method önceligi bağlı oldugu methoda verir
            önce bağlı olunan method çalışır
            eger test passed olursa bağlı olan methodda çalışır


     */
    List<WebElement> bulunanUrunElementleriList;

    @Test
    public void a(){
        //	1. Test : Testotomasyonu ana sayfaya gittiginizi test edin

        Driver.getDriver().get("https://www.testotomasyonu.com");

        String expectedUrl = "https://www.testotomasyonu.com/";
        String actualUrl = Driver.getDriver().getCurrentUrl();

        Assert.assertEquals(actualUrl,expectedUrl);
    }  // Test otomazyonu methodu

    @Test(dependsOnMethods = "a")
    public void b(){
        //	2. Test : search Box’i kullanarak “phone” icin arama yapin \
        //            ve arama sonucunda urun bulunabildigini test edin

        WebElement aramaKutusu = Driver.getDriver().findElement(By.id("global-search"));

        aramaKutusu.sendKeys("phone" + Keys.ENTER);

        bulunanUrunElementleriList = Driver.getDriver().findElements(By.xpath("//*[@*='product-box my-2  py-1']"));

        Assert.assertTrue(bulunanUrunElementleriList.size()>0);

    } // arama testi

    @Test(dependsOnMethods = "b")
    public void c(){
        //  3.Test : ilk urunu tiklayin
        //  ve urun isminin case sensitive olmaksizin phone icerdigini test edin

        bulunanUrunElementleriList.get(0).click();

        WebElement urunIsimElementi = Driver.getDriver().findElement(By.xpath(" //div[@class=' heading-sm mb-4']"));

        String expectedUrunIsimIcerigi = "phone";
        String actualUrunIsmiKucukHarf= urunIsimElementi.getText().toLowerCase();

        Assert.assertTrue(actualUrunIsmiKucukHarf.contains(expectedUrunIsimIcerigi));

        Driver.quitDriver();

    }  // ilk urun isim testi



}
