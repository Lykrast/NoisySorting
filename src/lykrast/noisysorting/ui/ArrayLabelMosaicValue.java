package lykrast.noisysorting.ui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import lykrast.noisysorting.array.VisualArray;

public class ArrayLabelMosaicValue extends ArrayLabel {
	private static final long serialVersionUID = 1L;

	public ArrayLabelMosaicValue(VisualArray array)
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
		int rectWidth = (int)itemWidth - paddingX;
		int rectHeight = (int)itemHeight - paddingY;
		
		g.setFont(g.getFont().deriveFont(rectWidth/3.0F));
		FontMetrics metrics = g.getFontMetrics();

		for (int i=0;i<array.getSize();i++)
		{
			g.setColor(getColor(i));
			int rectStartX = (int)(startX + (i % side)*itemWidth) + paddingX;
			int rectStartY = (int)(startY + (i / side)*itemHeight) + paddingY;
			g.fillRect(rectStartX, rectStartY, rectWidth, rectHeight);
			
			g.setColor(Color.BLACK);
			Rectangle2D renderSize = metrics.getStringBounds(Integer.toString(array.getSilent(i)), g);
			g.drawString(Integer.toString(array.getSilent(i)), 
					rectStartX + rectWidth/2 - (int)renderSize.getWidth()/2, 
					rectStartY + rectHeight/2 + (int)renderSize.getHeight()/2 - metrics.getDescent());
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
