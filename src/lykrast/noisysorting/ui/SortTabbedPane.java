package lykrast.noisysorting.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTabbedPane;

import lykrast.noisysorting.sorting.VisualArray;
import lykrast.noisysorting.sorting.sort.SorterAbstract;

public class SortTabbedPane extends JTabbedPane {
	private static final long serialVersionUID = 1L;
	private static final List<SortScrollPane> SORTS = new ArrayList<>();
	
	static
	{
		SORTS.add(new SortScrollPane(new SortListExchange()));
		SORTS.add(new SortScrollPane(new SortListSelection()));
		SORTS.add(new SortScrollPane(new SortListInsertion()));
		SORTS.add(new SortScrollPane(new SortListMerge()));
		SORTS.add(new SortScrollPane(new SortListDistribution()));
		SORTS.add(new SortScrollPane(new SortListHybrid()));
		SORTS.add(new SortScrollPane(new SortListMisc()));
	}
	
	public SortTabbedPane()
	{
		super();
		//setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		this.getSelectedComponent();
		
		for (SortScrollPane l : SORTS)
		{
			addTab(l.toString(), l);
		}
	}
	
	public SorterAbstract getSorter(VisualArray a)
	{
		return ((SortScrollPane) getSelectedComponent()).getList().getSorter(a);
	}

}
