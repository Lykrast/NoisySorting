package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterRadixMSD;

public class SelectorRadixMSD extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterRadixMSD(a);
	}
	
	@Override
	public String toString()
	{
		return "Radix Sort (MSD)";
	}

}
