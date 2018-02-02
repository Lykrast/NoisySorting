package lykrast.noisysorting.ui.selector.sorter;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterBubbleRecursive;

public class SelectorBubbleRecursive extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterBubbleRecursive(a);
	}
	
	@Override
	public String toString()
	{
		return "Recursive Bubble Sort";
	}

}
