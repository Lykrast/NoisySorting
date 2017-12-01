package lykrast.noisysorting.ui;

import javax.swing.JComboBox;

import lykrast.noisysorting.array.FillerAbstract;
import lykrast.noisysorting.array.FillerCubic;
import lykrast.noisysorting.array.FillerLinear;
import lykrast.noisysorting.array.FillerN2Equal;

public class FillerComboBox extends JComboBox<FillerAbstract> {
	private static final long serialVersionUID = 1L;
	
	public FillerComboBox()
	{
		addItem(new FillerLinear());
		addItem(new FillerCubic());
		addItem(new FillerN2Equal());
		setSelectedIndex(0);
	}

}
