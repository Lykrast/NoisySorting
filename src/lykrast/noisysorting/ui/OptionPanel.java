package lykrast.noisysorting.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import lykrast.noisysorting.array.FillerAbstract;
import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.sorting.SorterAbstract;

public class OptionPanel extends JPanel implements ActionListener, ChangeListener {
	private static final long serialVersionUID = 1L;
	private SortingFrame parent;
	private VisualArray array;
	private JButton sort, shuffle, reset;
	private JButton reverse, nearShuffle;
	private FillerComboBox fillerCombo;
	private LabelComboBox labelCombo;
	private InstrumentComboBox instrumentCombo;
	private JSlider speedSlider, sizeSlider, volumeSlider;
	private SortTabbedPane sortTabs;
	private SorterAbstract sorter;
	private ArraySoundMaker soundMaker;
	
	private static final int DEFAULT_VOLUME = 50;

	public OptionPanel(SortingFrame frame, VisualArray a) {
		parent = frame;
		array = a;
		setLayout(new BorderLayout());
		// List
		sortTabs = new SortTabbedPane();
		add(sortTabs, BorderLayout.CENTER);
		// Soundmaker
		soundMaker = new ArraySoundMaker(array, DEFAULT_VOLUME);
		// Options
		JPanel options = new JPanel();
		options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));
		// Buttons
		// Top
		JPanel buttonsTop = new JPanel();
		buttonsTop.setLayout(new FlowLayout());

		sort = new JButton("Sort");
		shuffle = new JButton("Shuffle");
		reset = new JButton("Reset");

		sort.addActionListener(this);
		shuffle.addActionListener(this);
		reset.addActionListener(this);

		buttonsTop.add(sort);
		buttonsTop.add(shuffle);
		buttonsTop.add(reset);

		options.add(buttonsTop);
		// Bottom
		JPanel buttonsBot = new JPanel();
		buttonsBot.setLayout(new FlowLayout());

		reverse = new JButton("Reverse");
		nearShuffle = new JButton("Near Shuffle");

		reverse.addActionListener(this);
		nearShuffle.addActionListener(this);

		buttonsBot.add(reverse);
		buttonsBot.add(nearShuffle);

		options.add(buttonsBot);
		// Speed
		speedSlider = new JSlider(0, 1000, 100);
		speedSlider.setMajorTickSpacing(100);
		speedSlider.setMinorTickSpacing(10);
		speedSlider.setPaintTicks(true);
		speedSlider.setPaintLabels(true);
		speedSlider.addChangeListener(this);
		speedSlider.setBorder(BorderFactory.createTitledBorder("Action delay (ms)"));
		options.add(speedSlider);

		// Combo boxes
		// Filler
		fillerCombo = new FillerComboBox();
		fillerCombo.setBorder(BorderFactory.createTitledBorder("Fill mode"));

		options.add(fillerCombo);
		// Label
		labelCombo = new LabelComboBox();
		labelCombo.setBorder(BorderFactory.createTitledBorder("Display mode"));
		parent.setLabelComboBox(labelCombo);

		options.add(labelCombo);

		// Array Size
		sizeSlider = new JSlider(0, 1000, 20);
		sizeSlider.setMajorTickSpacing(100);
		sizeSlider.setMinorTickSpacing(10);
		sizeSlider.setPaintTicks(true);
		sizeSlider.setPaintLabels(true);
		sizeSlider.setBorder(BorderFactory.createTitledBorder("Size of array"));
		options.add(sizeSlider);

		// Sound stuff
		// Instruments
		instrumentCombo = soundMaker.getInstrumentBox();
		instrumentCombo.setBorder(BorderFactory.createTitledBorder("Instrument"));

		options.add(instrumentCombo);

		// Sound Volume
		volumeSlider = new JSlider(0, 100, DEFAULT_VOLUME);
		volumeSlider.setMajorTickSpacing(10);
		volumeSlider.setMinorTickSpacing(1);
		volumeSlider.setPaintTicks(true);
		volumeSlider.setPaintLabels(true);
		volumeSlider.addChangeListener(this);
		volumeSlider.setBorder(BorderFactory.createTitledBorder("Sound volume"));
		options.add(volumeSlider);

		add(options, BorderLayout.NORTH);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == sort) {
			cancelSort();
			sorter = sortTabs.getSorter(array);
			sorter.execute();
		}
		else if (event.getSource() == shuffle) {
			cancelSort();
			array.shuffle();
		}
		else if (event.getSource() == reset) {
			cancelSort();
			int newsize = Math.max(2, sizeSlider.getValue());
			FillerAbstract newfiller = fillerCombo.getSelected();
			if (array.getSize() != newsize) parent.newArray(new VisualArray(newsize, newfiller));
			else {
				if (array.getFiller() != newfiller) array.setFiller(newfiller);
				else array.fill();

				parent.refreshDisplay();
			}
		}
		else if (event.getSource() == reverse) {
			cancelSort();
			array.reverse();
		}
		else if (event.getSource() == nearShuffle) {
			cancelSort();
			array.shuffleNear();
		}

	}

	@Override
	public void stateChanged(ChangeEvent event) {
		if (event.getSource() == speedSlider) {
			SorterAbstract.setTimeout(speedSlider.getValue());
		}
		else if (event.getSource() == volumeSlider) {
			soundMaker.setVolume(volumeSlider.getValue());
		}
	}

	public void setArray(VisualArray a) {
		array = a;
		if (soundMaker != null) soundMaker.cleanup();
		soundMaker = new ArraySoundMaker(array, volumeSlider.getValue());
		instrumentCombo.changeSoundMaker(soundMaker);
	}

	private void cancelSort() {
		if (sorter != null) {
			sorter.cancel(true);
		}
	}

}
