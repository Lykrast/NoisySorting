package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterShuffle;

public class SelectorShuffle extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterShuffle(a);
	}
	
	@Override
	public String toString()
	{
		return "Shuffle Sort";
	}

}
