package paces;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import utilities.Driver;

public class ZeroWebPage {

    public ZeroWebPage(){
        PageFactory.initElements(Driver.getDriver(),this);

    }

    @FindBy(xpath = "//*[@class='signin btn btn-info']") public WebElement singInLinki;
    @FindBy(xpath = "//*[@id='user_login']")public WebElement userNameKutusu;
    @FindBy(xpath = "//*[@id='user_password']")public WebElement passwordKutusu;
    @FindBy(xpath = "//*[@name='submit']")public WebElement sinInButonu;
    @FindBy(xpath = "(//a[@class='dropdown-toggle'])[2]")public WebElement loginDogrulama;
    @FindBy(xpath = "//strong[text()='Online Banking']")public WebElement onlinebankingButonu;
    @FindBy(xpath = "//*[@id='pay_bills_link']")public  WebElement payBillsLinki;
    @FindBy(xpath = "//*[text()='Purchase Foreign Currency']")public WebElement purchaseFroeingCurrencyTab;
    @FindBy(xpath = "//*[@id='pc_currency']")public WebElement pcCurrencyDropdown;



}
