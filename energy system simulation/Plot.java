package hidroelektrana;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Parcela extends Label {
	
	private boolean izabrana = false;
	public Parcela(char oznaka, Color boja) {
		super(oznaka+"");
		setAlignment(CENTER);
		setForeground(Color.WHITE);
		setFont(new Font(Font.SERIF, Font.BOLD, 14));
		setBackground(boja);
		Parcela parcela=this;
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Component source = (Component) e.getSource();
				Plac plac = (Plac) source.getParent();
				plac.izaberiParcelu(parcela);
				izaberi();
			}
		});
	}
	
	public void promeniPozadinu(Color c) {
		setBackground(c);
	}
	
	public void izaberi() {
		izabrana=true;
	}
	public void ponisti() {
		this.setFont(new Font(Font.SERIF, Font.BOLD, 14));
		izabrana=false;
	}

}
