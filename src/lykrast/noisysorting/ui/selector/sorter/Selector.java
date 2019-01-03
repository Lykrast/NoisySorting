package lykrast.noisysorting.ui.selector.sorter;

import java.util.function.Function;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;

public class Selector extends SelectorAbstract {
	private String name;
	private Function<VisualArray, SorterAbstract> sorter;
	
	public Selector(String name, Function<VisualArray, SorterAbstract> sorter) {
		this.name = name;
		this.sorter = sorter;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public SorterAbstract getSorter(VisualArray a) {
		return sorter.apply(a);
	}

}
