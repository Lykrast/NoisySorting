package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterHeapMin extends SorterAbstract {
	//It's kind of like a scam but like it looks different
	public SorterHeapMin(VisualArray array) {
		super(array);
	}

	@Override
	protected void sort() throws InterruptedException {
		int size = a.getSize();
		int end = size - 1;

		//Heapify
		int start = parent(end);
		while (start >= 0) {
			siftDown(start, end);
			start--;
		}

		while (end > 0) {
			a.swap(0, end);
			sleep();

			end--;
			siftDown(0, end);
		}
		
		//Reverse
		int middle = a.getSize() / 2;
		for (int i = 0; i < middle; i++) {
			a.swap(i, size - i - 1);
			sleep();
		}
	}

	private void siftDown(int min, int max) throws InterruptedException {
		int root = min;

		while (leftChild(root) <= max) {
			int child = leftChild(root);
			int swap = root;

			if (a.get(swap) > a.get(child)) swap = child;
			sleep();

			if (child < max) {
				if (a.get(swap) > a.get(child + 1)) swap = child + 1;
				sleep();
			}

			if (swap == root) return;

			a.swap(root, swap);
			root = swap;
			sleep();
		}
	}

	private int parent(int i) {
		return (i - 1) / 2;
	}

	private int leftChild(int i) {
		return 2 * i + 1;
	}
	//private int rightChild(int i) {return 2*i+2;}

}
