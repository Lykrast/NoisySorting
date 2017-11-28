package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.sorting.VisualArray;
import lykrast.noisysorting.sorting.sort.SorterAbstract;
import lykrast.noisysorting.sorting.sort.SorterQuickHoare;

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
