package zmijica;

import java.awt.Color;
import java.awt.Graphics;

import zmijica.Pozicija.Smer;

public abstract class Figura {

	protected Pozicija p;
	protected Color boja;
	public Figura(Pozicija p, Color boja) {
		this.p = p; this.boja = boja;
	}
	protected abstract void crt(Graphics g, int w, int h);
	public void crtaj(Tabla t) {
		Graphics g = t.getGraphics();
		int w = t.getWidth() / t.x();
		int h = t.getHeight() / t.y();
		g.setColor(boja); crt(g, w, h);
	}
	public void postaviBoju(Color b) { this.boja = b;}
	public abstract boolean zauzimaPoziciju(Pozicija p);
	public abstract void pomeri(Smer smer, Tabla t) throws GNeMoze;
	public Pozicija pozicija() { return p; }
}
