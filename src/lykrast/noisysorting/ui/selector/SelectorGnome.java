package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterGnome;

public class SelectorGnome extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterGnome(a);
	}
	
	@Override
	public String toString()
	{
		return "Gnome Sort";
	}

}
