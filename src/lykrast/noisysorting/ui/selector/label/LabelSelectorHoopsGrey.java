package lykrast.noisysorting.ui.selector.label;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.ui.arraylabel.ArrayLabel;
import lykrast.noisysorting.ui.arraylabel.ArrayLabelHoopsGrey;

public class LabelSelectorHoopsGrey extends LabelSelectorAbstract {

	@Override
	public ArrayLabel getLabel(VisualArray a)
	{
		return new ArrayLabelHoopsGrey(a);
	}
	
	@Override
	public String toString()
	{
		return "Greyscale Hoops";
	}

	@Override
	public boolean matches(ArrayLabel l) {
		return l instanceof ArrayLabelHoopsGrey;
	}

}
