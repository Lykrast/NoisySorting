package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.ui.ArrayLabel;

public abstract class LabelSelectorAbstract {
	public LabelSelectorAbstract() {}
	
	public abstract ArrayLabel getLabel(VisualArray a);
	
	@Override
	public String toString()
	{
		return "Unnamed";
	}
	
	public abstract boolean matches(ArrayLabel l);
	
}
