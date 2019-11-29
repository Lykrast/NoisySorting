package lykrast.noisysorting.ui.selector.label;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.ui.arraylabel.ArrayLabel;
import lykrast.noisysorting.ui.arraylabel.ArrayLabelDisparityHoops;

public class LabelSelectorDisparityHoops extends LabelSelectorAbstract {

	@Override
	public ArrayLabel getLabel(VisualArray a) {
		return new ArrayLabelDisparityHoops(a);
	}

	@Override
	public String toString() {
		return "Disparity Hoops";
	}

	@Override
	public boolean matches(ArrayLabel l) {
		return l instanceof ArrayLabelDisparityHoops;
	}

}
