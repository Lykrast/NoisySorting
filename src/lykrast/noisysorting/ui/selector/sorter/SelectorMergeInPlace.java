package lykrast.noisysorting.ui.selector.sorter;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterMergeInPlace;

public class SelectorMergeInPlace extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterMergeInPlace(a);
	}
	
	@Override
	public String toString()
	{
		return "In-Place Merge Sort";
	}

}