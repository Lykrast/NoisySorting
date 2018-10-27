package lykrast.noisysorting.ui.selector.sorter;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterArraysObject;

public class SelectorArraysObject extends SelectorAbstract {
	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterArraysObject(a);
	}
	
	@Override
	public String toString()
	{
		return "Arrays.sort() (Object, legacy)";
	}

}
