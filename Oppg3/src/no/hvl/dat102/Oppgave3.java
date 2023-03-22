package no.hvl.dat102;

import java.util.Arrays;
import java.util.Random;

public class Oppgave3 {


	public static void main(String[] args) {


		Random r = new Random();

		int antallTall = 10;

		int[] uordnet = new int[antallTall];

		int[] ordnet = new int[antallTall];
		int   siste  = 0;
		for (int i = 0; i < antallTall; i++) {
			int x = r.nextInt(1000);
			uordnet[i] = x;
			ordnet[i]  = x;
			             siste = x;
		}
		Arrays.sort(ordnet);
		System.out.println(Arrays.toString(ordnet));
		System.out.println(siste);
		System.out.println(binearSoek(ordnet, siste));
	}

	public static boolean linearSoek(int[] a, int x) {
		if (a == null) {
			throw new NullPointerException("Søketabell kan ikke være null");
		}
		for (int i : a) {
			if (i == x) {
				return true;
			}
		}
		return false;
	}

	public static boolean binearSoek(int[] a, int x) {
		if (a == null) {
			throw new NullPointerException("Søketabell kan ikke være null");
		}
		return binearSoek(a, x, 0, a.length - 1);
	}

	private static boolean binearSoek(int[] a, int x, int startpos, int sluttpos) {
		int     midtpunkt = (sluttpos + startpos) / 2;
		boolean svar      = false;
		if (sluttpos < startpos) {
			return false;
		}
		if (x == a[midtpunkt]) {
			svar = true;
		} else if (x < a[midtpunkt]) {
			svar = binearSoek(a, x, startpos, midtpunkt - 1);
		} else {
			svar = binearSoek(a, x, midtpunkt + 1, sluttpos);
		}

		return svar;
	}
}
