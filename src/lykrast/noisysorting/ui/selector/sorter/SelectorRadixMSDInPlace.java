package lykrast.noisysorting.ui.selector.sorter;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterRadixMSDInPlace;

public class SelectorRadixMSDInPlace extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterRadixMSDInPlace(a);
	}
	
	@Override
	public String toString()
	{
		return "In-place Radix Sort (MSD)";
	}

}
