package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.sorting.VisualArray;
import lykrast.noisysorting.sorting.sort.SorterAbstract;
import lykrast.noisysorting.sorting.sort.SorterQuickLomuto;

public class SelectorQuickLomuto extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterQuickLomuto(a);
	}
	
	@Override
	public String toString()
	{
		return "Quicksort (Lomuto partition scheme)";
	}

}
