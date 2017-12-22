package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.ui.ArrayLabel;
import lykrast.noisysorting.ui.ArrayLabelMosaicValue;

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
