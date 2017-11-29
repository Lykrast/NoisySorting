package lykrast.noisysorting.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import lykrast.noisysorting.sorting.VisualArray;
import lykrast.noisysorting.sorting.sort.SorterAbstract;

public class OptionPanel extends JPanel implements ActionListener, ChangeListener {
	private static final long serialVersionUID = 1L;
	private SortingFrame parent;
	private VisualArray array;
	private JButton sort, shuffle, reverse, reset;
	private JSlider speedSlider, sizeSlider;
	private SortList sortList;
	private SorterAbstract sorter;
	
	public OptionPanel(SortingFrame frame, VisualArray a)
	{
		parent = frame;
		setArray(a);
		setLayout(new BorderLayout());
		//List
		sortList = new SortList();
		JScrollPane listScroll = new JScrollPane(sortList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(listScroll, BorderLayout.SOUTH);
		//Sliders
		JPanel sliders = new JPanel();
		sliders.setLayout(new BorderLayout());
		
		speedSlider = new JSlider(0, 1000, 100);
		speedSlider.setMajorTickSpacing(100);
		speedSlider.setMinorTickSpacing(10);
		speedSlider.setPaintTicks(true);
		speedSlider.setPaintLabels(true);
		speedSlider.addChangeListener(this);
		sliders.add(speedSlider, BorderLayout.NORTH);
		
		sizeSlider = new JSlider(0, 1000, 20);
		sizeSlider.setMajorTickSpacing(100);
		sizeSlider.setMinorTickSpacing(10);
		sizeSlider.setPaintTicks(true);
		sizeSlider.setPaintLabels(true);
		sliders.add(sizeSlider, BorderLayout.SOUTH);
		
		add(sliders, BorderLayout.CENTER);
		//Buttons
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		
		sort = new JButton("Sort");
		shuffle = new JButton("Shuffle");
		reverse = new JButton("Reverse");
		reset = new JButton("Reset");
		
		sort.addActionListener(this);
		shuffle.addActionListener(this);
		reverse.addActionListener(this);
		reset.addActionListener(this);
		
		buttons.add(sort);
		buttons.add(shuffle);
		buttons.add(reverse);
		buttons.add(reset);
		
		add(buttons, BorderLayout.NORTH);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == sort)
		{
			cancelSort();
			sorter = sortList.getSorter(array);
			sorter.execute();
		}
		else if (event.getSource() == shuffle)
		{
			cancelSort();
			array.shuffle();
		}
		else if (event.getSource() == reverse)
		{
			cancelSort();
			array.reverse();
		}
		else if (event.getSource() == reset)
		{
			cancelSort();
			int newsize = Math.max(2, sizeSlider.getValue());
			if (array.getSize() != newsize) parent.newArray(newsize);
			else array.fill();
		}
		
	}

	@Override
	public void stateChanged(ChangeEvent event) {
		if (event.getSource() == speedSlider)
		{
			SorterAbstract.setTimeout(speedSlider.getValue());
		}
	}
	
	public void setArray(VisualArray a)
	{
		array = a;
	}
	
	private void cancelSort()
	{
		if (sorter !=null)
		{
			sorter.cancel(true);
		}
	}

}
