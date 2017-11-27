package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.sorting.VisualArray;
import lykrast.noisysorting.sorting.sort.SorterAbstract;
import lykrast.noisysorting.sorting.sort.SorterShuffle;

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
