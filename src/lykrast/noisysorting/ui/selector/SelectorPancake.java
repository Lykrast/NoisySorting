package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.sorting.VisualArray;
import lykrast.noisysorting.sorting.sort.SorterAbstract;
import lykrast.noisysorting.sorting.sort.SorterPancake;

public class SelectorPancake extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterPancake(a);
	}
	
	@Override
	public String toString()
	{
		return "Pancake Sort";
	}

}
