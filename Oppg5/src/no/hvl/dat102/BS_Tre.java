package no.hvl.dat102;

import java.util.Iterator;

/**
 * En datatype som lagrer data i form av et BinærTre
 *
 * @param <T> Elementtypen som binærtreet holder
 */
public class BS_Tre<T extends Comparable<? super T>> implements SoektreInterface<T> {

	/**
	 * Roten til binærtreet
	 */
	private BinaerTreNode<T> rot;

	private int antall;

	/**
	 * Lager et nytt tomt binærtre
	 */
	public BS_Tre() {
		rot    = null;
		antall = 0;
	}

	/**
	 * Lager et binærtre med et angitt alement som rot
	 *
	 * @param element Element som skal bli rot
	 */
	public BS_Tre(T element) {
		rot    = new BinaerTreNode<>(element);
		antall = 1;
	}

	/**
	 * Finner høyden til binærtreet
	 *
	 * @return Integer verdi av høyden til binærtreet
	 */
	public int finnHoyde() {
		BinaerTreNode<T> node = rot;
		if (node == null) {
			return -1;
		}
		return finnHoyde(node, 0);
	}

	/**
	 * Intern metode for å rekursivt finne høyden til binærtreet
	 *
	 * @param node Node en skal jobbe fra
	 *
	 * @return Integer verdi av høyden
	 */
	private int finnHoyde(BinaerTreNode<T> node, int nivaa) {
		if (node == null) {
			return nivaa - 1;
		}
		nivaa++;

		int vh = finnHoyde(node.getVenstre(), nivaa);
		int hh = finnHoyde(node.getHogre(), nivaa);

		return Math.max(vh, hh);
	}

	/**
	 * Søker etter et gitt element i treet.
	 *
	 * @param element elementet vi søker etter.
	 *
	 * @return true om elementet finst, false elles.
	 */
	public boolean inneholder(T element) {
		return false;

		// Kan kalle finn-metoden nedanfor. Prøv sjølv.
	}

	/**
	 * Henter et element i treet.
	 *
	 * @param element elementet vi leiter etter.
	 *
	 * @return Elementet dersom det finst, elles null.
	 */
	public T finn(T element) {

		return finn(element, rot);
	}


	private T finn(T element, BinaerTreNode<T> p) {

		T svar = null;

		// basis: p == null -> tomt undertre, elementet finst ikkje

		if (p != null) {
			int sml = element.compareTo(p.getElement());
			if (sml == 0) {  // basistilfelle
				svar = p.getElement();
			}
			if (sml < 0) {
				svar = finn(element, p.getVenstre());
			} else {
				svar = finn(element, p.getHogre());
			}
		}

		return svar;
	}

	/**
	 * Legg eit element til treet dersom det ikkje finst frå før. Elles
	 * blir det gamle elementet erstatta med det nye.
	 *
	 * @param nyElement elementet som skal leggast til
	 *
	 * @return Elementet som vart erstatta, null om det ikkje finst frå før
	 */
	public T leggTil(T nyElement) {
		T resultat = null;
		if (rot == null) {
			rot = new BinaerTreNode<>(nyElement);
		} else {
			resultat = leggTil(nyElement, rot);
		}
		antall++;
		return resultat;
	}

	private T leggTil(T nyttElement, BinaerTreNode<T> p) {

		// p er ulik null

		T   resultat = null;
		int sml      = nyttElement.compareTo(p.getElement());

		if (sml == 0) {  // elementet finst frå før
			resultat = p.getElement();   // tek vare på gamal verdi
			p.setElement(nyttElement);
		} else if (sml < 0) {
			if (p.getVenstre() != null) {  // p har venstrebarn
				resultat = leggTil(nyttElement, p.getVenstre());
			} else {
				p.setVenstre(new BinaerTreNode<>(nyttElement));
			}
		} else {
			if (p.getHogre() != null) {  // p har høgrebarn
				resultat = leggTil(nyttElement, p.getHogre());
			} else {
				p.setHogre(new BinaerTreNode<>(nyttElement));
			}
		}

		return resultat;
	}

	/**
	 * Fjernar eit element frå treet.
	 *
	 * @param element elementet som skal fjernast.
	 *
	 * @return elementet som vart fjerna eller null om det ikkje finst.
	 */
	public T fjern(T element) {
		// Er ikkje pensum å kunne skrive denne
		// Men de må kunne forklare korleis vi slettar
		// Sjå lyskark

		return null;
	}

	/**
	 * Lagar ein iterator som går gjennom alle element i treet i inorden.
	 *
	 * @return ein iterator som elementa i sortert rekkefølge.
	 */
	public Iterator<T> getInordenIterator() {
		return null;
	}

	/**
	 * Returnerer hvor mange noder som finnes i BinærTreet
	 *
	 * @return integer antall elementer i BinærTreet
	 */
	public int getAntall() {
		return antall;
	}
}
