package lykrast.noisysorting.ui.selector.sorter;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterSelectionRecursive;

public class SelectorSelectionRecursive extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterSelectionRecursive(a);
	}
	
	@Override
	public String toString()
	{
		return "Recursive Selection Sort";
	}

}
