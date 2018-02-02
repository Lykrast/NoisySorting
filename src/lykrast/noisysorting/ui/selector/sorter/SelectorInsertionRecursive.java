package lykrast.noisysorting.ui.selector.sorter;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterInsertionRecursive;

public class SelectorInsertionRecursive extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterInsertionRecursive(a);
	}
	
	@Override
	public String toString()
	{
		return "Recursive Insertion Sort";
	}

}
