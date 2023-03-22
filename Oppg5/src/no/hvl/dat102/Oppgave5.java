package no.hvl.dat102;

public class Oppgave5 {
	public static void main(String[] args) {
		BS_Tre<Integer> bsTre = new BS_Tre<>();
		/* Random          r     = new Random();

		int antallTall = 3;

		for (int i = 0; i < antallTall; i++) {
			int x = r.nextInt(100);
			bsTre.leggTil(x);
			System.out.println(x);
		} */

		bsTre.leggTil(10);
		bsTre.leggTil(12);
		bsTre.leggTil(11);

		System.out.println("Høyde: " + bsTre.finnHoyde());
	}
}
