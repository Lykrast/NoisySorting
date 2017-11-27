package lykrast.noisysorting.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import lykrast.noisysorting.sorting.VisualArray;

public class SortingFrame extends JFrame {
	private VisualArray array;
	private ArrayLabel arrayLabel;
	private ArraySoundMaker soundMaker;
	private OptionPanel options;

	public SortingFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setLocationRelativeTo(null);
		setName("Noisy Sorting");
		
		array = new VisualArray(20);
		arrayLabel = new ArrayLabel(array);
		options = new OptionPanel(this, array);
		soundMaker = new ArraySoundMaker(array);
		
		add(arrayLabel, BorderLayout.CENTER);
		add(options, BorderLayout.EAST);
	}
	
	public void newArray(int size)
	{
		array = new VisualArray(size);
		remove(arrayLabel);
		arrayLabel = new ArrayLabel(array);
		add(arrayLabel);
		options.setArray(array);
		soundMaker = new ArraySoundMaker(array);
		
		revalidate();
		repaint();
	}

}
