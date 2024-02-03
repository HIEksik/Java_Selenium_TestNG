package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {

    /*
         Bu Class ın amacı
         belirlenen Browser a uygun WebDriver objesi oluşturmak
     */

    private Driver(){

        // Baska Class ların Driver Clasından obje oluşturmasını engellemek için
        //Singleton pattern kullanılmıştır
        //Singleton pattern class tan obje oluşturulmasını engellemek için
        //constructor ı görunur yapıp , erişimini Private yapmaya dayanır


    }


    public static WebDriver driver;
    public static WebDriver getDriver(){

        String browserTercihi=ConfigReader.getProperty("browser");
        /*
            Browser ın sadece chrome olmaması için
            configuration.properties ' e browser=firefox  secenegi ekledik


            orada yazan browser tercihini
            String browserTercihi variablesine uygun driver oluşturması için
            bir swithc kullandık


         */

        if (driver==null){

            switch (browserTercihi){

                case "firefox" :
                    WebDriverManager.firefoxdriver().setup();
                    driver=new FirefoxDriver();
                    break;
                case  "edge" :
                    WebDriverManager.edgedriver().setup();
                    driver=new EdgeDriver();
                   break;

                case "safari" :
                    WebDriverManager.safaridriver().setup();
                    driver=new SafariDriver();
                    break;

                default:
                    WebDriverManager.chromedriver().setup();
                    driver=new ChromeDriver();

            }


            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }

        return driver;

    }

    public static void closeDriver(){
        if (driver!=null){
            driver.close();
            driver=null;
        }
    }
    public static void quitDriver(){
        if (driver!=null){
            driver.quit();
            driver=null;
        }
    }

}
