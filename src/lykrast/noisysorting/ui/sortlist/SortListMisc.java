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
		list.add(new Selector("Bolse-Nelson Sort", SorterBoseNelson::new, "?", "A sorting network created using the Bose-Nelson algorithm."));
		list.add(new Selector("Hibbard Sort", SorterHibbard::new, "?", "A sorting network created using the Hibbard algorithm."));
		list.add(new Selector("Bitonic Sort", SorterBitonic::new, "?", "A sorting network created using Batcher's bitonic algorithm."));
		list.add(new Selector("Batcher Odd-Even Mergesort", SorterBatcher::new, "?", "A sorting network created using Batcher's Odd-Even Merge algorithm."));
		list.add(new Selector("Balanced Sort", SorterBalanced::new, "?", "A sorting network created using an algorithm by M. Dowd, Y. Perl, M Saks, and L. Rudolph."));
		//TODO find something for the pairwise sorting network https://pdfs.semanticscholar.org/d663/dbbdb0e35258fbdbc0da91de97f06813e9a6.pdf
		list.add(new Selector("Sleep Sort", SorterSleep::new, "O(n*m) m largest value", "For each element simultaneously, waits an amount of time proportional to its value, then writes the values in order their wait expired. May not fully sort the array on fast time settings."));
		list.add(new Selector("Pancake Sort", SorterPancake::new, "O(n^2)", "Sorts the list as if it was a stack of pancake, with the only operation being flipping part of the stack with a spatula."));
		list.add(new Selector("Fisher–Yates shuffle", SorterFisherYatesShuffle::new, "O(n)", "Shuffles the list, but animated."));
		
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
