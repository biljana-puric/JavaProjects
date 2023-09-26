package zmijica;

public class Pozicija {

	public enum Smer{LEVO, GORE, DESNO, DOLE};
	private int x, y;
	public Pozicija(int x, int y) {
		this.x = x; this.y = y;
	}
	public int x() { return x; }
	public int y() { return y; }
	public void pomeri(Smer smer) {
	switch (smer) {
	case LEVO: x-=1; break;
	case DESNO: x+=1; break;
	case DOLE: y+=1; break;
	case GORE: y-=1; break;
	}
	}
	public Pozicija pomereno(Smer smer) {
		Pozicija p = new Pozicija(x, y);
		p.pomeri(smer);
		return p;
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Pozicija))
			return false;
		Pozicija p = (Pozicija)obj;
		return x == p.x && y == p.y;
	}
}
