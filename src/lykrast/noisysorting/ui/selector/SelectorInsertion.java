package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterInsertion;

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
