package tests.day20_dataProvider_crossBrowserTest;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import paces.TestOtomasyonPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

public class C01_DataProvider extends TestBaseRapor {
    @DataProvider
    public static Object[][] aracanacakUrunlerProvider() {
        /*
              @DataProvider notasyonunu kullanan methodlar bize 2 katlı bir array döndürmek üzere hazırlanmıştır

              Bu methodun içerisinde non-primitiv data türüne sahip 2  katlı bir array oluşturup
              Bu array i return olarak gönderirsek
              arraydeki her bir inner array i sırası ile test methodumuza parametre olarak gönderir

         */
        String[][] aranacakUrunlerArrayi={{"phone"},{"java"},{"dress"},{"apple"},{"netulla"},{"cokokrem"},{"baby"}};


        return aranacakUrunlerArrayi;
    }

    // test otomasyonu anasayfaya gidin
    // verilen urun listesindeki her bir urun için
    // arama yapıp , arama sonucunda urun buluna bildigini Test edin
    // aranacak urun listesi : phone, java, dress, apple, nutella, cokokrem, baby

    @Test(dataProvider = "aracanacakUrunlerProvider")
    public void topluAramaTesti(String aranacakUrun){
        extentTest=extentReports.createTest("Toplu Arama Testi","Verilen Listedeki Tüm Ürünlerin Stokta Bulundugunu Test Eder");

        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));
        extentTest.info("Kullanıcı testotomasyonu ana sayfaya gidir");

        TestOtomasyonPage testOtomasyonPage=new TestOtomasyonPage();
        testOtomasyonPage.aramaKutusu.sendKeys(aranacakUrun);
        extentTest.info("Arama kutusuna "+aranacakUrun+" yazar");
        ReusableMethods.bekle(2);
        testOtomasyonPage.aramaKutusu.submit();
        extentTest.info("Arama kutusuna yazılan "+aranacakUrun+" için arama yapar");

        String unExpectedSonucYazisi="0 Products Found";

        Assert.assertFalse(testOtomasyonPage.bulunanUrunSayisiElementi.getText().equals(unExpectedSonucYazisi));
        extentTest.pass(aranacakUrun+" aramasında urun bulunabildigini test eder");

        Driver.quitDriver();
        extentTest.info("Sayfayı kapatır");

    }
}
