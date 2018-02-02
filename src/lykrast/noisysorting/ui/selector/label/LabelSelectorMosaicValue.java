package lykrast.noisysorting.ui.selector.label;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.ui.arraylabel.ArrayLabel;
import lykrast.noisysorting.ui.arraylabel.ArrayLabelMosaicValue;

public class LabelSelectorMosaicValue extends LabelSelectorAbstract {

	@Override
	public ArrayLabel getLabel(VisualArray a)
	{
		return new ArrayLabelMosaicValue(a);
	}
	
	@Override
	public String toString()
	{
		return "Numbered Mosaic";
	}

	@Override
	public boolean matches(ArrayLabel l) {
		return l instanceof ArrayLabelMosaicValue;
	}

}
