package lykrast.noisysorting.ui;

import java.util.ArrayList;
import java.util.List;

import lykrast.noisysorting.ui.selector.SelectorAbstract;
import lykrast.noisysorting.ui.selector.SelectorBogo;
import lykrast.noisysorting.ui.selector.SelectorBozo;
import lykrast.noisysorting.ui.selector.SelectorBubble;
import lykrast.noisysorting.ui.selector.SelectorCocktail;
import lykrast.noisysorting.ui.selector.SelectorComb;
import lykrast.noisysorting.ui.selector.SelectorDoubleSlowInsertion;
import lykrast.noisysorting.ui.selector.SelectorGnome;
import lykrast.noisysorting.ui.selector.SelectorMergeExchange;
import lykrast.noisysorting.ui.selector.SelectorOddEven;
import lykrast.noisysorting.ui.selector.SelectorQuickHoare;
import lykrast.noisysorting.ui.selector.SelectorQuickLomuto;
import lykrast.noisysorting.ui.selector.SelectorSlow;
import lykrast.noisysorting.ui.selector.SelectorStooge;

public class SortListExchange extends SortList {
	private static final long serialVersionUID = 1L;
	private static SelectorAbstract[] sorts = new SelectorAbstract[0];
	
	static
	{
		List<SelectorAbstract> list = new ArrayList<>();
		list.add(new SelectorBubble());
		list.add(new SelectorCocktail());
		list.add(new SelectorOddEven());
		list.add(new SelectorComb());
		list.add(new SelectorMergeExchange());
		list.add(new SelectorGnome());
		list.add(new SelectorQuickLomuto());
		list.add(new SelectorQuickHoare());
		list.add(new SelectorSlow());
		list.add(new SelectorStooge());
		list.add(new SelectorBogo());
		list.add(new SelectorBozo());
		list.add(new SelectorDoubleSlowInsertion());
		
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
