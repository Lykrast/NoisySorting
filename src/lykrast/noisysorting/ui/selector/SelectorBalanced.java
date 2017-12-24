package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterBalanced;

public class SelectorBalanced extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterBalanced(a);
	}
	
	@Override
	public String toString()
	{
		return "Balanced Sort";
	}

}
