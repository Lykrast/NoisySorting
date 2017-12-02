package lykrast.noisysorting.ui;

import java.awt.Color;

import lykrast.noisysorting.array.VisualArray;

public class ArrayLabelCircleGrey extends ArrayLabelCircle {
	private static final long serialVersionUID = 1L;

	public ArrayLabelCircleGrey(VisualArray array)
	{
		super(array);
	}
	
	@Override
	protected Color getColor(int i)
	{
		switch (status[i])
		{
		case DEFAULT:
			return new Color(Color.HSBtoRGB(0F, 0F, 1-((array.getSilent(i)-1)/(float)(array.getSize()-1))));
		case MARKED:
			return Color.CYAN;
		default:
			return Color.RED;
		}
	}

}
