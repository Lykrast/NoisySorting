package lykrast.noisysorting.ui.sortlist;

import java.util.ArrayList;
import java.util.List;

import lykrast.noisysorting.sorting.*;
import lykrast.noisysorting.ui.selector.sorter.*;

public class SortListDistribution extends SortList {
	private static final long serialVersionUID = 1L;
	private static SelectorAbstract[] sorts = new SelectorAbstract[0];
	
	static
	{
		List<SelectorAbstract> list = new ArrayList<>();
		int[] radixes = {2, 4, 8, 10};
		list.add(new Selector("Counting Sort", SorterCounting::new));
		for (int r : radixes)	list.add(new Selector("Radix Sort (LSD) - Radix " + r, a -> new SorterRadixLSD(a, r)));
		for (int r : radixes)	list.add(new Selector("Radix Sort (MSD) - Radix " + r, a -> new SorterRadixMSD(a, r)));
		for (int r : radixes)	list.add(new Selector("In-place Radix Sort (LSD) - Radix " + r, a -> new SorterRadixLSDInPlace(a, r)));
		list.add(new Selector("In-place Radix Sort (MSD)", SorterRadixMSDInPlace::new));
		for (int r : radixes) list.add(new Selector("American Flag Sort - Radix " + r, a -> new SorterAmericanFlag(a, r)));
		list.add(new Selector("Shuffle Sort", SorterShuffle::new));
		list.add(new Selector("Bead Sort", SorterBead::new));
		list.add(new Selector("Flash Sort", SorterFlash::new));
		
		//Collections.sort(list);
		sorts = list.toArray(sorts);
	}
	
	public SortListDistribution()
	{
		super(sorts);
	}
	
	@Override
	public String toString()
	{
		return "Distribution";
	}

}
