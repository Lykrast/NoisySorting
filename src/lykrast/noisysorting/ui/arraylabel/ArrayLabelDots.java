package lykrast.noisysorting.ui.arraylabel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import lykrast.noisysorting.array.VisualArray;

public class ArrayLabelDots extends ArrayLabel {
	private static final long serialVersionUID = 1L;

	public ArrayLabelDots(VisualArray array) {
		super(array);
	}

	@Override
	protected void paintArray(Graphics g) {
		Rectangle bb = g.getClipBounds();
		g.setColor(Color.BLACK);
		g.fillRect(bb.x, bb.y, bb.width, bb.height);

		int size = array.getSize();
		int startX = bb.x + 10;
		int endX = bb.x + bb.width - 10;
		int startY = bb.y + 10;
		int endY = bb.y + bb.height - 10;
		double xSpacing = (endX - startX) / (double) size;
		double ySpacing = (endY - startY) / (double) size;
		double itemWidth = Math.max(4, xSpacing);
		double itemHeight = Math.max(4, ySpacing);

		for (int i = 0; i < array.getSize(); i++) {
			g.setColor(getColor(i));
			int ai = array.getSilent(i);
			g.fillOval(
					(int) Math.ceil(startX + i * xSpacing), 
					(int) Math.ceil(startY + (size - ai) * ySpacing), 
					(int) Math.floor(itemWidth), 
					(int) Math.floor(itemHeight));
		}
	}

	private Color getColor(int i) {
		switch (status[i]) {
			case DEFAULT:
				return Color.WHITE;
			case MARKED:
				return Color.CYAN;
			default:
				return Color.RED;
		}
	}

}
