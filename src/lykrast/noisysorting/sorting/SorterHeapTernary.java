package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterHeapTernary extends SorterAbstract {
	// https://codereview.stackexchange.com/q/63384
	public SorterHeapTernary(VisualArray array) {
		super(array);
	}

	@Override
	protected void sort() throws InterruptedException {
		int size = a.getSize() - 1;
		
		//Build the heap
		for (int i = size / 3; i >= 0; i--) heapify(i, size);
		
		//Sort
		for (int i = size; i >= 0; i--) {
			a.swap(0, i);
			sleep();
			size--;
			heapify(0, size);
		}
	}
	
	private void heapify(int i, int size) throws InterruptedException {
		int left = left(i), middle = middle(i), right = right(i);
		int largest = i;
		if (left <= size) {
			if (a.get(left) > a.get(largest)) largest = left;
			sleep();
		}
		if (right <= size) {
			if (a.get(right) > a.get(largest)) largest = right;
			sleep();
		}
		if (middle <= size) {
			if (a.get(middle) > a.get(largest)) largest = middle;
			sleep();
		}
		
		if (largest != i) {
			a.swap(i, largest);
			sleep();
			heapify(largest, size);
		}
	}

	private int left(int i) {
		return 3*i + 1;
	}

	private int middle(int i) {
		return 3*i + 2;
	}

	private int right(int i) {
		return 3*i + 3;
	}
}
