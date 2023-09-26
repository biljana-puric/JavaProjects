package hidroelektrana;

import java.awt.Color;

public class Hidroelektrana extends Proizvodjac {

	private int brojPovrsina;
	public Hidroelektrana(Baterija b) {
		super(b, 1500, Color.BLUE, 'H');
		brojPovrsina=0;
	}
	
	public void postaviBroj(int b) {
		brojPovrsina=b;
	}
	
	@Override
	public synchronized boolean dodajEn() {
		if (brojPovrsina==0) return false;
		for(int i=0; i<brojPovrsina; i++) {
			baterija.dodajEnergiju(1);
		}
		return true;
		
	}

}
