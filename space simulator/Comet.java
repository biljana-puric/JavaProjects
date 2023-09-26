package simulator;

import java.awt.Color;
import java.awt.Graphics;

public class Kometa extends NebeskoTelo {

	private double rand=Math.random()*90;
	public Kometa(int x, int y, int r) {
		super(x,y,Color.GRAY,r);
	}
	@Override
	public void crtaj(Graphics g) {
		g.setColor(boja);
		double inc =2*Math.PI/5;
		int xPoints[]=new int[5];
		int yPoints[]=new int[5];
		xPoints[0]=(int)(Math.cos(rand)*poluprecnik+x );
		yPoints[0]=(int)(Math.sin(rand)*poluprecnik+y);
		for(int i=1; i<5; i++) {
			int xx= (int)(Math.cos(rand+inc*i)*poluprecnik+x );
			int yy= (int)(Math.sin(rand+inc*i)*poluprecnik+y);
			xPoints[i]=xx;
			yPoints[i]=yy;
		}
        g.fillPolygon(xPoints, yPoints, 5);
	}

}
