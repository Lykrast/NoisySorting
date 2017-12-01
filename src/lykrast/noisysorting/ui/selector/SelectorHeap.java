package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterHeap;

public class SelectorHeap extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterHeap(a);
	}
	
	@Override
	public String toString()
	{
		return "Heap Sort";
	}

}
