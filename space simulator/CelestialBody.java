package simulator;

import java.awt.Color;

public abstract class NebeskoTelo extends Objekat {

	protected int poluprecnik;
	public NebeskoTelo(int x, int y, Color c, int r) {
		super(x,y,c);
		poluprecnik=r;
	}

}
