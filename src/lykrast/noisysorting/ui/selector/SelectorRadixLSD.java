package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterRadixLSD;

public class SelectorRadixLSD extends SelectorAbstract {
	private static final int RADIX = 4;

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterRadixLSD(a, RADIX);
	}
	
	@Override
	public String toString()
	{
		return "Radix Sort (LSD)";
	}

}
