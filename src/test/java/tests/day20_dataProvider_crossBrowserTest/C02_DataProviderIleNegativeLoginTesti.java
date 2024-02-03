package tests.day20_dataProvider_crossBrowserTest;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import paces.TestOtomasyonPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBaseRapor;

public class C02_DataProviderIleNegativeLoginTesti extends TestBaseRapor {
    @DataProvider
    public static Object[][] emailVePasswordProvider() {

        String[][] emailVeSifreler={{"banu@gmail.com","898989"},{"sedat@yahoo.com","898987"},{"orkong@tmail.com","122334"},
                {"fatih@hotmail.com","454545"},{"wise@gmail.com","12345"},{"arzu@senmail.com","676767"},{"mehmet@mynet.com","878987"},};

        return emailVeSifreler;
    }

    // testotomasyonu anasayfaya gidin
    // account linkine tıklayın
    // liste olarak verilen kullanıcı adı ve passwordler ile
    // giriş yapılamadıgını test edin

    //banu@gmail.com   898989
    //sedat@yahoo.com  898987
    //orkong@tmail.com  122334
    //fatih@hotmail.com  454545
    //arzu@senmail.com  676767
    //mehmet@mynet.com  878987

    @Test(dataProvider = "emailVePasswordProvider")
    public void topluNegativeLoginTesti(String email , String password){

        extentTest=extentReports.createTest("Negatif LoginTesti","QA");

        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));
        extentTest.info("Test otomasyon ana sayfaya gider");

        TestOtomasyonPage testOtomasyonPage=new TestOtomasyonPage();
        testOtomasyonPage.accountLinki.click();
        extentTest.info("Account linkine tıklar");

        testOtomasyonPage.emailKutusu.sendKeys(email);
        extentTest.info("Email kusuna "+email+" yazar");
        testOtomasyonPage.passwordKutusu.sendKeys(password);
        extentTest.info("Password kutusuna "+password+" yazar");


        testOtomasyonPage.loginButonu.click();
        extentTest.info("Login linkine click yapar");

        try {
            Assert.assertTrue(testOtomasyonPage.emailKutusu.isDisplayed());
        } catch (NoSuchElementException e) {  // giriş yapılanı yakalarsaaa

            testOtomasyonPage.logoutButonu.click();
            Assert.assertTrue(false);
        }


        extentTest.pass(email + " email "+password+" password giriş yapılamadığını  test eder");

        Driver.quitDriver();

        extentTest.info("Sayfa Kapatılır");


    }


}
