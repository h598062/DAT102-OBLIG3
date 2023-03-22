package no.hvl.dat102;

/**
 * Nodetypen som brukes for å holde elementer i et binærtre i BS_Tre klassen
 *
 * @param <T> Elementtypen som noden holder
 */
public class BinaerTreNode<T> {
	private T                element;
	private BinaerTreNode<T> venstre;
	private BinaerTreNode<T> hogre;

	/**
	 * Oppretter en ny BinaerTreNode som holder element av typen T
	 *
	 * @param el Hvilken type element denne noden kan holde
	 */
	BinaerTreNode(T el) {
		element = el;
		venstre = null;
		hogre   = null;
	}

	/**
	 * Returnerer elementet som er i denne noden
	 *
	 * @return Elementet som er lagret i noden, eller null
	 */
	public T getElement() {
		return element;
	}

	/**
	 * Lagrer et element i denne noden
	 *
	 * @param element Elementet som skal lagres i noden
	 */
	public void setElement(T element) {
		this.element = element;
	}

	/**
	 * Henter ut denne noden sin venstre undernode
	 *
	 * @return Referanse til venstre undernode
	 */
	public BinaerTreNode<T> getVenstre() {
		return venstre;
	}

	/**
	 * Lagrer en referanse til en annen node i denne noden sin "venstre"
	 *
	 * @param venstre Noden som skal lagres som venstre node
	 */
	public void setVenstre(BinaerTreNode<T> venstre) {
		this.venstre = venstre;
	}

	/**
	 * Henter ut denne noden sin høyre undernode
	 *
	 * @return Referanse til høyre undernode
	 */
	public BinaerTreNode<T> getHogre() {
		return hogre;
	}

	/**
	 * Lagrer en referanse til en annen node i denne noden sin "høyre"
	 *
	 * @param hogre Noden som skal lagres som høyre node
	 */
	public void setHogre(BinaerTreNode<T> hogre) {
		this.hogre = hogre;
	}

}

