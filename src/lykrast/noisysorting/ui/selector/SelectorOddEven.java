package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.sorting.VisualArray;
import lykrast.noisysorting.sorting.sort.SorterAbstract;
import lykrast.noisysorting.sorting.sort.SorterOddEven;

public class SelectorOddEven extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterOddEven(a);
	}
	
	@Override
	public String toString()
	{
		return "Odd-Even Sort";
	}

}
