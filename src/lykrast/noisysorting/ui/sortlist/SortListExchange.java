package lykrast.noisysorting.ui.sortlist;

import java.util.ArrayList;
import java.util.List;

import lykrast.noisysorting.ui.selector.sorter.SelectorAbstract;
import lykrast.noisysorting.ui.selector.sorter.SelectorBogo;
import lykrast.noisysorting.ui.selector.sorter.SelectorBozo;
import lykrast.noisysorting.ui.selector.sorter.SelectorBubble;
import lykrast.noisysorting.ui.selector.sorter.SelectorBubbleRecursive;
import lykrast.noisysorting.ui.selector.sorter.SelectorCocktail;
import lykrast.noisysorting.ui.selector.sorter.SelectorComb;
import lykrast.noisysorting.ui.selector.sorter.SelectorDoubleSlowInsertion;
import lykrast.noisysorting.ui.selector.sorter.SelectorGnome;
import lykrast.noisysorting.ui.selector.sorter.SelectorJumpDown;
import lykrast.noisysorting.ui.selector.sorter.SelectorMergeExchange;
import lykrast.noisysorting.ui.selector.sorter.SelectorOddEven;
import lykrast.noisysorting.ui.selector.sorter.SelectorPermutation;
import lykrast.noisysorting.ui.selector.sorter.SelectorQuickHoare;
import lykrast.noisysorting.ui.selector.sorter.SelectorQuickLomuto;
import lykrast.noisysorting.ui.selector.sorter.SelectorSlow;
import lykrast.noisysorting.ui.selector.sorter.SelectorStooge;

public class SortListExchange extends SortList {
	private static final long serialVersionUID = 1L;
	private static SelectorAbstract[] sorts = new SelectorAbstract[0];
	
	static
	{
		List<SelectorAbstract> list = new ArrayList<>();
		list.add(new SelectorBubble());
		list.add(new SelectorCocktail());
		list.add(new SelectorJumpDown());
		list.add(new SelectorOddEven());
		list.add(new SelectorComb());
		list.add(new SelectorBubbleRecursive());
		list.add(new SelectorMergeExchange());
		list.add(new SelectorGnome());
		list.add(new SelectorQuickLomuto());
		list.add(new SelectorQuickHoare());
		list.add(new SelectorSlow());
		list.add(new SelectorStooge());
		list.add(new SelectorBogo());
		list.add(new SelectorBozo());
		list.add(new SelectorDoubleSlowInsertion());
		list.add(new SelectorPermutation());
		
		//Collections.sort(list);
		sorts = list.toArray(sorts);
	}
	
	public SortListExchange()
	{
		super(sorts);
	}
	
	@Override
	public String toString()
	{
		return "Exchange";
	}

}
