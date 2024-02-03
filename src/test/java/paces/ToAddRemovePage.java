package paces;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class ToAddRemovePage {

    public ToAddRemovePage(){
        PageFactory.initElements(Driver.getDriver(),this); // bu classta bu driver ı kullanacağız dedik
    }

    @FindBy(xpath = "//*[text()='Add/Remove Elements']")public WebElement addRemoveYaziElementi;

    @FindBy(xpath = "//a[text()='Electronics Products']")public WebElement electronixLinki;

    @FindBy(xpath = "//li[@class='current']")public WebElement elecTronicsSayfasiDogrulama;

    @FindBy(xpath = "//*[@class='product-box mb-2 pb-1']")public List<WebElement> bulunanUrunElementleriList;





}
