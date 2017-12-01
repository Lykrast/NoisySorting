package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterQuickLomuto;

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
