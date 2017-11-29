package lykrast.noisysorting.ui;

import java.util.ArrayList;
import java.util.List;

import lykrast.noisysorting.ui.selector.SelectorAbstract;
import lykrast.noisysorting.ui.selector.SelectorHeap;
import lykrast.noisysorting.ui.selector.SelectorSelection;

public class SortListSelection extends SortList {
	private static final long serialVersionUID = 1L;
	private static SelectorAbstract[] sorts = new SelectorAbstract[0];
	
	static
	{
		List<SelectorAbstract> list = new ArrayList<>();
		list.add(new SelectorSelection());
		list.add(new SelectorHeap());
		
		//Collections.sort(list);
		sorts = list.toArray(sorts);
	}
	
	public SortListSelection()
	{
		super(sorts);
	}
	
	@Override
	public String toString()
	{
		return "Selection";
	}

}