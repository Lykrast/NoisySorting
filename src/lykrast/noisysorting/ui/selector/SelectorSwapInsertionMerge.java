package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterSwapInsertionMerge;

public class SelectorSwapInsertionMerge extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterSwapInsertionMerge(a);
	}
	
	@Override
	public String toString()
	{
		return "Swap Insertion Merge Sort";
	}

}
