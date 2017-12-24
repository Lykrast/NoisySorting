package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterBoseNelson;

public class SelectorBoseNelson extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterBoseNelson(a);
	}
	
	@Override
	public String toString()
	{
		return "Bolse-Nelson Sort";
	}

}
