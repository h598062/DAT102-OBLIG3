package no.hvl.dat102.haug.klient;

import no.hvl.dat102.haug.tabell.*;

public class KlientHaug {
	public static void main(String[] a) {
		// Tester haugen ved å sortere verdier i en tabell
		int[] tab = {300, 10, 30, 2, 100, 33, 1, 200, 18, 54};

		TabellHaug<Integer> h1 = new TabellHaug<>();
		for (int i = 0; i < tab.length; i++) {

			h1.leggTilElement((tab[i]));
		}


		System.out.println("Verdiene i tabellen er nå:");
		h1.skrivTab();
		// Tar ut av haugen og skriver i sortert rekkefølge
		System.out.println("\nHaugen i sortert rekkefoelge:");
		while (!h1.erTom()) {
			Integer h = h1.fjernMinste();
			System.out.print(h + "  ");
		}
		System.out.println();
	}
}
