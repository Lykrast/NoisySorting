package lykrast.noisysorting.ui.selector.sorter;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterArraysPrimitive;

public class SelectorArraysPrimitive extends SelectorAbstract {
	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterArraysPrimitive(a);
	}
	
	@Override
	public String toString()
	{
		return "Arrays.sort() (Primitive)";
	}

}
