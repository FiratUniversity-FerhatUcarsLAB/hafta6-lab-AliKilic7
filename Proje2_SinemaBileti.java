

import java.util.Scanner;

public class SinemaBileti {

    // 1. Hafta sonu mu? (gun: 1=Pzt ... 7=Paz)
    public static boolean isWeekend(int gun) {
        return (gun == 6 || gun == 7);
    }

    // 2. Matine mi? (saat 0-23) -> matine: 12:00 öncesi
    public static boolean isMatinee(int saat) {
        return saat < 12;
    }

    // 3. Temel fiyat hesapla (gun, saat)
    public static double calculateBasePrice(int gun, int saat) {
        boolean weekend = isWeekend(gun);
        boolean matinee = isMatinee(saat);

        if (weekend) {
            if (matinee) return 55.0;
            else return 85.0;
        } else {
            if (matinee) return 45.0;
            else return 65.0;
        }
    }

    // 4. İndirim hesapla (yas, meslek, gun)
    // meslek: 1=Ogrenci, 2=Ogretmen, 3=Diğer
    public static double calculateDiscount(int yas, int meslek, int gun) {
        // Öncelikli: yaş bazlı
        if (yas >= 65) return 0.30; // %30 her gün
        if (yas < 12) return 0.25; // %25 her gün

        boolean isStudent = (meslek == 1);
        boolean isTeacher = (meslek == 2);

        // Öğrenci: %20 (Pzt-Per), %15 (Cuma-Pazar)
        if (isStudent) {
            if (gun >= 1 && gun <= 4) return 0.20; // Pzt-Per
            else return 0.15; // Cumartesi-Pazar (Cuma? PDF says %15 (Cuma-Pazar) so include Cuma which is 5)
        }

        // Öğretmen: %35 sadece Çarşamba (3)
        if (isTeacher && gun == 3) return 0.35;

        return 0.0;
    }

    // 5. Film türü ekstra (1=2D,2=3D,3=IMAX,4=4DX)
    public static double getFormatExtra(int filmTuru) {
        switch (filmTuru) {
            case 2: return 25.0; // 3D
            case 3: return 35.0; // IMAX
            case 4: return 50.0; // 4DX
            case 1:
            default: return 0.0; // 2D veya bilinmeyen
        }
    }

    // 6. Toplam fiyat hesapla (tüm parametreleri kullan)
    public static double calculateFinalPrice(int gun, int saat, int yas, int meslek, int filmTuru) {
        double base = calculateBasePrice(gun, saat);
        double extra = getFormatExtra(filmTuru);
        double discountRate = calculateDiscount(yas, meslek, gun);

        double discounted = base * (1.0 - discountRate);
        double total = discounted + extra;

        // Round to 2 decimals
        return Math.round(total * 100.0) / 100.0;
    }

    // 7. Bilet bilgisi oluştur (string çıktısı)
    public static String generateTicketInfo(int gun, int saat, int yas, int meslek, int filmTuru) {
        double base = calculateBasePrice(gun, saat);
        double extra = getFormatExtra(filmTuru);
        double discountRate = calculateDiscount(yas, meslek, gun);
        double discounted = base * (1.0 - discountRate);
        double total = discounted + extra;

        String gunAdi;
        switch (gun) {
            case 1: gunAdi = "Pazartesi"; break;
            case 2: gunAdi = "Sali"; break;
            case 3: gunAdi = "Carsamba"; break;
            case 4: gunAdi = "Persembe"; break;
            case 5: gunAdi = "Cuma"; break;
            case 6: gunAdi = "Cumartesi"; break;
            case 7: gunAdi = "Pazar"; break;
            default: gunAdi = "Bilinmeyen"; break;
        }

        String meslekAdi;
        switch (meslek) {
            case 1: meslekAdi = "Ogrenci"; break;
            case 2: meslekAdi = "Ogretmen"; break;
            default: meslekAdi = "Diger"; break;
        }

        String filmAdi;
        switch (filmTuru) {
            case 1: filmAdi = "2D"; break;
            case 2: filmAdi = "3D"; break;
            case 3: filmAdi = "IMAX"; break;
            case 4: filmAdi = "4DX"; break;
            default: filmAdi = "2D"; break;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== BILET BILGILERI ===\n");
        sb.append("Gun: ").append(gunAdi).append(" (").append(gun).append(")\n");
        sb.append("Saat: ").append(saat).append("\n");
        sb.append("Yas: ").append(yas).append("\n");
        sb.append("Meslek: ").append(meslekAdi).append("\n");
        sb.append("Film Turu: ").append(filmAdi).append("\n");
        sb.append(String.format("Temel Fiyat: %.2f TL%n", base));
        sb.append(String.format("Uygulanan Indirim Orani: %.0f%% %n", discountRate * 100));
        sb.append(String.format("Indirimli Fiyat: %.2f TL%n", discounted));
        sb.append(String.format("Format Ekstra: %.2f TL%n", extra));
        sb.append(String.format("Toplam: %.2f TL%n", Math.round(total * 100.0) / 100.0));

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Gun (1=Pzt ... 7=Paz): ");
        int gun = sc.nextInt();

        System.out.println("Saat (8-23): ");
        int saat = sc.nextInt();

        System.out.println("Yas: ");
        int yas = sc.nextInt();

        System.out.println("Meslek (1=Ogrenci, 2=Ogretmen, 3=Diger): ");
        int meslek = sc.nextInt();

        System.out.println("Film Turu (1=2D,2=3D,3=IMAX,4=4DX): ");
        int filmTuru = sc.nextInt();

        System.out.println();
        System.out.println(generateTicketInfo(gun, saat, yas, meslek, filmTuru));

        sc.close();
    }
}
