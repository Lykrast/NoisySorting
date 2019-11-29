package lykrast.noisysorting.ui.arraylabel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;

import lykrast.noisysorting.array.VisualArray;

//https://github.com/w0rthy/ArrayVisualizer/blob/master/src/array/visualizer/ArrayVisualizer.java#L284
public class ArrayLabelDisparityHoops extends ArrayLabel {
	private static final long serialVersionUID = 1L;

	public ArrayLabelDisparityHoops(VisualArray array) {
		super(array);
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

	private static Stroke stroke = new BasicStroke(1.0f);

	@Override
	protected void paintArray(Graphics g) {
		Rectangle bb = g.getClipBounds();
		g.setColor(Color.BLACK);
		g.fillRect(bb.x, bb.y, bb.width, bb.height);

		((Graphics2D) g).setStroke(stroke);
		int size = array.getSize();
		double halfsize = size/2.0;
		int halfwidth = bb.width / 2 + 10;
		int halfheight = bb.height / 2 + 10;
		double diam = Math.min(bb.width, bb.height) - 20;
		double diamstep = diam / size;

		for (int i = size-1; i >=0; i--) {
			g.setColor(getColor(i));
			
			int radius = (int) (diam/2.0);
			int dist = Math.abs(i-array.getSilent(i));
            double disparity = 1.0-(Math.min(dist, size-dist)/halfsize);
            int actualdiam = (int)(disparity*diam);
            g.drawOval(halfwidth-actualdiam/2, halfheight-radius, actualdiam, actualdiam);
			
			diam -= diamstep;
		}
	}

}