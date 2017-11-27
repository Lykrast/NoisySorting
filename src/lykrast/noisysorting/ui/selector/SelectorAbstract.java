package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.sorting.VisualArray;
import lykrast.noisysorting.sorting.sort.SorterAbstract;

public abstract class SelectorAbstract implements Comparable {
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
	public int compareTo(Object arg0)
	{
		if (!(arg0 instanceof SelectorAbstract)) return 0;
		else return toString().compareTo(arg0.toString());
	}
	
}
