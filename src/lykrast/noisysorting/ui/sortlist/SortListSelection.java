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
		list.add(new Selector("Selection Sort", SorterSelection::new));
		list.add(new Selector("Double Selection Sort", SorterSelectionDouble::new));
		list.add(new Selector("Bingo Sort", SorterBingo::new));
		list.add(new Selector("Recursive Selection Sort", SorterSelectionRecursive::new));
		list.add(new Selector("Max Heap Sort", SorterHeapMax::new));
		list.add(new Selector("Min Heap Sort", SorterHeapMin::new));
		list.add(new Selector("Ternary Heap Sort", SorterHeapTernary::new));
		//TODO find weak heap sort
		list.add(new Selector("Smoothsort", SorterSmooth::new));
		list.add(new Selector("Cycle Sort", SorterCycle::new));
		
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
