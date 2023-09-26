package simulator;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Svemir extends Canvas implements Runnable {

	private Thread nit;
	private boolean radi;
	private ArrayList<NebeskoTelo> tela=new ArrayList<>();
	public Svemir() {
		setBackground(Color.BLACK);
		nit=new Thread(this);
		nit.start();
	}
	
	public synchronized void dodajTelo(NebeskoTelo t) {
		tela.add(t);
	}
	
	public synchronized void pokreni() {
		radi=true;
		notify();
	}
	
	public synchronized void zaustavi() {
		radi=false;
		nit.interrupt();
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				synchronized (this) {
					while(!radi) wait();
				}
				Thread.sleep(100);
				repaint();
			}
		}
		catch(InterruptedException e) {}
	}

	@Override
	public void paint(Graphics g) {
		int w=getWidth();
		int h=getHeight();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, w, h);
		iscrtaj();
	}
	
	private void iscrtaj() {
		for(int i=0; i<tela.size(); i++) {
			tela.get(i).crtaj(getGraphics());
			tela.get(i).pomeriY(5);
		}
		
	}

}
