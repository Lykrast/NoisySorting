package lykrast.noisysorting.ui.arraylabel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import lykrast.noisysorting.array.VisualArray;

public class ArrayLabelBarsHorizontal extends ArrayLabel {
	private static final long serialVersionUID = 1L;

	public ArrayLabelBarsHorizontal(VisualArray array)
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
		itemHeight = Math.max(1, itemHeight);
		int itemPadding = itemHeight >= 3.0 ? 1 : 0;

		for (int i=0;i<array.getSize();i++)
		{
			g.setColor(getColor(i));
			int ai = array.getSilent(i);
			g.fillRect((int)Math.ceil(startX + (size-ai)*itemWidth), (int)Math.ceil(startY + i*itemHeight) + itemPadding, 
					(int)Math.floor(ai*itemWidth), (int)Math.floor(itemHeight) - itemPadding);
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
