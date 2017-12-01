package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterCounting;

public class SelectorCounting extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterCounting(a);
	}
	
	@Override
	public String toString()
	{
		return "Counting Sort";
	}

}
