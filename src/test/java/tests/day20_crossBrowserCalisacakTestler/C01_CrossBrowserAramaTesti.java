package tests.day20_crossBrowserCalisacakTestler;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.*;

public class C01_CrossBrowserAramaTesti extends TestBaseRapor {

    protected WebDriver driver;
    @Parameters("crossBrowser")
    @BeforeMethod
    public void setUp(@Optional String crossBrowser){
        driver= DriverCross.getDriver(crossBrowser);


    }
    @AfterMethod
    public void tearDown(){
        DriverCross.closeDriver();
    }

    @Test
    public void aramaTesti(){
        extentTest=extentReports.createTest("Paralel test","QA");


        // testotomasyonu anasayfaya gidin
        driver.get(ConfigReader.getProperty("toUrl"));
        extentTest.info("test otomasyon sayfasına gider");
        // phone için arama yapın
        WebElement aramaKutusu=driver.findElement(By.id("global-search"));
        aramaKutusu.sendKeys("phone"+ Keys.ENTER);
        extentTest.info("phone aratır");


        // arama sonucunda ürün bulunabildigibi test edin
        WebElement aramaSonucElementi=driver.findElement(By.xpath("//*[@*='product-count-text']"));

        String unExpected="0 Products Found";

        String actualSonucyazisi=aramaSonucElementi.getText();

        Assert.assertNotEquals(actualSonucyazisi,unExpected);
        extentTest.pass("Arama sonucunda ürün bulundugunu test eder");

        ReusableMethods.bekle(3);
        extentTest.info("Sayfayı kapatır");







    }
}
