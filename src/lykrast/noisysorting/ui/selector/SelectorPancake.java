package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterPancake;

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
