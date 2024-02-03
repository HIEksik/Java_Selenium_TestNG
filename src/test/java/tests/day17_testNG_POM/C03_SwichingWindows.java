package tests.day17_testNG_POM;

import org.testng.Assert;
import org.testng.annotations.Test;
import paces.TestOtomasyonPage;
import paces.ToAddRemovePage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class C03_SwichingWindows {

    @Test
    public void windowTesti(){

        //● https://testotomasyonu.com/addremove/ adresine gidin.
        Driver.getDriver().get(ConfigReader.getProperty("toAddUrl"));

        //● Sayfadaki textin “Add/Remove Elements” olduğunu doğrulayın.
        String expectedYazi="Add/Remove Elements";

        ToAddRemovePage toAddRemovePage=new ToAddRemovePage();

        String actualYazi=toAddRemovePage.addRemoveYaziElementi.getText();

        Assert.assertEquals(actualYazi,expectedYazi);

        //● Sayfa başlığının(title) “Test Otomasyonu” olduğunu doğrulayın.
        String expectedTitle="Test Otomasyonu";
        String actualTitle=Driver.getDriver().getTitle();

        Assert.assertEquals(actualTitle,expectedTitle);

        String addRemoveSayfasiWHD=Driver.getDriver().getWindowHandle();

        //● 'Please click for Electronics Products' linkine tiklayin.

        ReusableMethods.bekle(2);
        toAddRemovePage.electronixLinki.click();

        //● Electronics sayfasinin acildigini test edin
                // ** Electronik sayfası ayrı bir TAB olarak açılıyor
                 //*** önce Webdriver objemizi o window a  geçirmeliyiz
        ReusableMethods.titleIleWindowDegistir("Test Otomasyonu - Electronics",Driver.getDriver());

        ReusableMethods.bekle(1);
        Assert.assertTrue(toAddRemovePage.elecTronicsSayfasiDogrulama.isDisplayed());


        //● Bulunan urun sayisinin 16 olduğunu test edin

        int expectedUrunSayisi=16;
        int actualUrunsayisi=toAddRemovePage.bulunanUrunElementleriList.size();

        Assert.assertEquals(actualUrunsayisi,expectedUrunSayisi);

        //● Ilk actiginiz addremove sayfasina donun
        Driver.getDriver().switchTo().window(addRemoveSayfasiWHD);

        //● Url’in addremove icerdigini test edin

        String expectedUrlIcerik="addremove";
        String actualUrl=Driver.getDriver().getCurrentUrl();

        Assert.assertTrue(actualUrl.contains(expectedUrlIcerik));

        Driver.quitDriver();

    }
}
