package lykrast.noisysorting.ui.sortlist;

import java.util.ArrayList;
import java.util.List;

import lykrast.noisysorting.ui.selector.sorter.SelectorAbstract;
import lykrast.noisysorting.ui.selector.sorter.SelectorInsertion;
import lykrast.noisysorting.ui.selector.sorter.SelectorShell;

public class SortListInsertion extends SortList {
	private static final long serialVersionUID = 1L;
	private static SelectorAbstract[] sorts = new SelectorAbstract[0];
	
	static
	{
		List<SelectorAbstract> list = new ArrayList<>();
		list.add(new SelectorInsertion());
		list.add(new SelectorShell());
		
		//Collections.sort(list);
		sorts = list.toArray(sorts);
	}
	
	public SortListInsertion()
	{
		super(sorts);
	}
	
	@Override
	public String toString()
	{
		return "Insertion";
	}

}
