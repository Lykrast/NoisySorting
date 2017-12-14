package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterDoubleSlowInsertion;

public class SelectorDoubleSlowInsertion extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterDoubleSlowInsertion(a);
	}
	
	@Override
	public String toString()
	{
		return "Double Slow Insertion Sort";
	}

}
