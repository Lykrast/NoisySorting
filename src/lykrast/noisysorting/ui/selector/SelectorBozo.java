package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.sorting.VisualArray;
import lykrast.noisysorting.sorting.sort.SorterAbstract;
import lykrast.noisysorting.sorting.sort.SorterBozo;

public class SelectorBozo extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterBozo(a);
	}
	
	@Override
	public String toString()
	{
		return "Bozo Sort";
	}

}
