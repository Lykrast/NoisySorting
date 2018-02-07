package lykrast.noisysorting.ui.sortlist;

import java.util.ArrayList;
import java.util.List;

import lykrast.noisysorting.ui.selector.sorter.SelectorAbstract;
import lykrast.noisysorting.ui.selector.sorter.SelectorArraysObject;
import lykrast.noisysorting.ui.selector.sorter.SelectorArraysPrimitive;
import lykrast.noisysorting.ui.selector.sorter.SelectorCombInsertion;
import lykrast.noisysorting.ui.selector.sorter.SelectorJ;
import lykrast.noisysorting.ui.selector.sorter.SelectorTim;

public class SortListHybrid extends SortList {
	private static final long serialVersionUID = 1L;
	private static SelectorAbstract[] sorts = new SelectorAbstract[0];
	
	static
	{
		List<SelectorAbstract> list = new ArrayList<>();
		list.add(new SelectorCombInsertion());
		list.add(new SelectorTim());
		list.add(new SelectorJ());
		list.add(new SelectorArraysPrimitive());
		//list.add(new SelectorArraysObject());
		
		//Collections.sort(list);
		sorts = list.toArray(sorts);
	}
	
	public SortListHybrid()
	{
		super(sorts);
	}
	
	@Override
	public String toString()
	{
		return "Hybrid";
	}

}
