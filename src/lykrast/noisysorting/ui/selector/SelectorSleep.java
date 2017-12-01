package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterSleep;

public class SelectorSleep extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterSleep(a);
	}
	
	@Override
	public String toString()
	{
		return "Sleep Sort";
	}

}
