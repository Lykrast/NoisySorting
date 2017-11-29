package lykrast.noisysorting.ui;

import java.util.ArrayList;
import java.util.List;

import lykrast.noisysorting.ui.selector.SelectorAbstract;
import lykrast.noisysorting.ui.selector.SelectorInsertion;

public class SortListInsertion extends SortList {
	private static final long serialVersionUID = 1L;
	private static SelectorAbstract[] sorts = new SelectorAbstract[0];
	
	static
	{
		List<SelectorAbstract> list = new ArrayList<>();
		list.add(new SelectorInsertion());
		
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
