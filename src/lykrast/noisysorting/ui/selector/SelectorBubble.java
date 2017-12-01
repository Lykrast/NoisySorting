package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterBubble;

public class SelectorBubble extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterBubble(a);
	}
	
	@Override
	public String toString()
	{
		return "Bubble Sort";
	}

}
