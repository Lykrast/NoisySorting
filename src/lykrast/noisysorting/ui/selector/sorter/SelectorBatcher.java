package lykrast.noisysorting.ui.selector.sorter;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterBatcher;

public class SelectorBatcher extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterBatcher(a);
	}
	
	@Override
	public String toString()
	{
		return "Batcher Odd-Even Mergesort";
	}

}
