package lykrast.noisysorting.ui.sortlist;

import java.util.ArrayList;
import java.util.List;

import lykrast.noisysorting.sorting.*;
import lykrast.noisysorting.ui.selector.sorter.Selector;
import lykrast.noisysorting.ui.selector.sorter.SelectorAbstract;

public class SortListMisc extends SortList {
	private static final long serialVersionUID = 1L;
	private static SelectorAbstract[] sorts = new SelectorAbstract[0];
	
	static
	{
		List<SelectorAbstract> list = new ArrayList<>();
		list.add(new Selector("Bolse-Nelson Sort", SorterBoseNelson::new));
		list.add(new Selector("Hibbard Sort", SorterHibbard::new));
		list.add(new Selector("Bitonic Sort", SorterBitonic::new));
		list.add(new Selector("Batcher Odd-Even Mergesort", SorterBatcher::new));
		list.add(new Selector("Balanced Sort", SorterBalanced::new));
		list.add(new Selector("Sleep Sort", SorterSleep::new));
		list.add(new Selector("Pancake Sort", SorterPancake::new));
		list.add(new Selector("Fisher–Yates shuffle", SorterFisherYatesShuffle::new));
		
		//Collections.sort(list);
		sorts = list.toArray(sorts);
	}
	
	public SortListMisc()
	{
		super(sorts);
	}
	
	@Override
	public String toString()
	{
		return "Misc";
	}

}
