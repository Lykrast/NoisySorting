package lykrast.noisysorting.ui.sortlist;

import java.util.ArrayList;
import java.util.List;

import lykrast.noisysorting.sorting.*;
import lykrast.noisysorting.ui.selector.sorter.Selector;
import lykrast.noisysorting.ui.selector.sorter.SelectorAbstract;

public class SortListMerge extends SortList {
	private static final long serialVersionUID = 1L;
	private static SelectorAbstract[] sorts = new SelectorAbstract[0];
	
	static
	{
		List<SelectorAbstract> list = new ArrayList<>();
		list.add(new Selector("Merge Sort", SorterMerge::new, "O(nlogn)", "A sort by John von Neumann. Recursively sorts both halves of the list, then merges them using a buffer array in memory."));
		list.add(new Selector("Bottom-Up Merge Sort", SorterMergeBottomUp::new, "O(nlogn)", "Splits the list into sublists of size 2, then iteratively merges them 2 by 2 until only one remains."));
		list.add(new Selector("3-Way Merge Sort", SorterMerge3Way::new, "O(nlogn)", "A variant of Merge Sort that splits lists into 3 instead of 2."));
		list.add(new Selector("Naive In-Place Merge Sort", SorterMergeInPlaceNaive::new, "O(n^2)", "A variant of Merge Sort that merges sublists without the use of an external buffer using a naive algorithm."));
		list.add(new Selector("In-Place Merge Sort", SorterMergeInPlace::new, "?", "A variant of Merge Sort by Jyrki Katajainen, Tomi Pasanen and Jukka Teuhola. Merges sublists without the use of an external buffer."));
		list.add(new Selector("Strand Sort", SorterStrand::new, "O(n^2)", "Creates sublists by finding elements in increasing order, then merges them. More suited to structures similar to linked lists."));
		list.add(new Selector("Swap Insertion Merge Sort (*)", SorterSwapInsertionMerge::new, "?", "A custom sort."));
		list.add(new Selector("Weaved Merge Sort (*)", SorterMergeWeaved::new, "O(nlogn)", "A custom variant of Merge Sort that partitions the list using modulo operations."));
		list.add(new Selector("Bottom-Up Weaved Merge Sort (*)", SorterMergeWeavedBottomUp::new, "O(nlogn)", "A custom variant of Bottom-Up Merge Sort that partitions the list using modulo operations."));
		
		//Collections.sort(list);
		sorts = list.toArray(sorts);
	}
	
	public SortListMerge()
	{
		super(sorts);
	}
	
	@Override
	public String toString()
	{
		return "Merge";
	}

}
