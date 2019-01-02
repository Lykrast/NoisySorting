package lykrast.noisysorting.ui.arraylabel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import lykrast.noisysorting.array.VisualArray;

//From https://github.com/w0rthy/ArrayVisualizer/blob/master/src/ArrayVisualizer.java#L458
public class ArrayLabelDisparityLoop extends ArrayLabel {
	private static final long serialVersionUID = 1L;

	public ArrayLabelDisparityLoop(VisualArray array) {
		super(array);
	}

	@Override
	protected void paintArray(Graphics g) {
		Rectangle bb = g.getClipBounds();
		g.setColor(Color.BLACK);
		g.fillRect(bb.x, bb.y, bb.width, bb.height);
		
		int size = array.getSize();
		int width = bb.width - 20;
		int height = bb.height - 20;
		
		int lastX = 0;
		int lastY = 0;
	
		((Graphics2D)g).setStroke(new BasicStroke(2));
		for (int i=0;i<size;i++)
		{
			g.setColor(getColor(i));
			
			double length = getDisparity(i);
			double sin = Math.sin(-i*2*Math.PI/size + Math.PI);
			double cos = Math.cos(-i*2*Math.PI/size + Math.PI);
			int posX = width/2+(int)(sin*width/2.0*length);
			int posY = height/2+(int)(cos*height/2.0*length);
			
			if (i > 0) g.drawLine(posX, posY, lastX, lastY);
			lastX = posX;
			lastY = posY;
		}
		//Draw last line
		g.setColor(getColor(0));
		
		double length = getDisparity(0);
		double sin = Math.sin(0*2*Math.PI/size + Math.PI);
		double cos = Math.cos(0*2*Math.PI/size + Math.PI);
		int posX = width/2+(int)(sin*width/2.0*length);
		int posY = height/2+(int)(cos*height/2.0*length);
		
		g.drawLine(posX, posY, lastX, lastY);
	}
	
	private double getDisparity(int i) {
		int size = array.getSize();
		double halfSize = array.getSize()/2.0;
		int diff = i-array.getSilent(i);
		
		return (halfSize - Math.min(Math.min(Math.abs(diff), Math.abs(diff+size)), Math.abs(diff-size))) / halfSize;
	}
	
	private Color getColor(int i)
	{
		switch (status[i])
		{
		case DEFAULT:
			return new Color(Color.HSBtoRGB(array.getSilent(i)/(float)array.getSize(), 1.0F, 0.9F));
		case MARKED:
			return new Color(Color.HSBtoRGB(array.getSilent(i)/(float)array.getSize(), 0.25F, 1.0F));
		default:
			return new Color(Color.HSBtoRGB(array.getSilent(i)/(float)array.getSize(), 0.25F, 0.25F));
		}
	}

}