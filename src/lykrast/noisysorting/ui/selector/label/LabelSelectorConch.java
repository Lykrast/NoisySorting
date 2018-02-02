package lykrast.noisysorting.ui.selector.label;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.ui.arraylabel.ArrayLabel;
import lykrast.noisysorting.ui.arraylabel.ArrayLabelConch;

public class LabelSelectorConch extends LabelSelectorAbstract {

	@Override
	public ArrayLabel getLabel(VisualArray a)
	{
		return new ArrayLabelConch(a);
	}
	
	@Override
	public String toString()
	{
		return "Conch";
	}

	@Override
	public boolean matches(ArrayLabel l) {
		return l instanceof ArrayLabelConch;
	}

}
