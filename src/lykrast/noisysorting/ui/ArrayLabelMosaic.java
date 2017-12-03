package lykrast.noisysorting.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import lykrast.noisysorting.array.VisualArray;

public class ArrayLabelMosaic extends ArrayLabel {
	private static final long serialVersionUID = 1L;

	public ArrayLabelMosaic(VisualArray array)
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
		int side = (int) Math.ceil(Math.sqrt(size));
		double itemWidth = (endX-startX)/(double)side;
		double itemHeight = (endY-startY)/(double)side;
		itemWidth = Math.max(1, itemWidth);
		itemHeight = Math.max(1, itemHeight);
		int paddingX = itemWidth >= 3 ? 1 : 0;
		int paddingY = itemHeight >= 3 ? 1 : 0;

		for (int i=0;i<array.getSize();i++)
		{
			g.setColor(getColor(i, side));
			int x = i % side;
			int y = i / side;
			g.fillRect((int)(startX + x*itemWidth) + paddingX, (int)(startY + y*itemHeight) + paddingY, 
					(int)itemWidth - paddingX, (int)itemHeight - paddingY);
		}
	}
	
	private Color getColor(int i, int side)
	{
		switch (status[i])
		{
		case DEFAULT:
			int a = array.getSilent(i) - 1;
			int x = a % side;
			int y = a / side;
			float saturation = 1.0F-(y/((float)side-1))*0.6F;
			return new Color(Color.HSBtoRGB(x/((float)side-1), saturation, 0.9F));
		case MARKED:
			return Color.WHITE;
		default:
			return Color.DARK_GRAY;
		}
	}

}
