package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterStooge;

public class SelectorStooge extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterStooge(a);
	}
	
	@Override
	public String toString()
	{
		return "Stooge Sort";
	}

}
