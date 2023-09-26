package zmijica;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import zmijica.Pozicija.Smer;

public class Zmija extends Figura {

	private List<Pozicija> polja = new ArrayList<>();
	public Zmija(Pozicija p) {
		super(p, Color.GREEN); polja.add(p);
	}
	public void uvecaj() {
		polja.add(polja.get(polja.size()-1));
	}
	public int duzina() { return polja.size(); }
	@Override
	protected void crt(Graphics g, int w, int h) {
		for(Pozicija p:polja)
			g.fillOval(p.x()*w, p.y()*h, w, h);
		g.setColor(Color.WHITE);
		g.fillOval(p.x()*w+w/4, p.y()*h+h/4, w/2,h/2);
	}
	@Override
	public boolean zauzimaPoziciju(Pozicija p) {
		return polja.contains(p);
	}
	@Override
	public void pomeri(Smer smer, Tabla t) throws GNeMoze {
		Pozicija pom = p.pomereno(smer);
		if(t.zauzetoPolje(pom) && !pom.equals(polja.get(polja.size()-1))) throw new GNeMoze();
		polja.remove(polja.size()-1); polja.add(0, p = pom);
	}

	
}
