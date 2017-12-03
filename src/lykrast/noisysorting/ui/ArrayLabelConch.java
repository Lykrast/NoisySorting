package lykrast.noisysorting.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Arc2D;

import lykrast.noisysorting.array.VisualArray;

public class ArrayLabelConch extends ArrayLabel {
	private static final long serialVersionUID = 1L;

	public ArrayLabelConch(VisualArray array) {
		super(array);
	}

	@Override
	protected void paintArray(Graphics g) {
		Rectangle bb = g.getClipBounds();
		g.setColor(Color.BLACK);
		g.fillRect(bb.x, bb.y, bb.width, bb.height);
		
		int size = array.getSize();
		int width = bb.width - 20;
		int midX = bb.x + bb.width/2;
		int midY = bb.y + bb.height/2;
		int height = bb.height - 20;
		double itemArc = -360.0/size;
		double anglePadding = itemArc > -0.75 ? 0.0 : itemArc * 0.1;
	
		for (int i=0;i<array.getSize();i++)
		{
			double scale = (array.getSilent(i)-1.0D)/(size-1.0D);
			g.setColor(getColor(i));
			Arc2D arc = new Arc2D.Double(midX - width * scale * 0.5, midY - height * scale * 0.5, 
					width * scale, height * scale, (itemArc * i) + 90, itemArc - anglePadding, Arc2D.PIE);
			((Graphics2D)g).fill(arc);
		}
	}
	
	private Color getColor(int i)
	{
		switch (status[i])
		{
		case DEFAULT:
			return Color.WHITE;
		case MARKED:
			return Color.CYAN;
		default:
			return Color.RED;
		}
	}

}