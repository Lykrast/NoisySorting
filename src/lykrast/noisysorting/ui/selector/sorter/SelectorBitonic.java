package lykrast.noisysorting.ui.selector.sorter;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterBitonic;

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
