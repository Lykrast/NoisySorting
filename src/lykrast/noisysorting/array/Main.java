package lykrast.noisysorting.array;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import lykrast.noisysorting.ui.SortingFrame;

public class Main {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println("Error loading look and feel: " + e);
		}
		
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
