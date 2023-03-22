package no.hvl.dat102;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class Oppgave1 {
	public static void main(String[] args) {
		int   gjIterasjoner = 5;
		int   antallnumre   = 50000;
		//int[] randomseeds   = {12345, 67890, 13579, 24680, 69420};
		int[] randomseeds = new int[gjIterasjoner];

		for (int i = 0; i < gjIterasjoner; i++) {
			Random r = new Random();
			randomseeds[i] = r.nextInt(10000);
		}

		long gjennomsnitt1 = sorterOgTaTid(gjIterasjoner, antallnumre, randomseeds, sortmetode.STANDARD);
		System.out.println("Gjennomsnitt for standard metode: " + gjennomsnitt1 + "ms");
		System.out.println();

		long gjennomsnitt2 = sorterOgTaTid(gjIterasjoner, antallnumre, randomseeds, sortmetode.SNARVEI);
		System.out.println("Gjennomsnitt for snarvei metode: " + gjennomsnitt2 + "ms");
		System.out.println();

		long gjennomsnitt3 = sorterOgTaTid(gjIterasjoner, antallnumre, randomseeds, sortmetode.DOBBEL);
		System.out.println("Gjennomsnitt for dobbel metode: " + gjennomsnitt3 + "ms");

		// vi har observert at metoden med å finne minst og sette den på posisjon 0 er raskere enn den vanlige metoden

		// metoden med å flytte dobbel er noen ganger raskest, noen ganger nest raskest, og noen ganger treigest.
		// ved noen tilfeller er den MYE raskere, opptil rundt 30% kortere tid enn snarvei metode, men andre ganger er den 30% treigere.
	}

	/**
	 * Hjeplemetode for snarvei sortering fra oppgave a.<br>
	 * Denne vil starte ved n-te element og jobbe seg framover i tabellen,
	 * og bytte posisjon på minste element og første element
	 *
	 * @param a   Tabellen som skal jobbes med
	 * @param n   Hvor mange elementer som skal tas med ved sortering i tabellen
	 * @param <T> Hvilken som helst type element som utvider Comparable klassen
	 */
	private static <T extends Comparable<? super T>> void flyttMinsteFoerst(T[] a, int n) {
		int pos   = n - 1;
		T   minst = a[n - 1];
		for (int i = n - 1; i > 0; i--) {
			T elem = a[i];
			if (elem.compareTo(a[i - 1]) < 0) {
				minst = elem;
				pos   = i;
			}
		}
		a[pos] = a[0];
		a[0]   = minst;
	}

	/**
	 * Flytter minste element i tabellen til indeks 0, deretter gjør en sortering<br>
	 * Oppgave a
	 *
	 * @param a   Tabellen som skal jobbes med
	 * @param n   Hvor mange elementer som skal tas med ved sortering i tabellen
	 * @param <T> Hvilken som helst type element som utvider Comparable klassen
	 */
	public static <T extends Comparable<? super T>> void sorteringMedSnarvei(T[] a, int n) {
		flyttMinsteFoerst(a, n);
		sorteringVedInssetting(a, 0, n - 1);
	}

	/**
	 * Omskrevet versjon av sortering med innsetting som flytter 2 elementer om gangen.<br>
	 * Oppgave b
	 *
	 * @param a Tabellen som skal jobbes med
	 * @param n Hvor mange elementer som skal tas med ved sortering i tabellen
	 */
	private static <T extends Comparable<? super T>> void sorteringMedDobbel(T[] a, int n) {
		// flyttMinsteFoerst(a, n);
		sorteringMedDobbel(a, 0, n - 1);
	}

	/**
	 * Omskrevet versjon av sortering med innsetting som flytter 2 elementer om gangen.<br>
	 * Oppgave b
	 *
	 * @param a     Tabellen som skal jobbes med
	 * @param start Hvor sorteringen starter
	 * @param slutt Hvor sorteringen slutter
	 * @param <T>   Hvilken som helst type element som utvider Comparable klassen
	 */
	public static <T extends Comparable<? super T>> void sorteringMedDobbel(T[] a, int start, int slutt) {
		/*
		Modifiser koden slik at i stedet for å sette inn ett element om gangen, setter vi inn to.
		Så lenge det største elementet er mindre enn elementet vi sammenligner med i sortert del, så
		kan vi flytte elementet to plasser til høyre.
		Når vi finner rett plass for største, forsetter vi som vanlig med å sette inn det minste.
		Kombiner med å flytte minste elementet først (som i a) før sorteringen starter.
		Pass på at metoden fungerer for både odde og jevne n.
		Skriv kort hva dere observerer.
		*/

		boolean huskSiste = false;

		if (slutt % 2 == 1) {
			huskSiste = true;
		}

		for (int i = start + 2; i <= slutt; i += 2) {
			T   tmin = a[i];
			T   tmax = a[i - 1];
			int j    = i - 2;  // siste i sortert del

			boolean funnetMinPos = false;
			boolean funnetMaxPos = false;

			// sørge for at tmin holder den minste verdien
			if (tmin.compareTo(tmax) > 0) {
				T tmp = tmin;
				tmin = tmax;
				tmax = tmp;
			}
			int pos = j;
			while (!funnetMinPos && j >= 0) {
				if (!funnetMaxPos && tmax.compareTo(a[j]) < 0) {
					a[j + 2] = a[j];
					j--;
					pos = j;
				} else if (tmin.compareTo(a[j]) < 0) {
					funnetMaxPos = true;
					a[j + 1]     = a[j];
					j--;
				} else {
					funnetMinPos = true;
				}
			}
			a[j + 1] = tmin;
			if (funnetMaxPos) {
				a[pos + 2] = tmax;
			} else {
				a[j + 2] = tmax;
			}

			/* håndtering for hvis tabell er partall lang
			når tabellen er partall lang vil vi hoppe over siste tall
			f.eks:
			lengde = 10
			siste for løkke loop:
			i == 9
			i+=2
			i==11
			11 > 10, blir ikkje kjørt noe mer kode i for løkke
			posisjon 10 blir aldri gjort noe med
			 */
			if (huskSiste && i == slutt - 1) {
				int x = i +1;
				T tmp = a[x];
				j = x - 1;  // siste i sortert del

				boolean ferdig = false;
				while (!ferdig && j >= 0) {
					if (tmp.compareTo(a[j]) < 0) {
						a[j + 1] = a[j];
						j--;
					} else {
						ferdig = true;
					}
				}
				a[j + 1] = tmp;
			}
		}

	}

	/**
	 * Sortering ved innsetting (Insertion sort)
	 *
	 * @param a   Tabellen som skal jobbes med
	 * @param n   Hvor mange elementer som skal tas med ved sortering i tabellen
	 * @param <T> Hvilken som helst type element som utvider Comparable klassen
	 */
	public static <T extends Comparable<? super T>> void sorteringVedInssetting(T[] a, int n) {
		sorteringVedInssetting(a, 0, n - 1);
	}

	/**
	 * Sorterer ein del av tabellen, start ... slutt (begge grensene er med)
	 *
	 * @param a     Tabellen som skal jobbes med
	 * @param start Hvor sorteringen starter
	 * @param slutt Hvor sorteringen slutter
	 * @param <T>   Hvilken som helst type element som utvider Comparable klassen
	 */
	public static <T extends Comparable<? super T>> void sorteringVedInssetting(T[] a, int start, int slutt) {
		for (int i = start + 1; i <= slutt; i++) {
			T   tmp = a[i];
			int j   = i - 1;  // siste i sortert del

			boolean ferdig = false;
			while (!ferdig && j >= 0) {
				if (tmp.compareTo(a[j]) < 0) {
					a[j + 1] = a[j];
					j--;
				} else {
					ferdig = true;
				}
			}
			a[j + 1] = tmp;
		}
	}

	/**
	 * Hjelpemetode for å opprette en tabell med tilfeldige tall i tilfeldig rekkefølge
	 *
	 * @param antall     Hvor mange tilfeldige tall som skal genereres
	 * @param randomseed Hvilken randomseed som skal brukes ved generering av tilfeldige tall
	 *
	 * @return En tabell med (antall) tilfeldige tall i tilfeldig rekkefølge
	 */
	private static Integer[] settOppTabell(int antall, int randomseed) {
		Integer[] tabell    = new Integer[antall];
		Random    tilfeldig = new Random(randomseed);
		for (int j = 0; j < antall; j++) {
			tabell[j] = tilfeldig.nextInt();
		}
		return tabell;
	}

	/**
	 * Enkel enum for å enkelt velge hvilken sorteringsmetode som skal brukes<br>
	 * Strengt tatt ikke nødvendig, men gjør koden litt enklere
	 */
	private enum sortmetode {
		STANDARD, SNARVEI, DOBBEL
	}

	/**
	 * Kjører valgt sorteringsmetode (iterasjoner) antall ganger over tabeller tilfedig generert av spesifiserte randomseeds
	 *
	 * @param iterasjoner Hvor mange ganger sortering skal gjøres for å regne gjennomsnitt
	 * @param antallnumre Hvor mange numre tabell som skal sorteres skal inneholde
	 * @param randomseeds Hvilke randomseeds som skal brukes. Denne tabellen må inneholde minst like mange seeds som det er iterasjoner.
	 * @param sm          Hvilken sorteringsmetode som skal brukes
	 *
	 * @return Tiden det tok å kjøre (iterasjoner) antall ganger av valgt sorteringsmetode på tabell av størrelse (antallnumre)
	 */
	private static long sorterOgTaTid(int iterasjoner, int antallnumre, int[] randomseeds, sortmetode sm) {
		long totalTid = 0;
		System.out.println("Sortering med metode: " + sm.toString());
		System.out.print("Tider: ");
		for (int i = 0; i < iterasjoner; i++) {
			Integer[] tabell = settOppTabell(antallnumre, randomseeds[i]);
			// System.out.println(Arrays.toString(tabell));
			Instant start = Instant.now();
			switch (sm) {
				case STANDARD -> sorteringVedInssetting(tabell, tabell.length);
				case SNARVEI -> sorteringMedSnarvei(tabell, tabell.length);
				case DOBBEL -> sorteringMedDobbel(tabell, tabell.length);
			}
			Instant finish = Instant.now();
			long tid = Duration.between(start, finish)
			                   .toMillis();

			totalTid += tid;
			System.out.print(tid + "ms, ");
			// System.out.println(Arrays.toString(tabell));
		}
		System.out.println();
		return totalTid / iterasjoner;
	}
}
