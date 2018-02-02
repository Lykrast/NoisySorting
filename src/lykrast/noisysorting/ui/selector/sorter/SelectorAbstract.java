package lykrast.noisysorting.ui.selector.sorter;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;

public abstract class SelectorAbstract implements Comparable<SelectorAbstract> {
	public SelectorAbstract() {}
	
	public abstract SorterAbstract getSorter(VisualArray a);
	
	@Override
	public String toString()
	{
		return "Unnamed Sort";
	}
	
	public String description()
	{
		return "No description found.";
	}

	@Override
	public int compareTo(SelectorAbstract arg0)
	{
		return toString().compareTo(arg0.toString());
	}
	
}
