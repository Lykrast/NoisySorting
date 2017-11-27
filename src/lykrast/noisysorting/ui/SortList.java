package lykrast.noisysorting.ui;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

import lykrast.noisysorting.sorting.VisualArray;
import lykrast.noisysorting.sorting.sort.SorterAbstract;
import lykrast.noisysorting.sorting.sort.SorterBubble;
import lykrast.noisysorting.sorting.sort.SorterCocktail;
import lykrast.noisysorting.sorting.sort.SorterComb;
import lykrast.noisysorting.sorting.sort.SorterGnome;
import lykrast.noisysorting.sorting.sort.SorterOddEven;
import lykrast.noisysorting.sorting.sort.SorterSelection;
import lykrast.noisysorting.sorting.sort.SorterSlow;
import lykrast.noisysorting.sorting.sort.SorterStooge;

public class SortList extends JList<String> {
	private static String[] sorts = {"Selection Sort", "Bubble Sort", "Cocktail Shaker Sort", "Odd-Even Sort", "Gnome Sort",
			"Comb Sort", "Slow Sort", "Stooge Sort"};
	
	public SortList()
	{
		super(sorts);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setSelectedIndex(0);
	}
	
	public SorterAbstract getSorter(VisualArray array)
	{		
		switch (getSelectedIndex())
		{
		case 0:
			return new SorterSelection(array);
		case 1:
			return new SorterBubble(array);
		case 2:
			return new SorterCocktail(array);
		case 3:
			return new SorterOddEven(array);
		case 4:
			return new SorterGnome(array);
		case 5:
			return new SorterComb(array);
		case 6:
			return new SorterSlow(array);
		case 7:
			return new SorterStooge(array);
		}
		
		return null;
	}

}
