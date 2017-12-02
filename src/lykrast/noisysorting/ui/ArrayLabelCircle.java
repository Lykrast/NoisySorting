package lykrast.noisysorting.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Arc2D;

import lykrast.noisysorting.array.VisualArray;

public class ArrayLabelCircle extends ArrayLabel {
	private static final long serialVersionUID = 1L;

	public ArrayLabelCircle(VisualArray array)
	{
		super(array);
	}
	
	@Override
	protected void paintArray(Graphics g)
	{
		Rectangle bb = g.getClipBounds();
		g.setColor(Color.BLACK);
		g.fillRect(bb.x, bb.y, bb.width, bb.height);
		
		int size = array.getSize();
		int startX = bb.x + 10;
		int width = bb.width - 20;
		int startY = bb.y + 10;
		int height = bb.height - 20;
		double itemArc = -360.0/size;

		for (int i=0;i<array.getSize();i++)
		{
			g.setColor(getColor(i));
			Arc2D arc = new Arc2D.Double(startX, startY, width, height, (itemArc * i) + 90, itemArc, Arc2D.PIE);
			((Graphics2D)g).fill(arc);
		}
	}
	
	private Color getColor(int i)
	{
		switch (status[i])
		{
		case DEFAULT:
			return new Color(Color.HSBtoRGB(array.getSilent(i)/(float)array.getSize(), 1.0F, 0.9F));
		case MARKED:
			return Color.WHITE;
		default:
			return Color.BLACK;
		}
	}

}
