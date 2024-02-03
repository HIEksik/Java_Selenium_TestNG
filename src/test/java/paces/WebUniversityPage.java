package paces;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Factory;
import utilities.Driver;

    public class WebUniversityPage {
    public WebUniversityPage(){
      PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//*[text()='Login Portal']") public WebElement loginPortalElementi;

    @FindBy(xpath = "//input[@placeholder='Username']")public WebElement userNameKutusu;

    @FindBy(xpath = "//input[@placeholder='Password']")public WebElement passwordKutusu;

    @FindBy(xpath = "//button[@id='login-button']")public WebElement loginButonu;



}
