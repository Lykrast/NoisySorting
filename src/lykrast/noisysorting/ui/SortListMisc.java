package lykrast.noisysorting.ui;

import java.util.ArrayList;
import java.util.List;

import lykrast.noisysorting.ui.selector.SelectorAbstract;
import lykrast.noisysorting.ui.selector.SelectorBatcher;
import lykrast.noisysorting.ui.selector.SelectorBitonic;
import lykrast.noisysorting.ui.selector.SelectorPancake;
import lykrast.noisysorting.ui.selector.SelectorSleep;

public class SortListMisc extends SortList {
	private static final long serialVersionUID = 1L;
	private static SelectorAbstract[] sorts = new SelectorAbstract[0];
	
	static
	{
		List<SelectorAbstract> list = new ArrayList<>();
		list.add(new SelectorBitonic());
		list.add(new SelectorBatcher());
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
