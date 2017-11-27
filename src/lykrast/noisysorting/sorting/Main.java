package lykrast.noisysorting.sorting;

import javax.swing.SwingUtilities;

import lykrast.noisysorting.sorting.sort.SorterAbstract;
import lykrast.noisysorting.sorting.sort.SorterCocktail;
import lykrast.noisysorting.ui.ArrayLabel;
import lykrast.noisysorting.ui.ArraySoundMaker;
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
