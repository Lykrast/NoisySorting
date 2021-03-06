package lykrast.noisysorting.ui.arraylabel;

import java.awt.Color;

import lykrast.noisysorting.array.VisualArray;

public class ArrayLabelHoopsGrey extends ArrayLabelHoops {
	private static final long serialVersionUID = 1L;

	public ArrayLabelHoopsGrey(VisualArray array)
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
			return new Color(Color.HSBtoRGB(0.5F, 1.0F, 1-((array.getSilent(i)-1)/(float)(array.getSize()-1))*0.6F));
		default:
			return new Color(Color.HSBtoRGB(0F, 1.0F, 1-((array.getSilent(i)-1)/(float)(array.getSize()-1))*0.6F));
		}
	}

}
