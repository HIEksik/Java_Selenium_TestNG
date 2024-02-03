package tests.day16_testNG_pom;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import paces.FacebookPage;
import utilities.Driver;
import utilities.ReusableMethods;

public class C03_FacebookNegativeLoginTesti {

    @Test(groups = {"smoke","e2e"})
    public void facebookNegativeTesti(){

        //1 - https://www.facebook.com/ adresine gidin
        //    cookies çıkıyorsa kabuledin
        Driver.getDriver().get("https://www.facebook.com/");

        //2- POM’a uygun olarak email, sifre kutularini ve giris yap butonunu locate edin
        FacebookPage facebookPage=new FacebookPage();

        //3- Faker class’ini kullanarak email ve sifre degerlerini yazdirip, giris butonuna basin
        Faker faker=new Faker();

        facebookPage.emailKutusu.sendKeys(faker.internet().emailAddress());
        facebookPage.passwordKutusu.sendKeys(faker.internet().password());
        facebookPage.loginbutonu.click();

        ReusableMethods.bekle(3);

        //4- Basarili giris yapilamadigini test edin

        Assert.assertTrue(facebookPage.girisYapilamadiYaziElementi.isDisplayed());

        Driver.quitDriver();



    }
}
