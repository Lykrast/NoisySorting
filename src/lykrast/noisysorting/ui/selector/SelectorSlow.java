package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterSlow;

public class SelectorSlow extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterSlow(a);
	}
	
	@Override
	public String toString()
	{
		return "Slow Sort";
	}

}
