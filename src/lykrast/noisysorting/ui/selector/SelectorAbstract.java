package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.sorting.VisualArray;
import lykrast.noisysorting.sorting.sort.SorterAbstract;

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
