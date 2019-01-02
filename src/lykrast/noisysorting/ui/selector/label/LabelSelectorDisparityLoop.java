package lykrast.noisysorting.ui.selector.label;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.ui.arraylabel.ArrayLabel;
import lykrast.noisysorting.ui.arraylabel.ArrayLabelDisparityLoop;

public class LabelSelectorDisparityLoop extends LabelSelectorAbstract {

	@Override
	public ArrayLabel getLabel(VisualArray a)
	{
		return new ArrayLabelDisparityLoop(a);
	}
	
	@Override
	public String toString()
	{
		return "Disparity Loop";
	}

	@Override
	public boolean matches(ArrayLabel l) {
		return l instanceof ArrayLabelDisparityLoop;
	}

}
