package lykrast.noisysorting.ui.sortlist;

import java.util.ArrayList;
import java.util.List;

import lykrast.noisysorting.ui.selector.sorter.SelectorAbstract;
import lykrast.noisysorting.ui.selector.sorter.SelectorMerge;
import lykrast.noisysorting.ui.selector.sorter.SelectorMerge3Way;
import lykrast.noisysorting.ui.selector.sorter.SelectorMergeBottomUp;
import lykrast.noisysorting.ui.selector.sorter.SelectorMergeInPlace;
import lykrast.noisysorting.ui.selector.sorter.SelectorMergeInPlaceNaive;
import lykrast.noisysorting.ui.selector.sorter.SelectorStrand;
import lykrast.noisysorting.ui.selector.sorter.SelectorSwapInsertionMerge;

public class SortListMerge extends SortList {
	private static final long serialVersionUID = 1L;
	private static SelectorAbstract[] sorts = new SelectorAbstract[0];
	
	static
	{
		List<SelectorAbstract> list = new ArrayList<>();
		list.add(new SelectorMerge());
		list.add(new SelectorMergeBottomUp());
		list.add(new SelectorMerge3Way());
		list.add(new SelectorMergeInPlaceNaive());
		list.add(new SelectorMergeInPlace());
		list.add(new SelectorStrand());
		list.add(new SelectorSwapInsertionMerge());
		
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
