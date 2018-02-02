package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterHibbard;

public class SelectorHibbard extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterHibbard(a);
	}
	
	@Override
	public String toString()
	{
		return "Hibbard Sort";
	}

}