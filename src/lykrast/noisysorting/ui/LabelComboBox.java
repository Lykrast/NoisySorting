package lykrast.noisysorting.ui;

import javax.swing.JComboBox;

import lykrast.noisysorting.ui.selector.LabelSelectorAbstract;
import lykrast.noisysorting.ui.selector.LabelSelectorBarsVertical;
import lykrast.noisysorting.ui.selector.LabelSelectorCircle;

public class LabelComboBox extends JComboBox<LabelSelectorAbstract> {
	private static final long serialVersionUID = 1L;
	
	public LabelComboBox()
	{
		addItem(new LabelSelectorBarsVertical());
		addItem(new LabelSelectorCircle());
		setSelectedIndex(0);
	}
	
	public LabelSelectorAbstract getSelected()
	{
		return getItemAt(getSelectedIndex());
	}

}
