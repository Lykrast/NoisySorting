package lykrast.noisysorting.ui.selector.label;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.ui.arraylabel.ArrayLabel;
import lykrast.noisysorting.ui.arraylabel.ArrayLabelMosaic;

public class LabelSelectorMosaic extends LabelSelectorAbstract {

	@Override
	public ArrayLabel getLabel(VisualArray a)
	{
		return new ArrayLabelMosaic(a);
	}
	
	@Override
	public String toString()
	{
		return "Mosaic";
	}

	@Override
	public boolean matches(ArrayLabel l) {
		return l instanceof ArrayLabelMosaic;
	}

}
