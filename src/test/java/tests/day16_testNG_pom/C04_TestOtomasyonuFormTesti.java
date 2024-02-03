package tests.day16_testNG_pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import paces.TestotomasyonFormPage;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.List;

public class C04_TestOtomasyonuFormTesti {
    @Test
    public void formTesti(){
        //1 - https://testotomasyonu.com/form adresine gidin
        Driver.getDriver().get("https://testotomasyonu.com/form");

        //2- Dogum tarihi gun seçeneğinden index kullanarak 5’i secin
        TestotomasyonFormPage testotomasyonFormPage=new TestotomasyonFormPage();

        Select selectGun=new Select(testotomasyonFormPage.gunDropdownMenu);
        selectGun.selectByIndex(5);


        //3- Dogum tarihi ay dropdownmenu den value kullanarak Nisan’i secin
        Select selectAy=new Select(testotomasyonFormPage.ayDropdownMenu);
        selectAy.selectByValue("nisan");

        //4- Dogum tarihi yil dropdownmenu visible text kullanarak 1990’i secin
        Select selectYil=new Select(testotomasyonFormPage.yilDropdownMenu);
        selectYil.selectByVisibleText("1990");

        //5- Secilen değerleri konsolda yazdirin
        System.out.println(selectGun.getFirstSelectedOption().getText());
        System.out.println(selectAy.getFirstSelectedOption().getText());
        System.out.println(selectYil.getFirstSelectedOption().getText());
        //6- Ay dropdown menüdeki tum değerleri(value) yazdırın
        List<WebElement>ayDropDownTumOpsiyonElementleri=selectAy.getOptions();
        System.out.println(ReusableMethods.stringListeCevir(ayDropDownTumOpsiyonElementleri));

        //7- Ay Dropdown menusunun boyutunun 13 olduğunu test edin

        Assert.assertEquals(ayDropDownTumOpsiyonElementleri.size(),13);

        ReusableMethods.bekle(3);
        Driver.quitDriver();
    }
}
