package lykrast.noisysorting.ui;

import java.util.ArrayList;
import java.util.List;

import lykrast.noisysorting.ui.selector.SelectorAbstract;
import lykrast.noisysorting.ui.selector.SelectorMerge;
import lykrast.noisysorting.ui.selector.SelectorMerge3Way;
import lykrast.noisysorting.ui.selector.SelectorMergeBottomUp;
import lykrast.noisysorting.ui.selector.SelectorStrand;

public class SortListMerge extends SortList {
	private static final long serialVersionUID = 1L;
	private static SelectorAbstract[] sorts = new SelectorAbstract[0];
	
	static
	{
		List<SelectorAbstract> list = new ArrayList<>();
		list.add(new SelectorMerge());
		list.add(new SelectorMergeBottomUp());
		list.add(new SelectorMerge3Way());
		list.add(new SelectorStrand());
		
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
