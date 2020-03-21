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
		list.add(new Selector("Comb-Insertion Sort", SorterCombInsertion::new, "O(n^2)", "Starts with Comb Sort, then switches to Insertion Sort when the gap gets small enough."));
		list.add(new Selector("Timsort", SorterTim::new, "O(nlogn)", "A sort by Tim Peters used in Python. Finds sequences that are already sorted (or creates them if needed), then merges them along the way."));
		list.add(new Selector("J Sort", SorterJ::new, "?", "A sort by John Cohen. Uses Shuffle Sort on large sublists and Strand Sort on smaller ones."));
		list.add(new Selector("WikiSort - No cache", SorterWiki::new, "O(nlogn)", "An implementation of Pok-Son Kim and Arne Kutzner's Block Merge Sort by Mike McFadden (BonzaiThePenguin)."));
		list.add(new Selector("GrailSort - No cache", SorterGrail::new, "O(nlogn)", "An implementation of Pok-Son Kim and Arne Kutzner's Block Merge Sort by the late Andrey Astrelin (Mrrl)."));
		list.add(new Selector("Arrays.sort() (Primitive)", SorterArraysPrimitive::new, "?", "The algorigthm used by OpenJDK 6 for primitives."));
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
