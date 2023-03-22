package no.hvl.dat102;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

public class Oppgave2 {
	public static void main(String[] args) {
		int gjIterasjoner = 5;
		int antallnumre   = 128000;
		// int[] randomseeds   = {12345, 67890, 13579, 24680, 69420};
		int[] randomseeds = new int[gjIterasjoner];

		for (int i = 0; i < gjIterasjoner; i++) {
			Random r = new Random();
			randomseeds[i] = r.nextInt(10000);
		}

		// sorterIntOgInteger(gjIterasjoner, antallnumre, randomseeds, sortmetode.FLETTE);
		// sorterIntOgInteger(gjIterasjoner, antallnumre, randomseeds, sortmetode.UTVALG);
		// sorterIntOgInteger(gjIterasjoner, antallnumre, randomseeds, sortmetode.INNSETTING);
		sorterIntOgInteger(gjIterasjoner, antallnumre, randomseeds, sortmetode.QUICKSORT);

		// oppgave 2 b
		Integer[] sammetall = new Integer[antallnumre];
		Arrays.fill(sammetall, 24);
		long tid = 0;
		for (int i = 0; i < gjIterasjoner; i++) {
			Instant start = Instant.now();
			SorterTabell.quickSort(sammetall, sammetall.length);
			Instant slutt = Instant.now();
			tid += Duration.between(start, slutt)
			               .toMillis();
		}

		System.out.println(
				"Quicksort på tabell med " + antallnumre + " tall tok " + (tid / gjIterasjoner) + "ms");
	}

	private static Integer[] settOppTabell(int antall, int randomseed) {
		Integer[] tabell    = new Integer[antall];
		Random    tilfeldig = new Random(randomseed);
		for (int j = 0; j < antall; j++) {
			tabell[j] = tilfeldig.nextInt();
		}
		return tabell;
	}

	private static int[] settOppTabellInt(int antall, int randomseed) {
		int[]  tabell    = new int[antall];
		Random tilfeldig = new Random(randomseed);
		for (int j = 0; j < antall; j++) {
			tabell[j] = tilfeldig.nextInt();
		}
		return tabell;
	}

	private static void sorterIntOgInteger(int iterasjoner, int antallnumre, int[] randomseeds,
	                                       Oppgave2.sortmetode sm) {
		System.out.println("Sortering med metode: " + sm.toString());
		System.out.println("Sorterer tabell med Integer wrapper klasse:");
		long tidInteger = sorterIntegerOgTaTid(iterasjoner, antallnumre, randomseeds, sm);
		System.out.println("Gjennomsnitt: " + tidInteger + "ms");
		System.out.println("\nSorterer tabell med primitiv int:");
		long tidInt = sorterIntOgTaTid(iterasjoner, antallnumre, randomseeds, sm);
		System.out.println("Gjennomsnitt: " + tidInt + "ms\n");

	}

	private static long sorterIntOgTaTid(int iterasjoner, int antallnumre, int[] randomseeds,
	                                     Oppgave2.sortmetode sm) {
		long totalTid = 0;

		System.out.print("Tider: ");
		for (int i = 0; i < iterasjoner; i++) {
			int[]   tabell = settOppTabellInt(antallnumre, randomseeds[i]);
			Instant start  = Instant.now();
			switch (sm) {
				case INNSETTING -> SorterTabellInt.sorteringVedInnsetting(tabell, tabell.length);
				case UTVALG -> SorterTabellInt.utvalgssortering(tabell, tabell.length);
				case QUICKSORT -> SorterTabellInt.quickSort(tabell, tabell.length);
				case FLETTE -> SorterTabellInt.flettesortering(tabell, tabell.length);
			}
			Instant finish = Instant.now();
			long tid = Duration.between(start, finish)
			                   .toMillis();

			totalTid += tid;
			System.out.print(tid + "ms, ");
		}
		System.out.println();
		return totalTid / iterasjoner;
	}

	private static long sorterIntegerOgTaTid(int iterasjoner, int antallnumre, int[] randomseeds,
	                                         Oppgave2.sortmetode sm) {
		long totalTid = 0;
		System.out.print("Tider: ");
		for (int i = 0; i < iterasjoner; i++) {
			Integer[] tabell = settOppTabell(antallnumre, randomseeds[i]);
			Instant   start  = Instant.now();
			switch (sm) {
				case INNSETTING -> SorterTabell.sorteringVedInnsetting(tabell, tabell.length);
				case UTVALG -> SorterTabell.utvalgssortering(tabell, tabell.length);
				case QUICKSORT -> SorterTabell.quickSort(tabell, tabell.length);
				case FLETTE -> SorterTabell.flettesortering(tabell, tabell.length);
			}
			Instant finish = Instant.now();
			long tid = Duration.between(start, finish)
			                   .toMillis();

			totalTid += tid;
			System.out.print(tid + "ms, ");
		}
		System.out.println();
		return totalTid / iterasjoner;
	}

	private enum sortmetode {
		INNSETTING, UTVALG, QUICKSORT, FLETTE,
	}

}
