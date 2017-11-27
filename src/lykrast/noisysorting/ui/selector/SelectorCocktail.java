package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.sorting.VisualArray;
import lykrast.noisysorting.sorting.sort.SorterAbstract;
import lykrast.noisysorting.sorting.sort.SorterCocktail;

public class SelectorCocktail extends SelectorAbstract {

	@Override
	public SorterAbstract getSorter(VisualArray a)
	{
		return new SorterCocktail(a);
	}
	
	@Override
	public String toString()
	{
		return "Cocktail Shaker Sort";
	}

}
