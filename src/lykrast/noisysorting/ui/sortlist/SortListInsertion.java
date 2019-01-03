package lykrast.noisysorting.ui.sortlist;

import java.util.ArrayList;
import java.util.List;

import lykrast.noisysorting.sorting.*;
import lykrast.noisysorting.ui.selector.sorter.Selector;
import lykrast.noisysorting.ui.selector.sorter.SelectorAbstract;

public class SortListInsertion extends SortList {
	private static final long serialVersionUID = 1L;
	private static SelectorAbstract[] sorts = new SelectorAbstract[0];
	
	static
	{
		List<SelectorAbstract> list = new ArrayList<>();
		list.add(new Selector("Insertion Sort", SorterInsertion::new));
		list.add(new Selector("Recursive Insertion Sort", SorterInsertionRecursive::new));
		list.add(new Selector("Shellsort", SorterShell::new));
		
		//Collections.sort(list);
		sorts = list.toArray(sorts);
	}
	
	public SortListInsertion()
	{
		super(sorts);
	}
	
	@Override
	public String toString()
	{
		return "Insertion";
	}

}
