package tests.day19_testNG_Reports;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import paces.TestOtomasyonPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

public class C02_Raporlu_AramaSepetiTesti extends TestBaseRapor {
    // 1 extend yapıyoruz
    // 2 extendTest e test methodu içinde deger atıyoruz
    // 3 raporda çıkmasını istedigimiz adımları işliyoruz

    @Test
    public void aramaSepetiTesti(){

        extentTest=extentReports.createTest("Arama Sepeti Testi","Sepete attıgımız urun ismi ile sepetteki ürün ismi ayni olmali");


        //1- https://www.testotomasyonu.com/ anasayfasina gidin
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));
        extentTest.info("Kullanıcı testotomasyonu ana sayfaya gider");

        //2- phone icin arama yapin
        TestOtomasyonPage testOtomasyonPage=new TestOtomasyonPage();
        testOtomasyonPage.aramaKutusu.sendKeys("phone"+ Keys.ENTER);
        extentTest.info("phone için arama yapar");

        //3- Listelenen sonuclardan ilkini tiklayin
        testOtomasyonPage.bulunanUrunElementleriList.get(0).click();
        extentTest.info("Listelenen sonuçlardan ilkini tıklar");

        //4- urun ismini kaydedin ve sepete ekleyin
        String sayfadakiUrunIsmi=testOtomasyonPage.urunSayfasindaUrunIsimElementi.getText();
        extentTest.info("Urun ismini kaydeder ve sepete ekler");

        Actions actions=new Actions(Driver.getDriver());

        for (int i = 0; i < 4; i++) {
            actions.sendKeys(Keys.ARROW_DOWN).perform();
        }

        ReusableMethods.bekle(2);
        testOtomasyonPage.addToCardButonu.click();

        //5- your cart linkine tiklayin




        for (int i = 0; i < 5; i++) {
            actions.sendKeys(Keys.ARROW_UP).perform();
        }

        ReusableMethods.bekle(4);
        testOtomasyonPage.yourCard.click();
        extentTest.info("YourCard linkine tıklar");

        //6- kaydettiginiz urun ismi ile sepetteki urun isminin ayni oldugunu test edin
        String sepettekiUrunIsmi=testOtomasyonPage.sepettekiUrunIsimElementi.getText();

        ReusableMethods.bekle(2);

        Assert.assertEquals(sepettekiUrunIsmi.toLowerCase(),sayfadakiUrunIsmi.toLowerCase());
        extentTest.pass("kaydettiginiz urun ismi ile sepetteki urun isminin ayni oldugunu test eder");

        //7- sayfayi kapatin
        Driver.quitDriver();
        extentTest.info("Sayfayi kapatır");
    }
}
