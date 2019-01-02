package lykrast.noisysorting.ui.selector.sorter;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterRadixLSDInPlace;

public class SelectorRadixLSDInPlace extends SelectorAbstract {
	private static final int RADIX = 4;

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterRadixLSDInPlace(a, RADIX);
	}
	
	@Override
	public String toString()
	{
		return "In-place Radix Sort (LSD)";
	}

}
