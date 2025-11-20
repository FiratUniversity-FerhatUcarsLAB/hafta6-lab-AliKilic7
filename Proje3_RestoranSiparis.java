/*
 * Ad Soyad: Selahattin Ali KILIÇ
 * Ogrenci No: 250541036
 * Tarih: 20.11.2025
 */

package Hafta6;

import java.util.Scanner;

public class RestoranSiparis {

    // Menu fiyatları sabit
    // Ana yemekler
    public static double getMainDishPrice(int secim) {
        switch (secim) {
            case 1: return 85.0; // Izgara Tavuk
            case 2: return 120.0; // Adana Kebap
            case 3: return 110.0; // Levrek
            case 4: return 65.0; // Manti
            default: return 0.0;
        }
    }

    // Baslangic
    public static double getAppetizerPrice(int secim) {
        switch (secim) {
            case 1: return 25.0; // Corba
            case 2: return 45.0; // Humus
            case 3: return 55.0; // Sigara Boregi
            case 0: return 0.0; // yok
            default: return 0.0;
        }
    }

    // Icecek
    public static double getDrinkPrice(int secim) {
        switch (secim) {
            case 1: return 15.0; // Kola
            case 2: return 12.0; // Ayran
            case 3: return 35.0; // Taze Meyve Suyu
            case 4: return 25.0; // Limonata
            case 0: return 0.0;
            default: return 0.0;
        }
    }

    // Tatli
    public static double getDessertPrice(int secim) {
        switch (secim) {
            case 1: return 65.0; // Kunefe
            case 2: return 55.0; // Baklava
            case 3: return 35.0; // Sutlac
            case 0: return 0.0;
            default: return 0.0;
        }
    }

    // Combo mu? (anaVar, icecekVar, tatliVar) -> boolean
    public static boolean isComboOrder(boolean anaVar, boolean icecekVar, boolean tatliVar) {
        return anaVar && icecekVar && tatliVar;
    }

    // Happy hour (14-17 arası)
    public static boolean isHappyHour(int saat) {
        return saat >= 14 && saat < 17;
    }

    // İndirim hesaplama: (tutar, combo, ogrenci, saat)
    // PDF örneğine uygun sıra: önce combo (%15 bütün tutar), sonra happy hour (içeceklerde %20),
    // sonra öğrenci hafta içi %10 (kullanıcının belirttiği gibi), sonra 200+ kontrol
    public static double calculateDiscount(double araToplam, boolean combo, boolean ogrenci, int saat, boolean isWeekday) {
        double toplam = araToplam;
        double comboDiscount = 0.0;
        double happyDiscount = 0.0;
        double studentDiscount = 0.0;
        double over200Discount = 0.0;

        if (combo) {
            comboDiscount = toplam * 0.15;
            toplam -= comboDiscount;
        }

        // Happy hour indirimi (içeceklerde %20) -> handled outside with drink price in main calculation
        // We'll return only the combined discounts to subtract from araToplam in main logic.
        // But keep structure here for clarity — actual drink discount computed in main.

        // Student: hafta içi %10 (isWeekday true means Pzt-Cum)
        if (ogrenci && isWeekday) {
            studentDiscount = toplam * 0.10;
            toplam -= studentDiscount;
        }

        // Over 200 TL: %10 (PDF: 200 üzeri -> %10)
        if (toplam > 200.0) {
            over200Discount = toplam * 0.10;
            toplam -= over200Discount;
        }

        // Sum of discounts
        return comboDiscount + studentDiscount + over200Discount;
    }

    // Bahşiş önerisi: %10
    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("== MENU ==");
        System.out.println("Ana Yemekler: 1:Izgara Tavuk(85), 2:Adana Kebap(120), 3:Levrek(110), 4:Manti(65), 0:Hayir");
        System.out.print("Ana yemek seciminiz (0-4): ");
        int ana = sc.nextInt();

        System.out.println("Baslangiclar: 1:Corba(25), 2:Humus(45), 3:Sigara Boregi(55), 0:Hayir");
        System.out.print("Baslangic seciminiz (0-3): ");
        int bas = sc.nextInt();

        System.out.println("Icecekler: 1:Kola(15), 2:Ayran(12), 3:Meyve Suyu(35), 4:Limonata(25), 0:Hayir");
        System.out.print("Icecek seciminiz (0-4): ");
        int ice = sc.nextInt();

        System.out.println("Tatlilar: 1:Kunefe(65),2:Baklava(55),3:Sutlac(35),0:Hayir");
        System.out.print("Tatli seciminiz (0-3): ");
        int tat = sc.nextInt();

        System.out.print("Saat (8-23): ");
        int saat = sc.nextInt();

        System.out.print("Gun (1=Pzt ... 7=Paz): ");
        int gun = sc.nextInt();

        System.out.print("Ogrenci misiniz? (Evet=1 / Hayir=0): ");
        int ogr = sc.nextInt();
        boolean ogrenci = (ogr == 1);

        // Parçaları al
        double mainPrice = getMainDishPrice(ana);
        double appetizerPrice = getAppetizerPrice(bas);
        double drinkPrice = getDrinkPrice(ice);
        double dessertPrice = getDessertPrice(tat);

        boolean anaVar = mainPrice > 0.0;
        boolean iceVar = drinkPrice > 0.0;
        boolean tatVar = dessertPrice > 0.0;

        double araToplam = mainPrice + appetizerPrice + drinkPrice + dessertPrice;

        // Combo kontrolü
        boolean combo = isComboOrder(anaVar, iceVar, tatVar);

        // Happy hour -> sadece içeceklerde %20 indirim
        double happyDiscountAmount = 0.0;
        if (isHappyHour(saat) && iceVar) {
            happyDiscountAmount = drinkPrice * 0.20;
        }

        // isWeekday: gun 1-5
        boolean isWeekday = (gun >= 1 && gun <= 5);

        // Diğer indirimleri hesapla (combo, student, over200)
        double otherDiscounts


