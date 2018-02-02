package lykrast.noisysorting.ui.selector.label;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.ui.arraylabel.ArrayLabel;
import lykrast.noisysorting.ui.arraylabel.ArrayLabelCurve;

public class LabelSelectorCurve extends LabelSelectorAbstract {

	@Override
	public ArrayLabel getLabel(VisualArray a)
	{
		return new ArrayLabelCurve(a);
	}
	
	@Override
	public String toString()
	{
		return "Curve";
	}

	@Override
	public boolean matches(ArrayLabel l) {
		return l instanceof ArrayLabelCurve;
	}

}
