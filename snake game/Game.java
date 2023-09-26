package zmijica;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import zmijica.Pozicija.Smer;

public class Igra extends Frame {

	private static final int W = 20;
	private static final int H = 20;
	private Tabla tabla = new Tabla(W, H);
	private Label duzina = new Label("Duzina: 0");
	private TextField sirina = new TextField("20"),
	visina = new TextField("20");
	private Button stani = new Button("Stani");
	private Choice nivo = new Choice();
	public Igra() {
		super("Zmija");
		setSize(400, 400);
		add(tabla, BorderLayout.CENTER);
		add(bottom(), BorderLayout.SOUTH);
		dodajMeni(); dodajOsluskivace();
		setVisible(true);
	}
	public void azuriraj(boolean traje) {
		stani.setEnabled(traje);
		sirina.setEnabled(!traje);
		visina.setEnabled(!traje);
		nivo.setEnabled(!traje);
	}
	
	private void dodajMeni() {
		MenuBar bar = new MenuBar();
		setMenuBar(bar);
		Menu menu = new Menu("Akcija");
		bar.add(menu);
		MenuItem novaIgra = new MenuItem("Nova igra", new MenuShortcut('N'));
		menu.add(novaIgra);
		novaIgra.addActionListener(e->{
		tabla.kreni(
				Integer.parseInt(sirina.getText()),
				Integer.parseInt(visina.getText())
				);
			azuriraj(true);
		});
		menu.addSeparator();
		MenuItem zatvori = new MenuItem("Zatvori", new MenuShortcut('Z'));
		menu.add(zatvori);
		zatvori.addActionListener(e->{tabla.zavrsi();
			dispose();});
		}
		private Panel bottom() {
			Panel p = new Panel(new GridLayout(2,2));
			duzina.setAlignment(Label.CENTER);
			duzina.setFont(new Font(null, Font.BOLD, 18));
			tabla.postaviLabelu(duzina);
			tabla.postaviIgru(this);
			stani.setEnabled(false);
			nivo.add("Lak");
			nivo.add("Srednji");
			nivo.add("Tezak");
			Panel xy = new Panel();
			xy.add(new Label("x, y:"));
			xy.add(sirina);
			xy.add(visina);
			p.add(nivo); p.add(stani); p.add(duzina);
			p.add(xy);
			return p;
		}
		private void dodajOsluskivace() {
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					tabla.zavrsi();
					dispose();
				}
			});
			stani.addActionListener(e->{
				tabla.stani();
				azuriraj(false);
			});
			nivo.addItemListener(e->{
				tabla.postaviNivo(new int[] {500, 300, 100}
				[nivo.getSelectedIndex()]);
			});
			tabla.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					switch(e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
						tabla.pomeri(Smer.LEVO); break;
					case KeyEvent.VK_RIGHT:
						tabla.pomeri(Smer.DESNO); break;
					case KeyEvent.VK_DOWN:
						tabla.pomeri(Smer.DOLE); break;
					case KeyEvent.VK_UP:
						tabla.pomeri(Smer.GORE); break;
					}
				}
			});
		}
		public static void main(String[] args) {
			new Igra();
		}
}
