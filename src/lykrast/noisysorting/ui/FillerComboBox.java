package lykrast.noisysorting.ui;

import javax.swing.JComboBox;

import lykrast.noisysorting.array.FillerAbstract;
import lykrast.noisysorting.array.FillerAppended;
import lykrast.noisysorting.array.FillerCubic;
import lykrast.noisysorting.array.FillerCubicRoot;
import lykrast.noisysorting.array.FillerLinear;
import lykrast.noisysorting.array.FillerN2Equal;
import lykrast.noisysorting.array.FillerRandomGaussian;
import lykrast.noisysorting.array.FillerRandomUniform;
import lykrast.noisysorting.array.FillerSortedRuns;
import lykrast.noisysorting.array.FillerWeaved;

public class FillerComboBox extends JComboBox<FillerAbstract> {
	private static final long serialVersionUID = 1L;

	public FillerComboBox() {
		addItem(new FillerLinear());
		addItem(new FillerCubic());
		addItem(new FillerCubicRoot());
		addItem(new FillerN2Equal());
		addItem(new FillerRandomUniform());
		addItem(new FillerRandomGaussian());
		addItem(new FillerAppended());
		addItem(new FillerSortedRuns());
		addItem(new FillerWeaved());
		setSelectedIndex(0);
	}

	public FillerAbstract getSelected() {
		return getItemAt(getSelectedIndex());
	}

}
