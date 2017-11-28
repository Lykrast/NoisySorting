package lykrast.noisysorting.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

import lykrast.noisysorting.sorting.VisualArray;
import lykrast.noisysorting.sorting.sort.SorterAbstract;
import lykrast.noisysorting.ui.selector.SelectorAbstract;
import lykrast.noisysorting.ui.selector.SelectorBogo;
import lykrast.noisysorting.ui.selector.SelectorBozo;
import lykrast.noisysorting.ui.selector.SelectorBubble;
import lykrast.noisysorting.ui.selector.SelectorCocktail;
import lykrast.noisysorting.ui.selector.SelectorComb;
import lykrast.noisysorting.ui.selector.SelectorGnome;
import lykrast.noisysorting.ui.selector.SelectorInsertion;
import lykrast.noisysorting.ui.selector.SelectorJ;
import lykrast.noisysorting.ui.selector.SelectorMerge;
import lykrast.noisysorting.ui.selector.SelectorOddEven;
import lykrast.noisysorting.ui.selector.SelectorSelection;
import lykrast.noisysorting.ui.selector.SelectorShuffle;
import lykrast.noisysorting.ui.selector.SelectorSleep;
import lykrast.noisysorting.ui.selector.SelectorSlow;
import lykrast.noisysorting.ui.selector.SelectorStooge;
import lykrast.noisysorting.ui.selector.SelectorStrand;

public class SortList extends JList<SelectorAbstract> {
	private static SelectorAbstract[] sorts = new SelectorAbstract[0];
	
	static
	{
		List<SelectorAbstract> list = new ArrayList<>();
		//Basic
		list.add(new SelectorSelection());
		list.add(new SelectorInsertion());
		list.add(new SelectorBubble());
		//Bubble cousins
		list.add(new SelectorCocktail());
		list.add(new SelectorOddEven());
		list.add(new SelectorComb());
		//Gnome
		list.add(new SelectorGnome());
		//Merging
		list.add(new SelectorMerge());
		list.add(new SelectorStrand());
		//Buckets
		list.add(new SelectorShuffle());
		//Hybrid
		list.add(new SelectorJ());
		//Slow
		list.add(new SelectorSleep());
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
