package lykrast.noisysorting.ui.selector.label;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.ui.arraylabel.ArrayLabel;
import lykrast.noisysorting.ui.arraylabel.ArrayLabelCircleGrey;

public class LabelSelectorCircleGrey extends LabelSelectorAbstract {

	@Override
	public ArrayLabel getLabel(VisualArray a)
	{
		return new ArrayLabelCircleGrey(a);
	}
	
	@Override
	public String toString()
	{
		return "Greyscale Circle";
	}

	@Override
	public boolean matches(ArrayLabel l) {
		return l instanceof ArrayLabelCircleGrey;
	}

}
