package simulator;

public class Generator implements Runnable {

	private Svemir svemir;
	private boolean radi;
	private Thread nit;
	public Generator(Svemir s){
		svemir=s;
		nit=new Thread(this);
		nit.start();
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
				Thread.sleep(900);
				int x=(int)(Math.random()*200);
				int r=(int)(Math.random()*20)+10;
				Kometa kometa=new Kometa(x,0,r);
				svemir.dodajTelo(kometa);
			}
		}
		catch(InterruptedException e) {}
	}

}
