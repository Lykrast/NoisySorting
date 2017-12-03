package lykrast.noisysorting.ui;

import javax.swing.JComboBox;

import lykrast.noisysorting.ui.selector.LabelSelectorAbstract;
import lykrast.noisysorting.ui.selector.LabelSelectorBarsHorizontal;
import lykrast.noisysorting.ui.selector.LabelSelectorBarsVertical;
import lykrast.noisysorting.ui.selector.LabelSelectorCircleColor;
import lykrast.noisysorting.ui.selector.LabelSelectorCircleGrey;
import lykrast.noisysorting.ui.selector.LabelSelectorConch;
import lykrast.noisysorting.ui.selector.LabelSelectorCurve;
import lykrast.noisysorting.ui.selector.LabelSelectorDots;
import lykrast.noisysorting.ui.selector.LabelSelectorMosaic;

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
		setSelectedIndex(0);
	}
	
	public LabelSelectorAbstract getSelected()
	{
		return getItemAt(getSelectedIndex());
	}

}
