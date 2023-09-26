package hidroelektrana;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EnergetskiSistem extends Frame {

	private Plac plac;
	private Baterija baterija;
	private Panel panel=new Panel();
	private Button dodaj=new Button("Dodaj");
	public EnergetskiSistem(int r, int k, int kap) {
		super("Energetski sistem");
		plac= new Plac(r, k);
		baterija=new Baterija(kap);
		setSize(500, 500);
		
		dodaj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Proizvodjac p=new Hidroelektrana(baterija);
				plac.dodajProizvodjaca(p);
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				plac.zaustavi();
				dispose();
			}
		});
		
		panel.add(dodaj);
		add(panel, BorderLayout.NORTH);
		add(plac, BorderLayout.CENTER);
		
		setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new EnergetskiSistem(5,5,10);
	}
}
