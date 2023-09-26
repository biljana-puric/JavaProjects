package simulator;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Objekat {
	protected int x,y;
	protected Color boja;
	
	public Objekat(int xx, int yy, Color c) {
		x=xx; y=yy; boja=c;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Color getBoja() {
		return boja;
	}
	
	public void pomeriX(int pom) {
		x=x+pom;
	}
	public void pomeriY(int pom) {
		y=y+pom;
	}
	
	public abstract void crtaj(Graphics g);
}
