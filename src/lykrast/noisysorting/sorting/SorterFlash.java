package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterFlash extends SorterAbstract {
	// https://www.w3resource.com/javascript-exercises/searching-and-sorting-algorithm/searching-and-sorting-algorithm-exercise-12.php
	public SorterFlash(VisualArray array) {
		super(array);
	}

	@Override
	protected void sort() throws InterruptedException {
		int imax = 0;
		int vmin = a.getSilent(0);
		int size = a.getSize();
		
		//Find imax and vmin
		for (int i = 1; i < size; i++) {
			if (a.get(i) < vmin) vmin = a.get(i);
			if (a.get(i) > a.get(imax)) imax = i;
			sleep();
		}
		
		//Black magic
		int m = (size * 45) / 100;
		int[] l = new int[m];
		double c1 = (m - 1.0) / (a.getSilent(imax) - vmin);
		
		for (int i = 0; i < size; i++)	{
			l[(int)(c1 * (a.get(i) - vmin))]++;
			sleep();
		}
		for (int i = 1; i < m; i++) l[i] += l[i-1];
		
		a.swap(0, imax);
		sleep();
		
		//Permutation
		int move = 0, j = 0, k = m-1;
		
		while (move < size-1) {
			while (j > (l[k] - 1)) {
				j++;
				k = (int)(c1 * (a.get(j) - vmin));
				sleep();
			}
			if (k < 0) break;
			int flash = a.getSilent(j);
			while (j != l[k]) {
				k = (int)(c1 * (flash - vmin));
				int t = --l[k];
				int hold = a.get(t);
				a.set(t, flash);
				flash = hold;
				move++;
				sleep();
			}
		}
		
		//Insertion
		for (int i = 1; i < size; i++) {
			int hold = a.getSilent(i);
			int ii = i - 1;
			while (ii >= 0 && a.get(ii) > hold) {
				a.set(ii+1, a.get(ii--));
				sleep();
			}
			a.set(ii+1, hold);
			sleep();
		}
	}

}
