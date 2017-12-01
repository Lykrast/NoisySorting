package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterMerge;

public class SelectorMerge extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterMerge(a);
	}
	
	@Override
	public String toString()
	{
		return "Merge Sort";
	}

}
