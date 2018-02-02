package lykrast.noisysorting.ui.selector.sorter;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterSelectionDouble;

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
