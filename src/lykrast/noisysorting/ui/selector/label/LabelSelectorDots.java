package lykrast.noisysorting.ui.selector.label;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.ui.arraylabel.ArrayLabel;
import lykrast.noisysorting.ui.arraylabel.ArrayLabelDots;

public class LabelSelectorDots extends LabelSelectorAbstract {

	@Override
	public ArrayLabel getLabel(VisualArray a)
	{
		return new ArrayLabelDots(a);
	}
	
	@Override
	public String toString()
	{
		return "Dots";
	}

	@Override
	public boolean matches(ArrayLabel l) {
		return l instanceof ArrayLabelDots;
	}

}
