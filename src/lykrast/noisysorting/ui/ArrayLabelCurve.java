package lykrast.noisysorting.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Path2D;

import lykrast.noisysorting.array.VisualArray;

public class ArrayLabelCurve extends ArrayLabel {
	private static final long serialVersionUID = 1L;

	public ArrayLabelCurve(VisualArray array)
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
		int endX = bb.x + bb.width - 10;
		int startY = bb.y + 10;
		int endY = bb.y + bb.height - 10;
		double itemWidth = (endX-startX)/(double)size;
		double itemHeight = (endY-startY)/(double)size;
		itemWidth = Math.max(1, itemWidth);
		itemHeight = Math.max(1, itemHeight);

		Path2D path = new Path2D.Double();
		path.moveTo(startX + 0.5*itemWidth, startY + (size-array.getSilent(0)+0.5)*itemHeight);
		for (int i=1;i<array.getSize();i++)
		{
			int ai = array.getSilent(i);
			path.lineTo(startX + (i+0.5)*itemWidth, startY + (size-ai+0.5)*itemHeight);
		}
		//TODO Status colors
		g.setColor(Color.WHITE);
		Graphics2D g2 = ((Graphics2D)g);
		g2.draw(path);
	}

}
