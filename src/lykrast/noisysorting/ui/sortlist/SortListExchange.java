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
		list.add(new Selector("Bubble Sort", SorterBubble::new));
		list.add(new Selector("Cocktail Shaker Sort", SorterCocktail::new));
		list.add(new Selector("Jump-Down Sort", SorterJumpDown::new));
		list.add(new Selector("Odd-Even Sort", SorterOddEven::new));
		list.add(new Selector("Comb Sort", SorterComb::new));
		list.add(new Selector("Recursive Bubble Sort", SorterBubbleRecursive::new));
		list.add(new Selector("Merge Exchange Sort", SorterMergeExchange::new));
		list.add(new Selector("Gnome Sort", SorterGnome::new));
		list.add(new Selector("Quicksort (Lomuto partition scheme)", SorterQuickLomuto::new));
		list.add(new Selector("Quicksort (Hoare partition scheme)", SorterQuickHoare::new));
		list.add(new Selector("Slow Sort", SorterSlow::new));
		list.add(new Selector("Stooge Sort", SorterStooge::new));
		list.add(new Selector("Bogo Sort", SorterBogo::new));
		list.add(new Selector("Bozo Sort", SorterBozo::new));
		list.add(new Selector("Double Slow Insertion Sort", SorterDoubleSlowInsertion::new));
		list.add(new Selector("Permutation Sort", SorterPermutation::new));
		
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
