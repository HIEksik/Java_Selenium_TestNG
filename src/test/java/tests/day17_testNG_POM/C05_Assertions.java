package tests.day17_testNG_POM;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import paces.TestOtomasyonPage;
import utilities.ConfigReader;
import utilities.Driver;

public class C05_Assertions {

    @Test
    public void aramaTesti(){


        // test otomasyonu ana sayfaya gidelim
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));

        // test otomasyonu sayfasına gittiginizi test edin
        String expectedUrl=ConfigReader.getProperty("toUrl")+"/";
        String actualUrl=Driver.getDriver().getCurrentUrl();

        Assert.assertEquals(actualUrl,expectedUrl);

        // Phone için arama yapın
        TestOtomasyonPage testOtomasyonPage=new TestOtomasyonPage();
        testOtomasyonPage.aramaKutusu.sendKeys("phone"+ Keys.ENTER);

        // bulunan urun sayısının 3 ten fazla oldugunu test edin
        int expectedMinurunsayisi=3;
        int actualUrunSayisi=testOtomasyonPage.bulunanUrunElementleriList.size();

        Assert.assertTrue(actualUrunSayisi>expectedMinurunsayisi);

        // ilk urune tıklayın
        testOtomasyonPage.bulunanUrunElementleriList.get(0).click();

        // acılan urun sayfasında urun isminin case senstive olmasan phone içerdigini test edin

        String expectedUrunIsimIcerik="phone";
        String actualUrunIsimIcerik=testOtomasyonPage.urunSayfasindaUrunIsimElementi.getText().toLowerCase();

        Assert.assertTrue(actualUrunIsimIcerik.contains(expectedUrunIsimIcerik));

        // sayfayı kapatın

        Driver.quitDriver();


    }
}
