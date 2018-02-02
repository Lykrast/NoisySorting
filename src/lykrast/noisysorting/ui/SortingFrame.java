package lykrast.noisysorting.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import lykrast.noisysorting.array.VisualArray;
import lykrast.noisysorting.ui.arraylabel.ArrayLabel;
import lykrast.noisysorting.ui.arraylabel.ArrayLabelBarsVertical;
import lykrast.noisysorting.ui.selector.label.LabelSelectorAbstract;

public class SortingFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private VisualArray array;
	private ArrayLabel arrayLabel;
	private OptionPanel options;
	private LabelComboBox labelCombo;

	public SortingFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setLocationRelativeTo(null);
		setTitle("Noisy Sorting");
		
		array = new VisualArray(20);
		arrayLabel = new ArrayLabelBarsVertical(array);
		options = new OptionPanel(this, array);
		
		add(arrayLabel, BorderLayout.CENTER);
		add(options, BorderLayout.EAST);
	}
	
	public void newArray(VisualArray a)
	{
		array = a;
		remove(arrayLabel);
		arrayLabel = labelCombo.getSelected().getLabel(array);
		add(arrayLabel);
		options.setArray(array);
		
		revalidate();
		repaint();
	}
	
	public void refreshDisplay()
	{
		LabelSelectorAbstract selector = labelCombo.getSelected();
		if (!selector.matches(arrayLabel))
		{
			remove(arrayLabel);
			arrayLabel = labelCombo.getSelected().getLabel(array);
			add(arrayLabel);
			
			revalidate();
			repaint();
		}
	}
	
	public void setLabelComboBox(LabelComboBox l)
	{
		labelCombo = l;
	}

}
