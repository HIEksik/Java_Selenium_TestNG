package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class ReusableMethods {

    public static void bekle(int saniye){
        try {
            Thread.sleep(saniye*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    public static List<String> stringListeCevir(List<WebElement> webElementList){

        List<String> stringList=new ArrayList<>();

        for (WebElement eachElement:webElementList
             ) {

            stringList.add(eachElement.getText());
        }

        return stringList;
    }

    public static void titleIleWindowDegistir(String hedefTitle, WebDriver driver){

        Set<String> whdSeti=driver.getWindowHandles();

        for (String eachwhd:whdSeti
        ) {
            driver.switchTo().window(eachwhd); // oldugumuz sayfanın whd si

            String oldugumuzSayfaTitle=driver.getTitle(); // oldugumuz sayfanın whd sini aldık

            if (oldugumuzSayfaTitle.equals(hedefTitle)){  // oldugumuz sayfa whd si ile hedef sayfa whd si aynıysa doğru yerdeyiz
                break;
            }
        }

    }

    public static void tumSayfaScreenShot(WebDriver driver){

        // 1 - bir TakesScreenShot objesi oluştur ve deger olarak driver ı atayın
        TakesScreenshot tss= (TakesScreenshot) driver;

        // 2- ScreenShot ı kaydedecegimiz bir dosya oluşturalım ve dosyanın uzantısını verelim (tss.jpeg)
                // ScreenShot ismini uniq yapabilmek için , timestamp ekleyelim (zaman damgası)
        LocalDateTime ldt=LocalDateTime.now();
        DateTimeFormatter zamanFormati=DateTimeFormatter.ofPattern("YYMMddHHmmss");//yıl/ay/gün/saat/dakika/saniye
        String timeStamp= ldt.format(zamanFormati);

        File tumSayfaScreenShot=new File("target/tumSayfaScreenShot/tumSayfa"+timeStamp+".jpeg");

        // 3- tss objesini kullanarak ScreenShot alın ve bir File olarak kaydedin
        File geciciDosya=tss.getScreenshotAs(OutputType.FILE);

        // 4-  gecici dosyayı deger olarak asıl kaydedilecek File a atayın yani kopyalayın
        try {
            FileUtils.copyFile(geciciDosya,tumSayfaScreenShot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void tumSayfaScreenShot(WebDriver driver , String resimAdi){

        // 1 - bir TakesScreenShot objesi oluştur ve deger olarak driver ı atayın
        TakesScreenshot tss= (TakesScreenshot) driver;

        // 2- ScreenShot ı kaydedecegimiz bir dosya oluşturalım ve dosyanın uzantısını verelim (tss.jpeg)
        File tumSayfaScreenShot=new File("target/tumSayfaScreenShot/"+resimAdi+".jpeg");

        // 3- tss objesini kullanarak ScreenShot alın ve bir File olarak kaydedin
        File geciciDosya=tss.getScreenshotAs(OutputType.FILE);

        // 4-  gecici dosyayı deger olarak asıl kaydedilecek File a atayın yani kopyalayın
        try {
            FileUtils.copyFile(geciciDosya,tumSayfaScreenShot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void webelementScreenshot(WebElement webElement){

        // 1- ScreenShot alacağınız webelementi locate edip kaydedin
        // 2- ScreenShot ı kaydedecegimiz dosyayı olusturun

        // ScreenShot ismini uniq yapabilmek için , timestamp ekleyelim (zaman damgası)
        LocalDateTime ldt=LocalDateTime.now();
        DateTimeFormatter zamanFormati=DateTimeFormatter.ofPattern("YYMMddHHmmss");//yıl/ay/gün/saat/dakika/saniye
        String timeStamp= ldt.format(zamanFormati);


        File webElementScreenShot=new File("target/webelementScreenShots/webElement"+timeStamp+".jpg");
        // 3- Webelementi kullanarak Screenshot alın ve gecici dosyaya kaydedin
        File gecisiScreenShot= webElement.getScreenshotAs(OutputType.FILE);
        // 4- Gecici dosyayı Asıl dosyaya kopyalayalım
        try {
            FileUtils.copyFile(gecisiScreenShot,webElementScreenShot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void webelementScreenshot(WebElement webElement, String screenShotIsim){

        // 1- ScreenShot alacağınız webelementi locate edip kaydedin
        // 2- ScreenShot ı kaydedecegimiz dosyayı olusturun

        // ScreenShot ismini uniq yapabilmek için , timestamp ekleyelim (zaman damgası)
        LocalDateTime ldt=LocalDateTime.now();
        DateTimeFormatter zamanFormati=DateTimeFormatter.ofPattern("YYMMddHHmmss");//yıl/ay/gün/saat/dakika/saniye
        String timeStamp= ldt.format(zamanFormati);


        File webElementScreenShot=new File("target/webelementScreenShots/"+screenShotIsim+timeStamp+".jpg");
        // 3- Webelementi kullanarak Screenshot alın ve gecici dosyaya kaydedin
        File gecisiScreenShot= webElement.getScreenshotAs(OutputType.FILE);
        // 4- Gecici dosyayı Asıl dosyaya kopyalayalım
        try {
            FileUtils.copyFile(gecisiScreenShot,webElementScreenShot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static String getScreenshot(String name) throws IOException {
        // naming the screenshot with the current date to avoid duplication
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        // TakesScreenshot is an interface of selenium that takes the screenshot
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        // full path to the screenshot location
        String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);
        // save the screenshot to the path given
        FileUtils.copyFile(source, finalDestination);
        return target;
    }

}
