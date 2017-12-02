package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.ui.ArrayLabel;
import lykrast.noisysorting.ui.ArrayLabelCircleGrey;

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
