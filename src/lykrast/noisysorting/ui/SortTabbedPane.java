package lykrast.noisysorting.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTabbedPane;
import javax.swing.event.ListSelectionListener;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.ui.selector.sorter.SelectorAbstract;
import lykrast.noisysorting.ui.sortlist.SortListDistribution;
import lykrast.noisysorting.ui.sortlist.SortListExchange;
import lykrast.noisysorting.ui.sortlist.SortListHybrid;
import lykrast.noisysorting.ui.sortlist.SortListInsertion;
import lykrast.noisysorting.ui.sortlist.SortListMerge;
import lykrast.noisysorting.ui.sortlist.SortListMisc;
import lykrast.noisysorting.ui.sortlist.SortListSelection;

public class SortTabbedPane extends JTabbedPane {
	private static final long serialVersionUID = 1L;
	private static final List<SortScrollPane> SORTS = new ArrayList<>();

	static {
		SORTS.add(new SortScrollPane(new SortListExchange()));
		SORTS.add(new SortScrollPane(new SortListSelection()));
		SORTS.add(new SortScrollPane(new SortListInsertion()));
		SORTS.add(new SortScrollPane(new SortListMerge()));
		SORTS.add(new SortScrollPane(new SortListDistribution()));
		SORTS.add(new SortScrollPane(new SortListHybrid()));
		SORTS.add(new SortScrollPane(new SortListMisc()));
	}

	public SortTabbedPane() {
		super();
		//setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		this.getSelectedComponent();

		for (SortScrollPane l : SORTS) {
			addTab(l.toString(), l);
		}
	}
	
	public void addListenerToLists(ListSelectionListener listener) {
		for (SortScrollPane s : SORTS) {
			s.getList().addListSelectionListener(listener);
		}
	}

	public SorterAbstract getSorter(VisualArray a) {
		return ((SortScrollPane) getSelectedComponent()).getList().getSorter(a);
	}

	public SelectorAbstract getSelector() {
		return ((SortScrollPane) getSelectedComponent()).getList().getSelectedValue();
	}

}
