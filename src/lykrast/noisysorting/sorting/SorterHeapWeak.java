package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterHeapWeak extends SorterAbstract {
	//https://www.sanfoundry.com/cpp-program-implement-weak-heap/
	public SorterHeapWeak(VisualArray array) {
		super(array);
	}

	@Override
	protected void sort() throws InterruptedException {
		//TODO make more readable
		int size = a.getSize();
		
		//Prepare the extra bits
		int[] bits = new int[(size + 7) / 8];
		for (int i = 0; i < size / 8; i++) bits[i] = 0;
		
		//Build the weak heap
		for (int i = size - 1; i > 0; i--) {
			int j = i;
			while ((j & 1) == getFlag(bits, j >> 1)) j >>= 1;
			merge(bits, j >> 1, i);
		}
		
		//Extract
		for (int i = size - 1; i >= 2; i--) {
			a.swap(0, i);
			sleep();
			int x = 1;
			int y;
			while ((y = 2 * x + getFlag(bits, x)) < i) x = y;
			while (x > 0) {
				merge(bits, 0, x);
				x >>= 1;
			}
		}
		
		a.swap(0, 1);
		sleep();
	}
	
	private int getFlag(int[] bits, int x) {
		return (bits[x >> 3] >> (x & 7)) & 1;
	}
	
	private void toggleFlag(int[] bits, int x) {
		bits[x >> 3] ^= 1 << (x & 7);
	}
	
	private void merge(int[] bits, int i, int j) throws InterruptedException {
		if (a.get(i) < a.get(j)) {
			toggleFlag(bits, j);
			a.swap(i, j);
		}
		sleep();
	}
}
