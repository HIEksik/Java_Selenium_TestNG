package tests.day17_testNG_POM;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import paces.TestOtomasyonPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class C02_UrunSepetiTesti {

    @Test
    public void urunSepetiTesti(){

        //1- https://www.testotomasyonu.com/ anasayfasina gidin
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));
        //2- phone icin arama yapin
        TestOtomasyonPage testOtomasyonPage=new TestOtomasyonPage();
        testOtomasyonPage.aramaKutusu.sendKeys("phone"+ Keys.ENTER);

        //3- Listelenen sonuclardan ilkini tiklayin
        testOtomasyonPage.bulunanUrunElementleriList.get(0).click();

        //4- urun ismini kaydedin ve sepete ekleyin
        String urunSaydasindakiUrunismi=testOtomasyonPage.urunSayfasindaUrunIsimElementi.getText();
        Actions actions=new Actions(Driver.getDriver());
        for (int i = 0; i < 10; i++) {
            actions.sendKeys(Keys.ARROW_DOWN).perform();
        }
        ReusableMethods.bekle(1);
        testOtomasyonPage.addToCardButonu.click();
        //5- your cart linkine tiklayin


        for (int i = 0; i < 13; i++) {
            actions.sendKeys(Keys.ARROW_UP).perform();
        }
        ReusableMethods.bekle(5);
        testOtomasyonPage.yourCard.click();
        //6- kaydettiginiz urun ismi ile sepetteki urun isminin ayni oldugunu test edin
        String sepettekiUrunIsmi=testOtomasyonPage.sepettekiUrunIsimElementi.getText();

        Assert.assertEquals(sepettekiUrunIsmi,urunSaydasindakiUrunismi);

        //7- sayfayi kapatin
        Driver.quitDriver();


    }
}
