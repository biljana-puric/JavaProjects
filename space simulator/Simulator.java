package simulator;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Simulator extends Frame {

	private Svemir svemir = new Svemir();
	private Generator generator = new Generator(svemir);
	private Button pokreni=new Button("Pokreni!");
	private Panel komande=new Panel();
	public Simulator() {
		super();
		setSize(200, 400);
		
		pokreni.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				svemir.pokreni();
				generator.pokreni();
				pokreni.setEnabled(false);
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				svemir.zaustavi();
				generator.zaustavi();
				dispose();
			}
		});
		add(svemir, BorderLayout.CENTER);
		komande.add(pokreni);
		add(komande, BorderLayout.SOUTH);
		
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Simulator();
	}
}
