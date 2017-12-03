package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterCombInsertion;

public class SelectorCombInsertion extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterCombInsertion(a);
	}
	
	@Override
	public String toString()
	{
		return "Comb-Insertion Sort";
	}
	
	@Override
	public String description()
	{
		return "Comb Sort that switches to Insertion Sort once the gap falls below 9.";
	}

}
