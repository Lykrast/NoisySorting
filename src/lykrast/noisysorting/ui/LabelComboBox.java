package lykrast.noisysorting.ui;

import javax.swing.JComboBox;

import lykrast.noisysorting.ui.selector.label.*;

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
		addItem(new LabelSelectorHoopsGrey());
		addItem(new LabelSelectorHoopsColor());
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
