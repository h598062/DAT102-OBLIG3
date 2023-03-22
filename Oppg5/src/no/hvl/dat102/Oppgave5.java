package no.hvl.dat102;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Oppgave5 {
	private static final Random r = new Random();

	public static void main(String[] args) {

		oppgAtestHoyde();

		int antallNoder = 121;
		int antallTraer = 10;

		List<BS_Tre<Integer>> bsTreListe = lagListeMedTilfeldigeBSTre(antallTraer, antallNoder);

		int minH = 0;
		int maxH = 0;
		int hSum = 0;

		for (BS_Tre<Integer> bsTre : bsTreListe) {
			int n = bsTre.getAntall();
			System.out.println("Antall noder: " + n);
			System.out.println("Minimal teoretisk høyde: " + regnUtMinimalTeoretiskHoyde(n));
			System.out.println("Maksimal teoretisk høyde: " + regnUtMaksimalTeoretiskHoyde(n));
			int h = bsTre.finnHoyde();
			System.out.println("Høyde: " + h);
			minH = Math.min(minH, h);
			maxH = Math.max(maxH, h);
			hSum += h;
			System.out.println();
		}
		System.out.println("Minste høyde funnet: " + minH);
		System.out.println("Største høyde funnet: " + maxH);
		System.out.println("Gjennomsnittlig høyde for trærne: " + (hSum / antallTraer));
	}

	private static int regnUtMaksimalTeoretiskHoyde(int antallNoder) {
		// hvis alle noder kommer inn i eksklusivt stigende eller synkende rekkefølge så vil antallNoder være maks, alle er i en lang lineær kjede
		return antallNoder;
	}

	private static int regnUtMinimalTeoretiskHoyde(int antallNoder) {
		// hentet fra presentasjon at høyde = log2 n
		// loga b = ln b / ln a
		// => log2 N = ln N / ln 2

		return (int) (Math.log(antallNoder) / Math.log(2));
	}

	private static List<BS_Tre<Integer>> lagListeMedTilfeldigeBSTre(int antallTraer, int antallNoder) {
		List<BS_Tre<Integer>> bsTreListe = new ArrayList<>(antallNoder);
		for (int i = 0; i < antallTraer; i++) {
			BS_Tre<Integer> bsTre = lagTilfeldigBSTre(antallNoder);
			bsTreListe.add(bsTre);
		}
		return bsTreListe;
	}

	private static BS_Tre<Integer> lagTilfeldigBSTre(int antallNoder) {
		BS_Tre<Integer> bsTre = new BS_Tre<>();
		// System.out.println("nytt tre:");
		for (int i = 0; i < antallNoder; i++) {
			int x = r.nextInt(antallNoder * 10);
			bsTre.leggTil(x);
			// System.out.print(x + ", ");
		}
		// System.out.println();
		return bsTre;
	}

	private static void oppgAtestHoyde() {
		BS_Tre<Integer> bsTre       = new BS_Tre<>();
		int[]           testTreTall = {10, 12, 11, 9, 15, 7, 13, 20, 4, 8, 16, 3, 2, 1}; // skal være 6 høyt ved venstre sti
		System.out.println("Oppgave a:");
		System.out.print("Noder i test tre: ");
		for (int x : testTreTall) {
			bsTre.leggTil(x);
			System.out.print(x + ", ");
		}
		System.out.println("\nHøyde: " + bsTre.finnHoyde()); // Høyde: 6
		System.out.println();
	}
}
