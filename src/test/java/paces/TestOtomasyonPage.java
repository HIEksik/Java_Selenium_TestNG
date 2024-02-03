package paces;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class TestOtomasyonPage {

    /*
    Page classları locate yapmak
    ve varsa Login işlemi gibi çalıştığımız sayfaya özel fonksiyonlara ait basit methodlar
    oluşturmak için kullanılır

    Page claslarının en büyük yeniligi
    driver.findElement() veya driver.findElements() methodları yerine
    @FindBy notasyonu kullanmasıdır
     */
        public TestOtomasyonPage(){
                 PageFactory.initElements(Driver.getDriver(),this); // bu classta bu driver ı kullanacağız dedik
            }
    @FindBy(id = "global-search") public WebElement aramaKutusu;

    @FindBy(xpath = "(//*[@class='e-cart'])[1]") public WebElement accountLinki;

    @FindBy(xpath = "//input[@id='email']") public WebElement emailKutusu;

    @FindBy(xpath = "//input[@id='password']")public WebElement passwordKutusu;

    @FindBy(xpath = "//*[@id='submitlogin']")public WebElement loginButonu;

    @FindBy(xpath = "//span[text()='Logout']")public WebElement logoutButonu;

    @FindBy(xpath = "//*[@*='product-box my-2  py-1']")public List<WebElement> bulunanUrunElementleriList;

    @FindBy(xpath = "//div[@class=' heading-sm mb-4']")public WebElement urunSayfasindaUrunIsimElementi;

    @FindBy(xpath = "//button[@class='add-to-cart']")public WebElement addToCardButonu;

    @FindBy(xpath = "(//a[@class='e-cart'])[2]")public WebElement yourCard;

    @FindBy(xpath = "//*[@Class='product-title text-center']")public WebElement sepettekiUrunIsimElementi;

    @FindBy(xpath = "//*[@*='product-count-text']")public WebElement bulunanUrunSayisiElementi;
}
