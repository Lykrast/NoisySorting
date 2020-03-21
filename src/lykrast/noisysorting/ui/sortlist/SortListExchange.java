package lykrast.noisysorting.ui.sortlist;

import java.util.ArrayList;
import java.util.List;

import lykrast.noisysorting.sorting.*;
import lykrast.noisysorting.ui.selector.sorter.Selector;
import lykrast.noisysorting.ui.selector.sorter.SelectorAbstract;

public class SortListExchange extends SortList {
	private static final long serialVersionUID = 1L;
	private static SelectorAbstract[] sorts = new SelectorAbstract[0];
	
	static
	{
		List<SelectorAbstract> list = new ArrayList<>();
		list.add(new Selector("Bubble Sort", SorterBubble::new, "O(n^2)", "Repeatedly steps through the list, comparing adjacent elements and swapping them if out of order, until the list is sorted."));
		list.add(new Selector("Cocktail Shaker Sort", SorterCocktail::new, "O(n^2)", "A variant of Bubble Sort that passes through the list alternatively from top to bottom then from bottom to top."));
		list.add(new Selector("Odd-Even Sort", SorterOddEven::new, "O(n^2)", "A variant of Bubble Sort that alternates between comparing odd-indexed pairs and even-indexed pairs."));
		list.add(new Selector("Comb Sort", SorterComb::new, "O(n^2)", "A variant of Bubble Sort by Wlodzimierz Dobosiewicz and Artur Borowy that compares and swaps elements that are further apart, then progressively shrink that gap."));
		list.add(new Selector("Jump-Down Sort", SorterJumpDown::new, "O(n^2)", "Steps through the list, comparing each element to the one in the last position and swapping them if out of order, then repeats until the whole list is sorted."));
		list.add(new Selector("Recursive Bubble Sort", SorterBubbleRecursive::new, "O(n^2) ?", "A variant of Bubble Sort by Rezaul Chowdhury and Pramod Ganapathi. Recursively splits the list in half, separating small elements from large ones, then sorts the partitions with Bubble Sort once they are small enough."));
		list.add(new Selector("Merge Exchange Sort", SorterMergeExchange::new, "?", "A sort by Donald Knuth from The Art of Computer Programming, vol. 3 : Sorting and Searching."));
		list.add(new Selector("Gnome Sort", SorterGnome::new, "O(n^2)", "A sort by Hamid Sarbazi-Azad similar to Insertion Sort. Looks at the current element and if it is out of order with the previous one, swap them and step back, otherwise step forward."));
		list.add(new Selector("Quicksort (Lomuto partition scheme)", SorterQuickLomuto::new, "O(nlogn) best", "Selects a pivot element then partitions the elements according to whether they are less than or greater than the pivot, then recursively sorts them.\nScheme by Nico Lomuto that scans the list with 1 index."));
		list.add(new Selector("Quicksort (Hoare partition scheme)", SorterQuickHoare::new, "O(nlogn) best", "Selects a pivot element then partitions the elements according to whether they are less than or greater than the pivot, then recursively sorts them.\nScheme by C.A.R. Hoare that uses 2 indices that go toward eachother."));
		list.add(new Selector("Quicksort 3 Way", SorterQuick3::new, "O(nlogn) best", "A variant of Quicksort that uses 3-way partitions."));
		list.add(new Selector("Slow Sort", SorterSlow::new, "O(n^3)", "A sort by Andrei Broder and Jorge Stolfi. Recursively sorts both halves, finds the maximum among them and places it at the end, then recursively sorts the remainder of the list."));
		list.add(new Selector("Stooge Sort", SorterStooge::new, "O(n^(log3/log1.5))", "Swaps the start and end value if they are out of order, then recursively sorts the first 2/3 of the list, then the last 2/3, then the first 2/3 again."));
		list.add(new Selector("Bogo Sort", SorterBogo::new, "O(n!) average", "Randomly shuffles the whole list until it is sorted."));
		list.add(new Selector("Bozo Sort", SorterBozo::new, "O(n!) average", "Swaps 2 random elements until the whole list is sorted."));
		list.add(new Selector("Permutation Sort", SorterPermutation::new, "O(n!)", "Iterates through all permutations of the list until one where the list is sorted is found."));
		list.add(new Selector("Circle Sort", SorterCircle::new, "?", "Compares the first and last element, swapping them if out of order, then the second element and second last, etc. Then splits the list in 2 and recurses until only one element is left. Then repeats for as long as swaps happen."));
		list.add(new Selector("Double Slow Insertion Sort (*)", SorterDoubleSlowInsertion::new, "?", "A custom sort."));
		
		//Collections.sort(list);
		sorts = list.toArray(sorts);
	}
	
	public SortListExchange()
	{
		super(sorts);
	}
	
	@Override
	public String toString()
	{
		return "Exchange";
	}

}
