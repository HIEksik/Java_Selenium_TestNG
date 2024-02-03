package tests.day15_testNG_pom;

import org.testng.annotations.Test;

public class C03_PriorityKurallari {

    /*
       1 - eger bizim mudahalemiz oılmaazsa TestNG testleri Harf sırasına göre çalışır
       2 - Eger biz testleri sıralamak istersek
           ( priority = istenen deger ) yazariz
           ve testNG priority degerlerine bakarak küçükten büyüge doğru çalıştırır
       3- şbazı test methodlarına priority ataması yapıp bazılarına yapmazsak ... atama yapılmayanlara
           Deger vermezsek prioritiy = default =0 olarak kabuleder bu degere göre sıralar

       4- Eger aynı priority degerine sahip birden fazla test methodu varsa
          Kendi içlerinde harf sırasına göre çalışırlar

     */

    @Test
    public void testOtomasyonTesti(){
        System.out.println("Test otomasyonu testi Çalıştı");
    }
    @Test
    public void wiseQuarterTesti(){
        System.out.println("WiseQuarter Testi Çalıştı");
    }
    @Test
    public void youTubeTesti(){
        System.out.println("YlouTube Testi Çalıştı");
    }

}
