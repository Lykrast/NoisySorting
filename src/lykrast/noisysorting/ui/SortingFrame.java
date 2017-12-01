package lykrast.noisysorting.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import lykrast.noisysorting.array.VisualArray;

public class SortingFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private VisualArray array;
	private ArrayLabel arrayLabel;
	private OptionPanel options;

	public SortingFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setLocationRelativeTo(null);
		setTitle("Noisy Sorting");
		
		array = new VisualArray(20);
		arrayLabel = new ArrayLabel(array);
		options = new OptionPanel(this, array);
		
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
		
		revalidate();
		repaint();
	}

}
