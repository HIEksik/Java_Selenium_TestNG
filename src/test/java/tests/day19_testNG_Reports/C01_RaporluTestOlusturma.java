package tests.day19_testNG_Reports;

import com.aventstack.extentreports.ExtentReports;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import paces.TestOtomasyonPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

public class C01_RaporluTestOlusturma extends TestBaseRapor {

    @Test
    public void aramaTesti(){

        /*
        Raporlu olmasını istedigimiz her test methodunda

        1- class ın TestBaseRapor a child yapılması
        2- eksik kalan extendTest objesinin oluşturulması
        3- raporda çıkmasını istedigimiz test adımlarını extendTest objesi ile işlemeliyiz

         */

        extentTest = extentReports.createTest("arama testi","test otomasyonunda phone aranabilmeli");

        // test otomasyonu ana sayfaya gidelim
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));
        extentTest.info("kullanıcı Test otomasyonu anasayfaya gider");

        // test otomasyonu sayfasına gittiginizi test edin
        String expectedUrl=ConfigReader.getProperty("toUrl")+"/";
        String actualUrl=Driver.getDriver().getCurrentUrl();

        //Assert.assertEquals(actualUrl,expectedUrl);
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(actualUrl,expectedUrl,"1.assertion faild"); // 1.assertion yapıldı// faild olursa String Açıklama yazıyoruz
        extentTest.info("Testotomasyonu sayfasına gittiginizi test eder");

        // Phone için arama yapın
        TestOtomasyonPage testOtomasyonPage=new TestOtomasyonPage();
        testOtomasyonPage.aramaKutusu.sendKeys("phone"+ Keys.ENTER);
        extentTest.info("phone için arama yapar");

        // bulunan urun sayısının 3 ten fazla oldugunu test edin
        int expectedMinurunsayisi=3;
        int actualUrunSayisi=testOtomasyonPage.bulunanUrunElementleriList.size();

        // Assert.assertTrue(actualUrunSayisi>expectedMinurunsayisi);
        softAssert.assertTrue(actualUrunSayisi>expectedMinurunsayisi,"2.Assertion Faild");// 2. asertion
        extentTest.info("bulunan urun sayısının 3 ten fazla oldugunu test eder");


        // ilk urune tıklayın
        ReusableMethods.bekle(1);
        testOtomasyonPage.bulunanUrunElementleriList.get(0).click();
        extentTest.info("ilk urune tıklar");

        // acılan urun sayfasında urun isminin case senstive olmasan phone içerdigini test edin

        String expectedUrunIsimIcerik="phone";
        String actualUrunIsimIcerik=testOtomasyonPage.urunSayfasindaUrunIsimElementi.getText().toLowerCase();
        extentTest.info("urun isminin case senstive olmasan phone içerdigini test eder");

        //Assert.assertTrue(actualUrunIsimIcerik.contains(expectedUrunIsimIcerik));
        softAssert.assertTrue(actualUrunIsimIcerik.contains(expectedUrunIsimIcerik),"3.Assertion Faild");//3.assertion

        softAssert.assertAll(); // Bunu yazmazsak aseertler rapor vermez .. Sonuncu assertten sonra yazılır.. enson yazılır
        extentTest.pass("softAssert ile yapılan tüm assertionlar raporlanir");

        // sayfayı kapatın

        Driver.quitDriver();
        extentTest.info("Sayfayi kapatir");
    }

}
