package lykrast.noisysorting.ui.selector.sorter;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterJ;

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
