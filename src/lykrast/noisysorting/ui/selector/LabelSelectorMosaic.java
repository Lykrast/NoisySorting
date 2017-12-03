package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.ui.ArrayLabel;
import lykrast.noisysorting.ui.ArrayLabelMosaic;

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
