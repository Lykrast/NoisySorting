package lykrast.noisysorting.ui;

import java.awt.Color;

import lykrast.noisysorting.array.VisualArray;

public class ArrayLabelCircleColor extends ArrayLabelCircle {
	private static final long serialVersionUID = 1L;

	public ArrayLabelCircleColor(VisualArray array)
	{
		super(array);
	}
	
	@Override
	protected Color getColor(int i)
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
