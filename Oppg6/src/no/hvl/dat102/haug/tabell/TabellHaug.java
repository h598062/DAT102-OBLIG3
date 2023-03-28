package no.hvl.dat102.haug.tabell;

/**
 * Minimumshaug som holder enhver type element som utvider Comparable<br>
 * Bruker at rot-noden starter ved indeks 1 i intern tabell
 *
 * @param <T> Typen element som haugen skal holde
 */
public class TabellHaug<T extends Comparable<T>> {

	/**
	 * Tabell som holder data til haugen
	 */
	private T[] haug;

	/**
	 * Lagrer posisjon til siste brukte plass i tabellen til haugen
	 */
	private int antall;

	private static final int STDK = 100;

	public TabellHaug() {
		haug   = (T[]) new Comparable[STDK + 1];
		antall = 0;
	}

	/**
	 * Legger til et element i haugen
	 *
	 * @param el Elementet som skal legges til
	 */
	public void leggTilElement(T el) {
		if (antall + 1 == haug.length) {
			utvidTabell();
		}
		int nyPos    = antall + 1;
		int forelder = nyPos / 2;

		while (forelder > 0 && el.compareTo(haug[forelder]) < 0) {
			haug[nyPos] = haug[forelder];
			nyPos       = forelder;
			forelder    = nyPos / 2;
		}
		haug[nyPos] = el;
		antall++;
	}

	/**
	 * Dobler tabellen ved behov for utviding
	 */
	private void utvidTabell() {
		int lengde = haug.length;
		T[] ny     = (T[]) new Comparable[2 * lengde];
		for (int i = 0; i < antall; i++) {
			ny[i] = haug[i];
		}
		haug = ny;
	}

	/**
	 * Intern metode for å reparere haug når nye elementer legges til<br>
	 * Ikke i bruk, integrert inn i leggTilElement() metoden
	 */
	private void reparerOpp() {
		// Fyll ut
	}

	/**
	 * Fjerner minste element fra haugen, som alltid skal være i rot-noden
	 *
	 * @return Minste element i haugen
	 */
	public T fjernMinste() {
		T svar = null;
		if (!erTom()) {
			svar    = haug[1];
			haug[1] = haug[antall];
			antall--;
			reparerNed();
		}
		//skrivTab();
		return svar;
	}

	/**
	 * Finner minste element i haugen, som skal være i rot-noden
	 *
	 * @return Minste element i haugen
	 */
	public T finnMinste() {
		T svar = null;
		if (!erTom()) {
			svar = haug[1];
		}
		return svar;
	}

	/**
	 * Intern metode for å reparere haugen ved fjerning av rot-node
	 */
	private void reparerNed() {
		boolean ferdig   = false;
		int     forelder = 1;
		int     vbarn    = forelder * 2;

		while (!ferdig && harVB(forelder)) { // Har flere noder lenger nede
			int minbarn = vbarn;
			int hbarn   = vbarn + 1;

			if (harHB(forelder) && haug[hbarn].compareTo(haug[vbarn]) < 0) {
				minbarn = hbarn;
			}
			// Har funnet det "minste" av barna. Sml med forelder
			if (haug[forelder].compareTo(haug[minbarn]) <= 0) {
				ferdig = true;
			} else { // Bytt om og gå videre nedover hvis forelder er for stor
				T hjelp = haug[minbarn];
				haug[minbarn]  = haug[forelder];
				haug[forelder] = hjelp;

				forelder = minbarn;
				vbarn    = forelder * 2;
			}
		}
	}

	/**
	 * Sjekker om en gitt nodeindeks har et venstrebarn
	 *
	 * @param node Noden en skal sjekke om har venstrebarn
	 *
	 * @return Sant om den gitte noden har et venstrebarn
	 */
	private boolean harVB(int node) {
		return 2 * node <= antall;
	}

	/**
	 * Sjekker om en gitt nodeindeks har et høyrebarn
	 *
	 * @param node Noden en skal sjekke om har høyrebarn
	 *
	 * @return Sant om den gitte noden har et høyrebarn
	 */
	private boolean harHB(int node) {
		return 2 * node + 1 <= antall;
	}

	/**
	 * Sjekker om haugen er tom
	 *
	 * @return Sant om haugen er tom
	 */
	public boolean erTom() {
		return antall < 1;
	}

	/**
	 * Hjelpemetode til test
	 */
	public void skrivTab() {
		for (int i = 1; i <= antall; i++) {
			System.out.print(haug[i] + "  ");
		}
		System.out.println();
	}
}
