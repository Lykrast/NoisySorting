package lykrast.noisysorting.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

import lykrast.noisysorting.sorting.VisualArray;
import lykrast.noisysorting.sorting.sort.SorterAbstract;
import lykrast.noisysorting.ui.selector.*;

public class SortList extends JList<SelectorAbstract> {
	private static SelectorAbstract[] sorts = new SelectorAbstract[0];
	
	static
	{
		List<SelectorAbstract> list = new ArrayList<>();
		//Basic
		list.add(new SelectorSelection());
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
		//Slow
		list.add(new SelectorSlow());
		list.add(new SelectorStooge());
		
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
