package lykrast.noisysorting.ui.sortlist;

import java.util.ArrayList;
import java.util.List;

import lykrast.noisysorting.sorting.*;
import lykrast.noisysorting.ui.selector.sorter.Selector;
import lykrast.noisysorting.ui.selector.sorter.SelectorAbstract;

public class SortListSelection extends SortList {
	private static final long serialVersionUID = 1L;
	private static SelectorAbstract[] sorts = new SelectorAbstract[0];
	
	static
	{
		List<SelectorAbstract> list = new ArrayList<>();
		list.add(new Selector("Selection Sort", SorterSelection::new, "O(n^2)", "Finds the largest element in the list, then puts it at the end of list. Repeats until the whole list is sorted."));
		list.add(new Selector("Double Selection Sort", SorterSelectionDouble::new, "O(n^2)", "A variant of Selection Sort that also finds the smallest element and places it correctly as well."));
		list.add(new Selector("Bingo Sort", SorterBingo::new, "O(n^2)", "A variant of Selection Sort that places all elements with the same value at the same time when they are the largest."));
		list.add(new Selector("Recursive Selection Sort", SorterSelectionRecursive::new, "O(n^2) ?", "A variant of Selection Sort by Rezaul Chowdhury and Pramod Ganapathi. Recursively splits the list in half, separating small elements from large ones, then sorts the partitions with Selection Sort once they are small enough."));
		list.add(new Selector("Max Heap Sort", SorterHeapMax::new, "O(nlogn)", "A sort by J. W. J. Williams improved by R. W. Floyd. Turns the list into a max heap, then repeatedly extracts the maximum element until the whole list is sorted, fixing the heap in the process."));
		list.add(new Selector("Min Heap Sort", SorterHeapMin::new, "O(nlogn)", "A sort by J. W. J. Williams improved by R. W. Floyd. Turns the list into a min heap, then repeatedly extracts the minimum element until the whole list is sorted, fixing the heap in the process. The list is then reversed to be in correct order."));
		list.add(new Selector("Ternary Heap Sort", SorterHeapTernary::new, "O(nlogn) ?", "A variant of Max Heap Sort that makes a ternary max heap, which has a different structure."));
		list.add(new Selector("Weak Heap Sort", SorterHeapWeak::new, "O(nlogn) ?", "A variant of Max Heap Sort that makes a weak max heap, which has a different structure but requires extra memory."));
		list.add(new Selector("Smoothsort", SorterSmooth::new, "O(nlogn)", "A sort by Edsger Dijkstra. Turns the list into a tree structure and uses it to extract elements to be put in their correct place."));
		list.add(new Selector("Cycle Sort", SorterCycle::new, "O(n^2)", "Finds the correct position of each element by counting how many elements are larger than it, then swaps it into that position."));
		
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
