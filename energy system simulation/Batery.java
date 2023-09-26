package hidroelektrana;

public class Baterija {

	private int kolicina;
	private int kapacitet;
	
	public Baterija(int kap) {
		kapacitet=kap;
		kolicina=kapacitet;
	}
	
	public synchronized void dodajEnergiju(int en) {
		if(en+kolicina>kapacitet) {
			kolicina=kapacitet;
		}
		else {
			kolicina+=en;
		}
	}
	
	public void isprazni() {
		kolicina=0;
	}
	
	public synchronized boolean puna() {
		return kolicina==kapacitet;
	}
	
}
