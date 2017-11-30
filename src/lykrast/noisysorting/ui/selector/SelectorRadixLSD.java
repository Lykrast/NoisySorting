package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.sorting.VisualArray;
import lykrast.noisysorting.sorting.sort.SorterAbstract;
import lykrast.noisysorting.sorting.sort.SorterRadixLSD;

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
