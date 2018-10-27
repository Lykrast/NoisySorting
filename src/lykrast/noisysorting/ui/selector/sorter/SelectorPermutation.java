package lykrast.noisysorting.ui.selector.sorter;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterPermutation;

public class SelectorPermutation extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterPermutation(a);
	}
	
	@Override
	public String toString()
	{
		return "Permutation Sort";
	}

}
