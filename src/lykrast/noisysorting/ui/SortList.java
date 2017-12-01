package lykrast.noisysorting.ui;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.ui.selector.SelectorAbstract;

public abstract class SortList extends JList<SelectorAbstract> {
	private static final long serialVersionUID = 1L;
	
	protected SortList(SelectorAbstract[] selectors)
	{
		super(selectors);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setSelectedIndex(0);
	}
	
	public SorterAbstract getSorter(VisualArray array)
	{
		return getSelectedValue().getSorter(array);
	}
	
	@Override
	public String toString()
	{
		return "Undefined";
	}

}
