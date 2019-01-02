package lykrast.noisysorting.ui.selector.label;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.ui.arraylabel.ArrayLabel;
import lykrast.noisysorting.ui.arraylabel.ArrayLabelHoopsColor;

public class LabelSelectorHoopsColor extends LabelSelectorAbstract {

	@Override
	public ArrayLabel getLabel(VisualArray a)
	{
		return new ArrayLabelHoopsColor(a);
	}
	
	@Override
	public String toString()
	{
		return "Color Hoops";
	}

	@Override
	public boolean matches(ArrayLabel l) {
		return l instanceof ArrayLabelHoopsColor;
	}

}
