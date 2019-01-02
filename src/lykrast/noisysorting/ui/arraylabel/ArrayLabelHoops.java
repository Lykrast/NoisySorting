package lykrast.noisysorting.ui.arraylabel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import lykrast.noisysorting.array.VisualArray;

//Inspired by https://www.youtube.com/watch?v=jXs1y3tCKQg
public abstract class ArrayLabelHoops extends ArrayLabel {
	private static final long serialVersionUID = 1L;

	public ArrayLabelHoops(VisualArray array) {
		super(array);
	}

	protected abstract Color getColor(int i);

	@Override
	protected void paintArray(Graphics g) {
		Rectangle bb = g.getClipBounds();
		g.setColor(Color.BLACK);
		g.fillRect(bb.x, bb.y, bb.width, bb.height);
		
		int size = array.getSize();
		int startX = bb.x + 10;
		int width = bb.width - 20;
		int startY = bb.y + 10;
		int height = bb.height - 20;
		double gapX = (double)width/size;
		double gapY = (double)height/size;
	
		for (int i=0;i<size;i++)
		{
			g.setColor(getColor(size-i-1));
			g.fillOval(startX + (int)(i*gapX/2.0), startY + (int)(i*gapY/2.0), width - (int)(i*gapX), height - (int)(i*gapY));
		}
	}

}