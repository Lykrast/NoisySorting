package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.sorting.VisualArray;
import lykrast.noisysorting.sorting.sort.SorterAbstract;
import lykrast.noisysorting.sorting.sort.SorterBogo;

public class SelectorBogo extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterBogo(a);
	}
	
	@Override
	public String toString()
	{
		return "Bogo Sort";
	}

}
