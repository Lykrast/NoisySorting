package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterSelection;

public class SelectorSelection extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterSelection(a);
	}
	
	@Override
	public String toString()
	{
		return "Selection Sort";
	}

}
