package tests.day18_softAssertions_xmlFiles;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import paces.TestOtomasyonPage;
import utilities.ConfigReader;
import utilities.Driver;

public class C01_SoftAssertion {
    /*
    TestNG JUnit teki Asertion Clasının aynısına sahiptir. Ancak

    Bu assertion da , bir test methodunun içinde bir den fazla Assertion oldugunda
    ilk FAİLED olan asetion da kodların çalışması durdugu için ,
    geriye kalan Asertionların sonuçlarını görme şansımız olmaz.

    Eger bir den fazla Asertion olan bir test methodunda
    Tüm asertionları yapıp en sonda varsa FAİLD olan tüm asertionları
    raporlamasını istersek  TestNG deki SoftAssert class ını kullanabiliriz

    Soft Assert 3 Adımda çalıştırılır
    1- Sorf Asset objesi oluşturma
    2- Oluşturdugumuz obje üzerinden Assertionları yapma
    3- AssertAll() ile yapılan tüm assertionları raporlama

    softAssert hatayı assertAll() methodununçalıştıgı satırda raporlar
    birden fazla assertion olan bir methodda faild olan assertion u rahat bulabilmek için
    Assertion kodlarına mesaj da eklemek isabetli bir tercih olacaktır

    soft assert ile hazırlanan bir test methodunun sonunda
    assertAll() demezsek
    Faild olan assertion olsa bile testimiz PASSED olur


     */

    @Test
    public void softAssertAramaTesti(){

        // test otomasyonu ana sayfaya gidelim
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));

        // test otomasyonu sayfasına gittiginizi test edin
        String expectedUrl=ConfigReader.getProperty("toUrl")+"/";
        String actualUrl=Driver.getDriver().getCurrentUrl();

        //Assert.assertEquals(actualUrl,expectedUrl);
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(actualUrl,expectedUrl,"1.assertion faild"); // 1.assertion yapıldı// faild olursa String Açıklama yazıyoruz

        // Phone için arama yapın
        TestOtomasyonPage testOtomasyonPage=new TestOtomasyonPage();
        testOtomasyonPage.aramaKutusu.sendKeys("phone"+ Keys.ENTER);

        // bulunan urun sayısının 3 ten fazla oldugunu test edin
        int expectedMinurunsayisi=3;
        int actualUrunSayisi=testOtomasyonPage.bulunanUrunElementleriList.size();

       // Assert.assertTrue(actualUrunSayisi>expectedMinurunsayisi);
        softAssert.assertTrue(actualUrunSayisi>expectedMinurunsayisi,"2.Assertion Faild");// 2. asertion


        // ilk urune tıklayın
        testOtomasyonPage.bulunanUrunElementleriList.get(0).click();

        // acılan urun sayfasında urun isminin case senstive olmasan phone içerdigini test edin

        String expectedUrunIsimIcerik="phone";
        String actualUrunIsimIcerik=testOtomasyonPage.urunSayfasindaUrunIsimElementi.getText().toLowerCase();

        //Assert.assertTrue(actualUrunIsimIcerik.contains(expectedUrunIsimIcerik));
        softAssert.assertTrue(actualUrunIsimIcerik.contains(expectedUrunIsimIcerik),"3.Assertion Faild");//3.assertion

        softAssert.assertAll(); // Bunu yazmazsak aseertler rapor vermez .. Sonuncu assertten sonra yazılır.. enson yazılır

        // sayfayı kapatın

        Driver.quitDriver();
    }
}
