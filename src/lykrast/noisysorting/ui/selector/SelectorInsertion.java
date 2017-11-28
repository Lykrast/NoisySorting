package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.sorting.VisualArray;
import lykrast.noisysorting.sorting.sort.SorterAbstract;
import lykrast.noisysorting.sorting.sort.SorterInsertion;

public class SelectorInsertion extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterInsertion(a);
	}
	
	@Override
	public String toString()
	{
		return "Insertion Sort";
	}

}
