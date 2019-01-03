package lykrast.noisysorting.ui.sortlist;

import java.util.ArrayList;
import java.util.List;

import lykrast.noisysorting.sorting.*;
import lykrast.noisysorting.ui.selector.sorter.*;

public class SortListDistribution extends SortList {
	private static final long serialVersionUID = 1L;
	private static SelectorAbstract[] sorts = new SelectorAbstract[0];
	
	private static final int RADIX = 4;
	
	static
	{
		List<SelectorAbstract> list = new ArrayList<>();
		list.add(new Selector("Counting Sort", SorterCounting::new));
		list.add(new Selector("Radix Sort (LSD)", a -> new SorterRadixLSD(a, RADIX)));
		list.add(new Selector("Radix Sort (MSD)", a -> new SorterRadixMSD(a, RADIX)));
		list.add(new Selector("In-place Radix Sort (LSD)", a -> new SorterRadixLSDInPlace(a, RADIX)));
		list.add(new Selector("In-place Radix Sort (MSD)", SorterRadixMSDInPlace::new));
		list.add(new Selector("American Flag Sort", a -> new SorterAmericanFlag(a, RADIX)));
		list.add(new Selector("Shuffle Sort", SorterShuffle::new));
		list.add(new Selector("Bead Sort", SorterBead::new));
		
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
