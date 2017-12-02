package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.ui.ArrayLabel;
import lykrast.noisysorting.ui.ArrayLabelBarsHorizontal;

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
