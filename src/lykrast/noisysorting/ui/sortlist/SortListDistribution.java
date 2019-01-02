package lykrast.noisysorting.ui.sortlist;

import java.util.ArrayList;
import java.util.List;

import lykrast.noisysorting.ui.selector.sorter.*;

public class SortListDistribution extends SortList {
	private static final long serialVersionUID = 1L;
	private static SelectorAbstract[] sorts = new SelectorAbstract[0];
	
	static
	{
		List<SelectorAbstract> list = new ArrayList<>();
		list.add(new SelectorCounting());
		list.add(new SelectorRadixLSD());
		list.add(new SelectorRadixMSD());
		list.add(new SelectorRadixLSDInPlace());
		list.add(new SelectorRadixMSDInPlace());
		list.add(new SelectorAmericanFlag());
		list.add(new SelectorShuffle());
		list.add(new SelectorBead());
		
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
