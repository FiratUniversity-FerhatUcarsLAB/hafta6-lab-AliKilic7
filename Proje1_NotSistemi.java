/*
 * Ad Soyad: Selahattin Ali KILIÇ
 * Ogrenci No: 250541036
 * Tarih: 20.11.2025
 */

package Hafta6;

import java.util.Scanner;

public class Proje1 {

	public static double ortalama(int vizeNotu,int finalNotu,int odevNotu) {
		double ortalama = (vizeNotu*0.3)+(finalNotu*0.4)+(odevNotu*0.3);
		return ortalama;
	}
	
	public static boolean gectiKaldi(double ortalama) {
		if(ortalama>= 50) {
		return true;
		}else{
		return false;
		}
	}
	
	public static String onurListesi(double ortalama) {
		String harfNotu = "";
		if(ortalama>=90 && ortalama<=100) {
			harfNotu = "A";
			}else if(ortalama>=80 && ortalama<90) {
				harfNotu = "B";
			}else if(ortalama>=70 && ortalama<80) {
				harfNotu = "C";
			}else if(ortalama>=60 && ortalama<70) {
				harfNotu = "D";
		}else {
			harfNotu = "F";
		}
		return harfNotu;
	}
		
	public static boolean onurListesi(double ortalama,int vizeNotu,int finalNotu,int odevNotu) {
		if(ortalama>=85 && vizeNotu>=70 && finalNotu>=70 && odevNotu>=70) {
			return true;
		}else {
			return false;
		}
		
	}
	public static boolean butunlemeHakki(double ortalama) {
		if(ortalama>=40 && ortalama<50) {
			return true;
		}else {
			return false;
		}
	        
	}
	
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int vizeNotu, finalNotu, odevNotu;
		System.out.println("Vize Notunu Girin");
		vizeNotu = input.nextInt();
		System.out.println("Final Notunu Girin");
		finalNotu = input.nextInt();
		System.out.println("Ödev Notunu Girin");
		odevNotu = input.nextInt();
		
		System.out.println("=== OGRENCİ NOT RAPORU ===");
		System.out.println("Vize Notu: "+vizeNotu);
		System.out.println("Final Notu: "+finalNotu);
		System.out.println("Ödev Notu: "+odevNotu);
		System.out.println("--------------------------");
		double ortalama = ortalama(vizeNotu, finalNotu, odevNotu);
		System.out.println("Ortalama: "+ortalama);
		System.out.println("Harf Notu: "+onurListesi(ortalama));
		System.out.println("Durum: "+ (gectiKaldi(ortalama) == true ? "GEÇTİ" : "KALDI"));
		System.out.println("Onur Listesi: "+(onurListesi(ortalama,vizeNotu,finalNotu,odevNotu) == true ? "EVET" : "HAYIR"));
		System.out.println("Bütünleme: "+(butunlemeHakki(ortalama) == true ? "VAR" : "YOK"));
		
		input.close();
	}

}

