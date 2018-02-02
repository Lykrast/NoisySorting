package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterAmericanFlag;

public class SelectorAmericanFlag extends SelectorAbstract {
	private static final int RADIX = 4;

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterAmericanFlag(a, RADIX);
	}
	
	@Override
	public String toString()
	{
		return "American Flag Sort";
	}

}
