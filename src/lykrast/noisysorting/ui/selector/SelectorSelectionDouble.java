package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.sorting.VisualArray;
import lykrast.noisysorting.sorting.sort.SorterAbstract;
import lykrast.noisysorting.sorting.sort.SorterSelectionDouble;

public class SelectorSelectionDouble extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterSelectionDouble(a);
	}
	
	@Override
	public String toString()
	{
		return "Double Selection Sort";
	}

}
