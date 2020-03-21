package lykrast.noisysorting.ui.selector.sorter;

import java.util.function.Function;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;

public class Selector extends SelectorAbstract {
	private String name, description;
	private Function<VisualArray, SorterAbstract> sorter;

	public Selector(String name, Function<VisualArray, SorterAbstract> sorter) {
		this(name, sorter, "?", "No description found.");
	}

	public Selector(String name, Function<VisualArray, SorterAbstract> sorter, String time, String description) {
		this.name = name;
		this.sorter = sorter;
		this.description = "Time: " + time + "\n" + description;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public String description() {
		return description;
	}

	@Override
	public SorterAbstract getSorter(VisualArray a) {
		return sorter.apply(a);
	}

}
