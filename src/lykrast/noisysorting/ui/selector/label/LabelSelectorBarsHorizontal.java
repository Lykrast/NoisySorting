package lykrast.noisysorting.ui.selector.label;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.ui.arraylabel.ArrayLabel;
import lykrast.noisysorting.ui.arraylabel.ArrayLabelBarsHorizontal;

public class LabelSelectorBarsHorizontal extends LabelSelectorAbstract {

	@Override
	public ArrayLabel getLabel(VisualArray a)
	{
		return new ArrayLabelBarsHorizontal(a);
	}
	
	@Override
	public String toString()
	{
		return "Horizontal Bars";
	}

	@Override
	public boolean matches(ArrayLabel l) {
		return l instanceof ArrayLabelBarsHorizontal;
	}

}
