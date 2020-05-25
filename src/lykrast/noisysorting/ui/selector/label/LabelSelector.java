package lykrast.noisysorting.ui.selector.label;

import java.util.function.Function;
import java.util.function.Predicate;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.ui.arraylabel.ArrayLabel;

public class LabelSelector extends LabelSelectorAbstract {
	private String name;
	private Function<VisualArray, ArrayLabel> generator;
	private Predicate<ArrayLabel> matcher;

	public LabelSelector(String name, Function<VisualArray, ArrayLabel> generator, Predicate<ArrayLabel> matcher) {
		this.name = name;
		this.generator = generator;
		this.matcher = matcher;
	}

	public LabelSelector(String name, Function<VisualArray, ArrayLabel> generator, Class<? extends ArrayLabel> clazz) {
		this(name, generator, clazz::isInstance);
	}

	@Override
	public ArrayLabel getLabel(VisualArray a) {
		return generator.apply(a);
	}

	@Override
	public boolean matches(ArrayLabel l) {
		return matcher.test(l);
	}
	
	@Override
	public String toString() {
		return name;
	}

}
