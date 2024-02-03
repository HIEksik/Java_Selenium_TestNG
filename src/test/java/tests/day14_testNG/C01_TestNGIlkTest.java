package tests.day14_testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;
import utilities.TestBaseCross;

public class C01_TestNGIlkTest extends TestBase {

    @Test
    public void aramaTesti(){

        // Testotomasyonu anasayafaya gidin
        driver.get("https://www.testotomasyonu.com");

        // phone için arama yapın
        WebElement aramaKutusu=driver.findElement(By.id("global-search"));
        aramaKutusu.sendKeys("phone"+ Keys.ENTER);

        // arama sonucunda urun bulunabilgidini test edin
        WebElement aramaSonucu= driver.findElement(By.xpath("//*[@*='product-count-text']"));

        String unexpectedAramaSonucyazisi="0 Products Found";
        String actualSonucAramaYazisi=aramaSonucu.getText();

        Assert.assertNotEquals(actualSonucAramaYazisi,unexpectedAramaSonucyazisi);


    }
}
