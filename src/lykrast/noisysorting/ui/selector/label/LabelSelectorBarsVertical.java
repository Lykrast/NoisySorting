package lykrast.noisysorting.ui.selector.label;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.ui.arraylabel.ArrayLabel;
import lykrast.noisysorting.ui.arraylabel.ArrayLabelBarsVertical;

public class LabelSelectorBarsVertical extends LabelSelectorAbstract {

	@Override
	public ArrayLabel getLabel(VisualArray a)
	{
		return new ArrayLabelBarsVertical(a);
	}
	
	@Override
	public String toString()
	{
		return "Vertical Bars";
	}

	@Override
	public boolean matches(ArrayLabel l) {
		return l instanceof ArrayLabelBarsVertical;
	}

}
