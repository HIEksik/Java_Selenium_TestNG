package tests.day16_testNG_pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import paces.TestOtomasyonPage;
import utilities.Driver;
import utilities.ReusableMethods;

public class C01_PageClassilePozitifLoginTesti {

    @Test(groups = "smoke")
    public void pozitifLoginTesti(){
        // PositiveTest
        //1- https://www.testotomasyonu.com/ anasayfasina gidin
        Driver.getDriver().get("https://www.testotomasyonu.com");

        //2- account linkine basin

                     // ***** POM de page classlarda locete ettigimiz webelementlere ulaşmak için
                     // ***** Page classlarından obje oluştututuz

        TestOtomasyonPage testOtomasyonPage=new TestOtomasyonPage();
        testOtomasyonPage.accountLinki.click();

        //3- Kullanici email'i olarak “wise@gmail.com”girin
        testOtomasyonPage.emailKutusu.sendKeys("wise@gmail.com");

        //4- Kullanici sifresi olarak “12345” girin
        testOtomasyonPage.passwordKutusu.sendKeys("12345");

        //5- Login butonuna basarak login olun
        testOtomasyonPage.loginButonu.click();

        //6- Basarili olarak giris yapilabildigini test edin
        Assert.assertTrue(testOtomasyonPage.logoutButonu.isDisplayed());

        ReusableMethods.bekle(2);
        Driver.quitDriver();
    }
}
