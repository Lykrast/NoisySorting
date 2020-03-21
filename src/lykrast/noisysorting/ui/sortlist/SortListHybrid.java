package lykrast.noisysorting.ui.sortlist;

import java.util.ArrayList;
import java.util.List;

import lykrast.noisysorting.sorting.*;
import lykrast.noisysorting.ui.selector.sorter.Selector;
import lykrast.noisysorting.ui.selector.sorter.SelectorAbstract;

public class SortListHybrid extends SortList {
	private static final long serialVersionUID = 1L;
	private static SelectorAbstract[] sorts = new SelectorAbstract[0];
	
	static
	{
		List<SelectorAbstract> list = new ArrayList<>();
		list.add(new Selector("Comb-Insertion Sort", SorterCombInsertion::new));
		list.add(new Selector("Timsort", SorterTim::new));
		list.add(new Selector("J Sort", SorterJ::new));
		list.add(new Selector("WikiSort - No cache", SorterWiki::new));
		list.add(new Selector("GrailSort - No cache", SorterGrail::new));
		list.add(new Selector("Arrays.sort() (Primitive)", SorterArraysPrimitive::new));
		//list.add(new Selector("Arrays.sort() (Object, legacy)", SorterArraysObject::new));
		
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
