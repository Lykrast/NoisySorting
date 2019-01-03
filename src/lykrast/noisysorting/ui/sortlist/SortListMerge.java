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
		list.add(new Selector("Merge Sort", SorterMerge::new));
		list.add(new Selector("Bottom-Up Merge Sort", SorterMergeBottomUp::new));
		list.add(new Selector("3-Way Merge Sort", SorterMerge3Way::new));
		list.add(new Selector("Naive In-Place Merge Sort", SorterMergeInPlaceNaive::new));
		list.add(new Selector("In-Place Merge Sort", SorterMergeInPlace::new));
		list.add(new Selector("Strand Sort", SorterStrand::new));
		list.add(new Selector("Swap Insertion Merge Sort", SorterSwapInsertionMerge::new));
		
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
