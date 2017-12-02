package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterMerge3Way;

public class SelectorMerge3Way extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterMerge3Way(a);
	}
	
	@Override
	public String toString()
	{
		return "3-Way Merge Sort";
	}

}
