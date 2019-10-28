package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterMergeWeavedBottomUp extends SorterAbstract {
	public SorterMergeWeavedBottomUp(VisualArray array) {
		super(array);
	}

	@Override
	protected void sort() throws InterruptedException {
		int maxmod = 0;
		while (2 << (maxmod+1) < a.getSize()) maxmod++;
		maxmod = 2 << maxmod;
		//Initial insertion sort
		for (int rem = 0; rem < maxmod; rem++) insertionSort(maxmod, rem);
		
		//Merge
		for (int mod = maxmod/2; mod > 0; mod /= 2) {
			for (int rem = 0; rem < mod; rem++) merge(mod, rem);
		}
	}
	
	private void merge(int mod, int rem) throws InterruptedException {
		int submod = mod*2;
		int size = a.getSize();
		
		int[] temp = new int[size / mod + (size % mod == 0 ? 0 : 1)];
		int pointerL = rem, pointerR = rem+mod;
		for (int i = rem; i < size; i+=mod) {
			if (pointerL < size && (pointerR >= size || a.get(pointerL) <= a.get(pointerR))) {
				temp[(i-rem)/mod] = a.get(pointerL);
				pointerL += submod;
			}
			else {
				temp[(i-rem)/mod] = a.get(pointerR);
				pointerR += submod;
			}
			sleep();
		}
		
		for (int i = rem; i < size; i+=mod) {
			a.set(i, temp[(i-rem)/mod]);
			sleep();
		}
	}
	
	private void insertionSort(int mod, int rem) throws InterruptedException {
		//Insertion sort
		int size = a.getSize();
		
		for (int i = rem; i < size; i+=mod) {
			a.mark(i);
			int j = i;
			
			while (j >= mod && a.get(j-mod) > a.get(j))
			{
				a.swap(j, j-mod);
				j--;
				sleep();
			}
			//Only sleep here if no change was made before
			if (j == i) sleep();
			a.unmark(i);
		}
	}

}
