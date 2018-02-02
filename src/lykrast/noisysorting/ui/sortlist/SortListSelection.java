package lykrast.noisysorting.ui.sortlist;

import java.util.ArrayList;
import java.util.List;

import lykrast.noisysorting.ui.selector.sorter.SelectorAbstract;
import lykrast.noisysorting.ui.selector.sorter.SelectorBingo;
import lykrast.noisysorting.ui.selector.sorter.SelectorCycle;
import lykrast.noisysorting.ui.selector.sorter.SelectorHeap;
import lykrast.noisysorting.ui.selector.sorter.SelectorSelection;
import lykrast.noisysorting.ui.selector.sorter.SelectorSelectionDouble;
import lykrast.noisysorting.ui.selector.sorter.SelectorSelectionRecursive;

public class SortListSelection extends SortList {
	private static final long serialVersionUID = 1L;
	private static SelectorAbstract[] sorts = new SelectorAbstract[0];
	
	static
	{
		List<SelectorAbstract> list = new ArrayList<>();
		list.add(new SelectorSelection());
		list.add(new SelectorSelectionDouble());
		list.add(new SelectorBingo());
		list.add(new SelectorSelectionRecursive());
		list.add(new SelectorHeap());
		list.add(new SelectorCycle());
		
		//Collections.sort(list);
		sorts = list.toArray(sorts);
	}
	
	public SortListSelection()
	{
		super(sorts);
	}
	
	@Override
	public String toString()
	{
		return "Selection";
	}

}
