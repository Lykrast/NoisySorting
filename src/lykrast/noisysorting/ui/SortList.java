package lykrast.noisysorting.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

import lykrast.noisysorting.sorting.VisualArray;
import lykrast.noisysorting.sorting.sort.SorterAbstract;
import lykrast.noisysorting.ui.selector.*;

public class SortList extends JList<SelectorAbstract> {
	private static final long serialVersionUID = 1L;
	private static SelectorAbstract[] sorts = new SelectorAbstract[0];
	
	static
	{
		List<SelectorAbstract> list = new ArrayList<>();
		//Basic
		list.add(new SelectorSelection());
		list.add(new SelectorInsertion());
		list.add(new SelectorBubble());
		//Selection
		list.add(new SelectorHeap());
		//Bubble cousins
		list.add(new SelectorCocktail());
		list.add(new SelectorOddEven());
		list.add(new SelectorComb());
		//Gnome
		list.add(new SelectorGnome());
		//Quick
		list.add(new SelectorQuickLomuto());
		list.add(new SelectorQuickHoare());
		//Merging
		list.add(new SelectorMerge());
		list.add(new SelectorStrand());
		//Buckets
		list.add(new SelectorShuffle());
		//Hybrid
		list.add(new SelectorJ());
		//Slow
		list.add(new SelectorSleep());
		list.add(new SelectorPancake());
		list.add(new SelectorSlow());
		list.add(new SelectorStooge());
		list.add(new SelectorBogo());
		list.add(new SelectorBozo());
		
		//Collections.sort(list);
		sorts = list.toArray(sorts);
	}
	
	public SortList()
	{
		super(sorts);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setSelectedIndex(0);
	}
	
	public SorterAbstract getSorter(VisualArray array)
	{
		return getSelectedValue().getSorter(array);
	}

}
