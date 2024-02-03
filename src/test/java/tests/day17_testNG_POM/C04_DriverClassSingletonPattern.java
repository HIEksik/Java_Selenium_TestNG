package tests.day17_testNG_POM;

import org.testng.annotations.Test;

public class C04_DriverClassSingletonPattern {

    @Test
    public void test01(){

        /*
            Testlerimizde kullanacağımız WebDrivere objesini
            utilities/ Driver Class ındaki getDriver() ile  oluşturuyoruz.

            Driver clasındaki WebDriver objesi olarak oluştutulan
           driver a Driver Class ından obje oluşturarak da erişebiliriz
           Ancak mahşerin 4 atlısı çalışmadığından driver objesi bir işe yaramaz.


           POM dizaynında Driver class ındaki driver objesine
           obje üzerinden  erişimi iptal etmek için singalten pattern tercih edilmiş


         */
    }
}
