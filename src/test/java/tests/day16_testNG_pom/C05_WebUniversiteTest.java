package tests.day16_testNG_pom;

import com.github.javafaker.Faker;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import paces.WebUniversityPage;
import utilities.Driver;
import utilities.ReusableMethods;

public class C05_WebUniversiteTest {

    @Test(groups = {"smoke","e2e","regression"})
    public void webUniversityTest(){

        //1."http://webdriveruniversity.com/" adresine gidin
        Driver.getDriver().get("https://webdriveruniversity.com/");

        String ilksayfaWHD=Driver.getDriver().getWindowHandle();

        //2."Login Portal" a kadar asagi inin
                // *** portal yazısına kadar aşşagı inmek için jS Executor kullanmak
        WebUniversityPage webUniversityPage=new WebUniversityPage();


        Actions actions=new Actions(Driver.getDriver());

        for (int i = 0; i < 10; i++) {
            actions.sendKeys(Keys.ARROW_DOWN).perform();
        }
        ReusableMethods.bekle(3);

        //3."Login Portal" a tiklayin
        webUniversityPage.loginPortalElementi.click();

        //4.Diger window'a gecin
        ReusableMethods.titleIleWindowDegistir("WebDriver | Login Portal",Driver.getDriver());

        //5."username" ve "password" kutularina deger yazdirin

        Faker faker=new Faker();
        webUniversityPage.userNameKutusu.sendKeys(faker.name().username());
        webUniversityPage.passwordKutusu.sendKeys(faker.internet().password());

        //6."login" butonuna basin
        webUniversityPage.loginButonu.click();
        //7.Popup'ta cikan yazinin "validation failed" oldugunu test edin // çıkan yazı JS aler
        String expectedAlertYazisi="validation failed";
        String actualalertYazisi=Driver.getDriver().switchTo().alert().getText(); // alert e geçip yazıyı aldı

        Assert.assertEquals(actualalertYazisi,expectedAlertYazisi);

        //8.Ok diyerek Popup'i kapatin
        Driver.getDriver().switchTo().alert().accept();  // allertte tamam dedik

        //9.Ilk sayfaya geri donun
        Driver.getDriver().switchTo().window(ilksayfaWHD);

        //10.Ilk sayfaya donuldugunu test edin

        String expectedUrl="https://webdriveruniversity.com/";
        String actualUrl=Driver.getDriver().getCurrentUrl();

        Assert.assertEquals(actualUrl,expectedUrl);

        Driver.quitDriver();
    }
}
