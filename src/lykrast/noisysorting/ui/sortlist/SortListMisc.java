package lykrast.noisysorting.ui.sortlist;

import java.util.ArrayList;
import java.util.List;

import lykrast.noisysorting.ui.selector.sorter.SelectorAbstract;
import lykrast.noisysorting.ui.selector.sorter.SelectorBalanced;
import lykrast.noisysorting.ui.selector.sorter.SelectorBatcher;
import lykrast.noisysorting.ui.selector.sorter.SelectorBitonic;
import lykrast.noisysorting.ui.selector.sorter.SelectorBoseNelson;
import lykrast.noisysorting.ui.selector.sorter.SelectorHibbard;
import lykrast.noisysorting.ui.selector.sorter.SelectorPancake;
import lykrast.noisysorting.ui.selector.sorter.SelectorSleep;

public class SortListMisc extends SortList {
	private static final long serialVersionUID = 1L;
	private static SelectorAbstract[] sorts = new SelectorAbstract[0];
	
	static
	{
		List<SelectorAbstract> list = new ArrayList<>();
		list.add(new SelectorBoseNelson());
		list.add(new SelectorHibbard());
		list.add(new SelectorBitonic());
		list.add(new SelectorBatcher());
		list.add(new SelectorBalanced());
		list.add(new SelectorSleep());
		list.add(new SelectorPancake());
		
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
