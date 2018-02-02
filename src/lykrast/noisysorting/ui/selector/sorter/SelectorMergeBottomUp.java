package lykrast.noisysorting.ui.selector.sorter;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterMergeBottomUp;

public class SelectorMergeBottomUp extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterMergeBottomUp(a);
	}
	
	@Override
	public String toString()
	{
		return "Bottom-Up Merge Sort";
	}

}
