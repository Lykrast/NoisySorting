package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterCircle extends SorterAbstract {
	//https://www.geeksforgeeks.org/circle-sort/
	public SorterCircle(VisualArray array) {
		super(array);
	}

	@Override
	protected void sort() throws InterruptedException {
		while (circleSort(0, a.getSize()-1));
	}
	
	private boolean circleSort(int min, int max) throws InterruptedException {
		boolean swapped = false;
		
		//Base case
		if (min >= max) return false;
		
		//Do the swaps
		int imin, imax;
		for (imin = min, imax = max; imin < imax; imin++, imax--) {
			if (a.get(imin) > a.get(imax)) {
				a.swap(imin, imax);
				swapped = true;
			}
			sleep();
		}
		
		//Handle odd sizes
		if (imin == imax) {
			if (a.get(imin) > a.get(imax + 1)) {
				a.swap(imin, imax + 1);
				swapped = true;
			}
			sleep();
		}
		
		//Recursion
		int mid = (max - min) / 2;
		boolean first = circleSort(min, min+mid);
		boolean second = circleSort(min+mid+1, max);
		
		return swapped || first || second;
	}

}
