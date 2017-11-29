package lykrast.noisysorting.sorting;

import javax.swing.SwingUtilities;

import lykrast.noisysorting.ui.SortingFrame;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable()
		{

			@Override
			public void run() {
				SortingFrame frame = new SortingFrame();
				frame.setVisible(true);
			}
			
		});
	}

}
