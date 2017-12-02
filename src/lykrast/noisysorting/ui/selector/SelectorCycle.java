package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterCycle;

public class SelectorCycle extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterCycle(a);
	}
	
	@Override
	public String toString()
	{
		return "Cycle Sort";
	}

}
