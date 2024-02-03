package tests.day18_softAssertions_xmlFiles;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import paces.ZeroWebPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.Date;
import java.util.List;

public class C02_WebAppTesti {
    @Test
    public void test01(){

        //1. “http://zero.webappsecurity.com/” Adresine gidin
        Driver.getDriver().get(ConfigReader.getProperty("zeroUrl"));

        //2. webbappsecurity ana sayafaya gittiginizi dogrulayin

        String expectedUrl=ConfigReader.getProperty("zeroUrl");
        String actualUrl=Driver.getDriver().getCurrentUrl();
                //** Dodrulayın dedigi için Soft assert kullanılacak
        SoftAssert softAssert=new SoftAssert();

        softAssert.assertEquals(actualUrl,expectedUrl,"Url Testi FAILD");

        //3. Sign in butonuna basin
        ZeroWebPage zeroWebPage=new ZeroWebPage();
        zeroWebPage.singInLinki.click();

        //4. Login kutusuna “username” yazin
        zeroWebPage.userNameKutusu.sendKeys(ConfigReader.getProperty("zeroGecerliUsername"));

        //5. Password kutusuna “password” yazin
        zeroWebPage.passwordKutusu.sendKeys(ConfigReader.getProperty("zeroGecerliPassword"));

        //6. Sign in tusuna basin
        zeroWebPage.sinInButonu.click();

        //7. Back tusuna basin
        Driver.getDriver().navigate().back();

        //8. Giris yapilabildigini dogrulayin
        softAssert.assertTrue(zeroWebPage.loginDogrulama.isDisplayed(),"Giris yapildi testi FAILD");

        //9. Online banking menusunu tiklayin
        zeroWebPage.onlinebankingButonu.click();

        //10. Pay Bills sayfasina gidin
        zeroWebPage.payBillsLinki.click();

        //11. “Purchase Foreign Currency” tusuna basin
        zeroWebPage.purchaseFroeingCurrencyTab.click();

        //12. Currency dropdown menusunun erisilebilir oldugunu dogrulayin
        softAssert.assertTrue(zeroWebPage.pcCurrencyDropdown.isEnabled(),"DropDown Menusu FAILED");

        //13. “Currency” dropdown menusunden Eurozone’u secin
        Select select=new Select(zeroWebPage.pcCurrencyDropdown);
        select.selectByValue("EUR");

        //14. "Eurozone (euro)" secildigini dogrulayin
        String expectedSecilenOptions="Eurozone (euro)";
        String actualSecilenOptions=select.getFirstSelectedOption().getText();

        softAssert.assertEquals(actualSecilenOptions,expectedSecilenOptions,"Eurozone secim testi FAILED");

        //15. Dropdown menude 16 option bulundugunu dogrulayin.
        int expectedOptionSayisi=16;
        int actualOptionSayisi=select.getOptions().size();

        softAssert.assertEquals(actualOptionSayisi,expectedOptionSayisi,"dropdwn option sayi Testi FAILED");

        //16. Dropdown menude "Canada (dollar)" bulunduğunu dogrulayin
        List<WebElement> dropdownOptionElementleriList=select.getOptions();
        List<String> dropdownoptionListesi= ReusableMethods.stringListeCevir(dropdownOptionElementleriList);

        String expectedOptionIcerik="Canada (dollar)";
        softAssert.assertTrue(dropdownoptionListesi.contains(expectedOptionIcerik),"CAnada testi FAIlED");

        softAssert.assertAll();
        //17. Sayfayi kapatin
        Driver.quitDriver();

    }
}
