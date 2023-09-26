package zmijica;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Label;

import zmijica.Pozicija.Smer;

public class Tabla extends Canvas implements Runnable {
		private int x, y, ms = 500;
		private Zmija zmija;
		private Muva muva;
		private Thread nit = new Thread(this);
		private boolean radi = false;
		private Label lduzina;
		private Smer smer, poslednji;
		private Igra igra;
		public Tabla(int x, int y) {
		this.x = x; this.y = y; nit.start();
		}
		public void postaviIgru(Igra igra) { this.igra = igra; }
		public void postaviLabelu(Label delova) {
			this.lduzina = delova;
		}
		private void azurirajLabele() {
			if(lduzina == null || zmija == null) return;
			lduzina.setText("Duzina: "+zmija.duzina());
		}
		private void napraviMuvu() {
			if(zmija.duzina() == x*y) { muva = null; return; }
			while(true) {
				int x = (int)(Math.random()*this.x);
				int y = (int)(Math.random()*this.y);
				Pozicija p = new Pozicija(x,y);
				try {
					if(!zauzetoPolje(p)) {
						muva = new Muva(p); break;
					}
				} catch (GNeMoze e) {}
			}
		}
		public boolean zauzetoPolje(Pozicija p) throws GNeMoze {
			if(p.x() >= x || p.y() >= y ||
					p.x() < 0 || p.y() < 0) throw new GNeMoze();
			return zmija.zauzimaPoziciju(p);
		}
		
		private synchronized void azuriraj() {
			if(zmija == null) {
				zmija = new Zmija(new Pozicija(x/2,y/2));
				napraviMuvu();
				smer = poslednji = Smer.DESNO;
				return;
			}
			try {
				zmija.pomeri(smer, this);
				poslednji = smer;
				if(zmija.pozicija().equals(muva.pozicija())){
					napraviMuvu();
					zmija.uvecaj();
				}
			} catch (GNeMoze e) {
				zmija.postaviBoju(Color.RED);
				stani();
				igra.azuriraj(false);
			}
		}
		
		public int x() {return x;}
		public int y() {return y;}
		public synchronized void pomeri(Smer smer) {
			if(zmija == null) return;
			if(Math.abs(poslednji.ordinal()-smer.ordinal()) == 2)
				return;
			this.smer = smer;
		}
		public synchronized void postaviNivo(int ms) {
			this.ms = ms;
		}
		@Override
		public void run() {
			try {
				while(!Thread.interrupted()) {
					synchronized (this) {
						while(!radi) wait();
					}
					Thread.sleep(ms);
					azuriraj(); azurirajLabele();
					repaint();
				}
			}catch (InterruptedException e) {}
		}
		
		public synchronized void kreni(int x, int y) {
			this.x = x; this.y = y; zmija = null; muva = null;
			azurirajLabele(); repaint();
			radi = true; notify();
		}
		public synchronized void stani() { radi = false; }
		public void zavrsi() { nit.interrupt(); }
		@Override
		public void paint(Graphics g) {
			int w = getWidth()/x;
			int h = getHeight()/y;
			g.setColor(new Color(220, 220, 220));
			g.fillRect(0, 0, w*x, h*y);
			g.setColor(Color.LIGHT_GRAY);
			for(int i=0;i<=x;i++)
				g.drawLine(w*i, 0, w*i, h*y);
			for(int i=0;i<=y;i++)
				g.drawLine(0, h*i, w*x, h*i);
			g.setColor(Color.BLACK);
			g.drawRect(0, 0, w*x, h*y);
			if(zmija != null) zmija.crtaj(this);
			if(muva != null) muva.crtaj(this);
		}

}
