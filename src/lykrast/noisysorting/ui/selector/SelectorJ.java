package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.sorting.VisualArray;
import lykrast.noisysorting.sorting.sort.SorterAbstract;
import lykrast.noisysorting.sorting.sort.SorterJ;

public class SelectorJ extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterJ(a);
	}
	
	@Override
	public String toString()
	{
		return "J Sort";
	}
	
	@Override
	public String description()
	{
		return "Uses Strand Sort on small arrays and Shuffle Sort on big ones.";
	}

}
