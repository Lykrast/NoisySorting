package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterRadixLSD;

public class SelectorRadixLSD extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterRadixLSD(a);
	}
	
	@Override
	public String toString()
	{
		return "Radix Sort (LSD)";
	}

}
