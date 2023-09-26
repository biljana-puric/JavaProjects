package zmijica;

import java.awt.Color;
import java.awt.Graphics;

import zmijica.Pozicija.Smer;

public class Muva extends Figura {

	public Muva(Pozicija p) { super(p, Color.BLACK); }
	@Override
	protected void crt(Graphics g, int w, int h) {
		int x = p.x(); int y = p.y();
		g.drawLine(x*w, y*h, x*w+w, y*h+h);
		g.drawLine(x*w, y*h+h, x*w+w, y*h);
		g.drawLine(x*w+w/2, y*h, x*w+w/2, y*h+h);
		g.drawLine(x*w, y*h+h/2, x*w+w, y*h+h/2);
	}
	
	@Override
	public boolean zauzimaPoziciju(Pozicija p) {
		return this.p.equals(p);
	}
	
	@Override
	public void pomeri(Smer smer, Tabla p) throws GNeMoze {
		return;
	}

}
