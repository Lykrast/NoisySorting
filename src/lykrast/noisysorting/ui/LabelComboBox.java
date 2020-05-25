package lykrast.noisysorting.ui;

import javax.swing.JComboBox;

import lykrast.noisysorting.ui.arraylabel.*;
import lykrast.noisysorting.ui.selector.label.LabelSelector;
import lykrast.noisysorting.ui.selector.label.LabelSelectorAbstract;

public class LabelComboBox extends JComboBox<LabelSelectorAbstract> {
	private static final long serialVersionUID = 1L;
	
	public LabelComboBox()
	{
		addItem(new LabelSelector("Vertical Bars", ArrayLabelBarsVertical::new, ArrayLabelBarsVertical.class));
		addItem(new LabelSelector("Horizontal Bars", ArrayLabelBarsHorizontal::new, ArrayLabelBarsHorizontal.class));
		addItem(new LabelSelector("Dots", ArrayLabelDots::new, ArrayLabelDots.class));
		addItem(new LabelSelector("Curve", ArrayLabelCurve::new, ArrayLabelCurve.class));
		addItem(new LabelSelector("Greyscale Circle", ArrayLabelCircleGrey::new, ArrayLabelCircleGrey.class));
		addItem(new LabelSelector("Color Circle", ArrayLabelCircleColor::new, ArrayLabelCircleColor.class));
		addItem(new LabelSelector("Greyscale Hoops", ArrayLabelHoopsGrey::new, ArrayLabelHoopsGrey.class));
		addItem(new LabelSelector("Color Hoops", ArrayLabelHoopsColor::new, ArrayLabelHoopsColor.class));
		addItem(new LabelSelector("Rainbow", ArrayLabelRainbow::new, ArrayLabelRainbow.class));
		addItem(new LabelSelector("Conch", ArrayLabelConch::new, ArrayLabelConch.class));
		addItem(new LabelSelector("Disparity Loop", ArrayLabelDisparityLoop::new, ArrayLabelDisparityLoop.class));
		addItem(new LabelSelector("Disparity Hoops", ArrayLabelDisparityHoops::new, ArrayLabelDisparityHoops.class));
		addItem(new LabelSelector("Mosaic", ArrayLabelMosaic::new, ArrayLabelMosaic.class));
		addItem(new LabelSelector("Numbered Mosaic", ArrayLabelMosaicValue::new, ArrayLabelMosaicValue.class));
		setSelectedIndex(0);
	}
	
	public LabelSelectorAbstract getSelected()
	{
		return getItemAt(getSelectedIndex());
	}

}
