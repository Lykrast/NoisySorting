package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.ui.ArrayLabel;
import lykrast.noisysorting.ui.ArrayLabelCircleColor;

public class LabelSelectorCircleColor extends LabelSelectorAbstract {

	@Override
	public ArrayLabel getLabel(VisualArray a)
	{
		return new ArrayLabelCircleColor(a);
	}
	
	@Override
	public String toString()
	{
		return "Color Circle";
	}

	@Override
	public boolean matches(ArrayLabel l) {
		return l instanceof ArrayLabelCircleColor;
	}

}
