package tests.day20_crossBrowserCalisacakTestler;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.*;

import java.util.List;

public class C02_SepeteUrunEklemeCross extends TestBaseRapor {


    public WebDriver driver;
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
    public void urunSepetitesti(){
        extentTest=extentReports.createTest("Sepete urun ekleme","QA");


        JavascriptExecutor jse;
        jse = (JavascriptExecutor) driver;



        //1- https://www.testotomasyonu.com/ anasayfasina gidin
        driver.get(ConfigReader.getProperty("toUrl"));
        extentTest.info("Testotomasyonu sayfasına gider");
        //2- phone icin arama yapin
        WebElement aramaKutusu =driver.findElement(By.id("global-search"));
        aramaKutusu.sendKeys("phone" + Keys.ENTER);
        extentTest.info("phone için arama yapar ");
        //3- Listelenen sonuclardan ilkini tiklayin
        List<WebElement> bulunanUrunElementleriList =
                driver.findElements(By.xpath("//div[@class='product-box my-2  py-1']"));

        ReusableMethods.bekle(1);
        bulunanUrunElementleriList.get(0).click();
        extentTest.info("ilk ürüne tıklar");
        //4- urun ismini kaydedin ve sepete ekleyin
        WebElement urunIsimElementi =driver.findElement(By.xpath("//div[@class=' heading-sm mb-4']"));

        String sayfadakiUrunIsmi = urunIsimElementi.getText();

        extentTest.info("Ürünün adını kaydeder");



       WebElement addCard=driver.findElement(By.xpath("//button[text()='Add to cart  ']"));
        jse.executeScript("arguments[0].scrollIntoView(true);", addCard);

        ReusableMethods.bekle(3);
        addCard.click();
        extentTest.info("Ürünü sepete ekler");

        ReusableMethods.bekle(3);

        //5- your cart linkine tiklayin
       WebElement yourCad=driver.findElement(By.xpath("//span[text()='Your Cart']"));
        jse.executeScript("arguments[0].scrollIntoView(true);", yourCad);

        ReusableMethods.bekle(3);
        yourCad.click();
        extentTest.info("Sepetteki tıklar");
        //6- kaydettiginiz urun ismi ile sepetteki urun isminin
        //   case sensitive olmadan ayni oldugunu test edin

        WebElement sepettekiUrunIsimElementi =
                driver.findElement(By.xpath("//*[@*='product-title text-center']"));

        String sepettekiUrunIsmi = sepettekiUrunIsimElementi.getText();

        Assert.assertEquals(sepettekiUrunIsmi.toLowerCase(),sayfadakiUrunIsmi.toLowerCase());
        extentTest.pass("Kaydedilen ürün ismi ile sepetteki ürün isminin aynı oldugunu kontrol eder");


    }
}
