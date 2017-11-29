package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.sorting.VisualArray;
import lykrast.noisysorting.sorting.sort.SorterAbstract;
import lykrast.noisysorting.sorting.sort.SorterBitonic;

public class SelectorBitonic extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterBitonic(a);
	}
	
	@Override
	public String toString()
	{
		return "Bitonic Sort";
	}

}
