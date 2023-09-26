package hidroelektrana;

import java.awt.Color;

import javax.sql.rowset.spi.SyncFactory;

public abstract class Proizvodjac extends Parcela implements Runnable {

	protected Baterija baterija;
	private Thread nit;
	private int vreme;
	private int ukupno;
	private boolean radi;
	
	public Proizvodjac(Baterija b, int v, Color c, char oznaka) {
		super(oznaka, c);
		nit=new Thread(this);
		baterija=b;
		vreme=v;
		ukupno=vreme+(int)(Math.random()*300);
		nit.start();
		radi=true;
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
				Thread.sleep(ukupno);
				if(dodajEn()) {
					setForeground(Color.RED);
				}
				Thread.sleep(300);
				setForeground(Color.WHITE);
			}
			
		}
		catch(InterruptedException e) {}
	}

	public abstract boolean dodajEn();

}
