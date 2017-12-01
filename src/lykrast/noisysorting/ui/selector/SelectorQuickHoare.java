package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterQuickHoare;

public class SelectorQuickHoare extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterQuickHoare(a);
	}
	
	@Override
	public String toString()
	{
		return "Quicksort (Hoare partition scheme)";
	}

}
