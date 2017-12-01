package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterOddEven;

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
