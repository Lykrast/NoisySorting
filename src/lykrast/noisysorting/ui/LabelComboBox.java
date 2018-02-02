package lykrast.noisysorting.ui;

import javax.swing.JComboBox;

import lykrast.noisysorting.ui.selector.label.LabelSelectorAbstract;
import lykrast.noisysorting.ui.selector.label.LabelSelectorBarsHorizontal;
import lykrast.noisysorting.ui.selector.label.LabelSelectorBarsVertical;
import lykrast.noisysorting.ui.selector.label.LabelSelectorCircleColor;
import lykrast.noisysorting.ui.selector.label.LabelSelectorCircleGrey;
import lykrast.noisysorting.ui.selector.label.LabelSelectorConch;
import lykrast.noisysorting.ui.selector.label.LabelSelectorCurve;
import lykrast.noisysorting.ui.selector.label.LabelSelectorDots;
import lykrast.noisysorting.ui.selector.label.LabelSelectorMosaic;
import lykrast.noisysorting.ui.selector.label.LabelSelectorMosaicValue;

public class LabelComboBox extends JComboBox<LabelSelectorAbstract> {
	private static final long serialVersionUID = 1L;
	
	public LabelComboBox()
	{
		addItem(new LabelSelectorBarsVertical());
		addItem(new LabelSelectorBarsHorizontal());
		addItem(new LabelSelectorDots());
		addItem(new LabelSelectorCurve());
		addItem(new LabelSelectorCircleGrey());
		addItem(new LabelSelectorCircleColor());
		addItem(new LabelSelectorConch());
		addItem(new LabelSelectorMosaic());
		addItem(new LabelSelectorMosaicValue());
		setSelectedIndex(0);
	}
	
	public LabelSelectorAbstract getSelected()
	{
		return getItemAt(getSelectedIndex());
	}

}
