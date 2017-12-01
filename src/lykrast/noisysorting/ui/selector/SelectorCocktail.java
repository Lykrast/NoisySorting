package lykrast.noisysorting.ui.selector;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;
import lykrast.noisysorting.sorting.SorterCocktail;

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
