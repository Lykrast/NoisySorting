package lykrast.noisysorting.ui.arraylabel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import lykrast.noisysorting.array.VisualArray;

public class ArrayLabelRainbow extends ArrayLabel {
	private static final long serialVersionUID = 1L;

	public ArrayLabelRainbow(VisualArray array) {
		super(array);
	}

	@Override
	protected void paintArray(Graphics g) {
		Rectangle bb = g.getClipBounds();
		g.setColor(Color.BLACK);
		g.fillRect(bb.x, bb.y, bb.width, bb.height);

		int size = array.getSize();
		int startX = bb.x;
		int endX = bb.x + bb.width;
		int startY = bb.y;
		double itemWidth = (endX - startX) / (double) size;
		int itemHeight = bb.height;
		itemWidth = Math.max(1, itemWidth);

		for (int i = 0; i < array.getSize(); i++) {
			g.setColor(getColor(i));
			g.fillRect(
					(int) Math.ceil(startX + i * itemWidth), startY, 
					(int) Math.ceil(itemWidth), itemHeight);
		}
	}

	private Color getColor(int i) {
		switch (status[i]) {
			case DEFAULT:
				return new Color(Color.HSBtoRGB(array.getSilent(i) / (float) array.getSize(), 1.0F, 0.9F));
			case MARKED:
				return new Color(Color.HSBtoRGB(array.getSilent(i) / (float) array.getSize(), 0.25F, 1.0F));
			default:
				return new Color(Color.HSBtoRGB(array.getSilent(i) / (float) array.getSize(), 0.25F, 0.25F));
		}
	}

}
