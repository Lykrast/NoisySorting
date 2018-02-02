package lykrast.noisysorting.ui.selector.sorter;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterJumpDown;

public class SelectorJumpDown extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterJumpDown(a);
	}
	
	@Override
	public String toString()
	{
		return "Jump-Down Sort";
	}

}
