package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterMergeWeaved extends SorterAbstract {
	public SorterMergeWeaved(VisualArray array) {
		super(array);
	}

	@Override
	protected void sort() throws InterruptedException {
		int depth = 0;
		while (2 << (depth+1) < a.getSize()) depth++;
		mergeSort(1, 0, depth);
	}
	
	private void mergeSort(int mod, int rem, int depth) throws InterruptedException {
		//Recursively sort the partitions
		int submod = mod*2;
		if (depth <= 0) {
			insertionSort(submod, rem);
			insertionSort(submod, rem+mod);
		}
		else {
			mergeSort(submod, rem, depth-1);
			mergeSort(submod, rem+mod, depth-1);
		}
		//Merge
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
