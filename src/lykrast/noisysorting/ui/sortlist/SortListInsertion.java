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
		list.add(new Selector("Insertion Sort", SorterInsertion::new, "O(n^2)", "Removes elements from the list one by one, inserting them into a growing sorted list at the start until the whole list is sorted."));
		list.add(new Selector("Recursive Insertion Sort", SorterInsertionRecursive::new, "O(n^log3) ?", "A variant of Insertion Sort by Rezaul Chowdhury and Pramod Ganapathi. Sorts small sections of the list, then merges them using Insertion Sort."));
		list.add(new Selector("Shellsort", SorterShell::new, "O(nlogn)", "A sort by Donald Shell, with a gap sequence by Ciura. Sorts pairs of elements far apart from each other, then progressively reduces the gap until the whole list is sorted."));
		
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
