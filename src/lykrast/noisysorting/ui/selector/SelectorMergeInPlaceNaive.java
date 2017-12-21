package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterMergeInPlaceNaive;

public class SelectorMergeInPlaceNaive extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterMergeInPlaceNaive(a);
	}
	
	@Override
	public String toString()
	{
		return "Naive In-Place Merge Sort";
	}

}
