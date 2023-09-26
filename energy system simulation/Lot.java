package hidroelektrana;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class Plac extends Panel {

	private Parcela izabrana;
	private int red, kolona;
	private ArrayList<Proizvodjac> proizvodjaci = new ArrayList<>();
	private Parcela parcele[][];
	public Plac(int r, int k) {
		red=r; kolona=k;
		parcele=new Parcela[red][kolona];
		setLayout(new GridLayout(red, kolona, 2,2));
		for(int i=0; i<red; i++) {
			for(int j=0; j<kolona; j++) {
				if(Math.random()<=0.7) {
					//parcele.add(new TravnataPovrs());
					parcele[i][j]=new TravnataPovrs();
					this.add(parcele[i][j]);
				}
				else {
					parcele[i][j]=new VodenaPovrs();
					this.add(parcele[i][j]);
				}
			}
		}
//		addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				if(izabrana!=null) {
//					izabrana.ponisti();
//				}
//				Component com=e.getComponent();
//				Parcela p = (Parcela)(com);
//				p.izaberi();
//				izabrana=p;
//				izabrana.setFont(new Font(Font.SERIF,Font.BOLD,20));
//			}
//		});
	}
	
	public void izaberiParcelu(Parcela p) {
		if(izabrana!=null) {
			izabrana.ponisti();
		}
		izabrana=p;
		p.izaberi();
		izabrana.setFont(new Font(Font.SERIF,Font.BOLD,20));
	}
	
	private int[] dohvatiParcelu(Parcela p) {
		for(int i=0; i<red; i++) {
			for(int j=0; j<kolona; j++) {
				if(parcele[i][j]==p) {
					int[] ret=new int[2];
					ret[0]=i;
					ret[1]=j;
					return ret;
				}
			}
		}
		return null;
	}
	
	public void dodajProizvodjaca(Proizvodjac p) {
		if(izabrana!=null) {
			int[] indeks = dohvatiParcelu(izabrana);
			int i=indeks[0];
			int j=indeks[1];
			this.remove(parcele[i][j]);
			this.add(p);
			parcele[i][j]=p;
			for(int x=0; x<red; x++) {
				for(int y=0; y<kolona; y++) {
					this.add(parcele[x][y]);
				}
			}

			azuriraj(p, i ,j);
		}
		revalidate();
	}
	
	private int jesteVodena(int x, int y) {
		if(x<0 || x>=red || y<0 || y>=kolona) return 0;
		if (parcele[x][y] instanceof VodenaPovrs) {
			return 1;
		}
		return 0;
	}
	
	private void azuriraj(Proizvodjac p, int x, int y) {
		int vodenih=0;
		if(p instanceof Hidroelektrana) {
			vodenih+=jesteVodena(x-1,y-1);
			vodenih+=jesteVodena(x-1,y);
			vodenih+=jesteVodena(x-1,y+1);
			vodenih+=jesteVodena(x,y-1);
			vodenih+=jesteVodena(x,y+1);
			vodenih+=jesteVodena(x+1,y-1);
			vodenih+=jesteVodena(x+1,y);
			vodenih+=jesteVodena(x+1,y+1);
			((Hidroelektrana) p).postaviBroj(vodenih);
		}
		
		
	}
	public synchronized void zaustavi() {
		for(Proizvodjac p:proizvodjaci) {
			p.zaustavi();
		}
	}
}
