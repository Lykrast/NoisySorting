package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.ui.ArrayLabel;
import lykrast.noisysorting.ui.ArrayLabelCircle;

public class LabelSelectorCircle extends LabelSelectorAbstract {

	@Override
	public ArrayLabel getLabel(VisualArray a)
	{
		return new ArrayLabelCircle(a);
	}
	
	@Override
	public String toString()
	{
		return "Color Circle";
	}

	@Override
	public boolean matches(ArrayLabel l) {
		return l instanceof ArrayLabelCircle;
	}

}
