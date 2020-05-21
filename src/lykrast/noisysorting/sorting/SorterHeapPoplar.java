package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterHeapPoplar extends SorterAbstract {
	//https://github.com/Morwenn/poplar-heap
	//This version doesn't use the insertion sort, binary carry sequence or intrisics
	public SorterHeapPoplar(VisualArray array) {
		super(array);
	}

	@Override
	protected void sort() throws InterruptedException {
		int size = a.getSize();
		
		//Make heap, this is the version without the binary carry sequence
		makeHeap(0, size);
		
		//Sort heap
		if (size < 2) return;
		
		do {
			//first is always equal to 0 because of how we sort
			popHeap(0, size);
			--size;
		} while (size > 1);
	}
	
	private void makeHeap(int first, int size) throws InterruptedException {
		int poplarSize = bitFloor(size + 1) - 1;
		
		while (true) {
			//Make a poplar
			makePoplar(first, poplarSize);
			if (size - poplarSize == 0) return;
			
			//Advance to next one
			first += poplarSize;
			size -= poplarSize;
			poplarSize = bitFloor(size + 1) - 1;
		}
	}
	
	private void popHeap(int first, int size) throws InterruptedException {
		//last == size since we only call with first = 0
		int poplarSize = bitFloor(size + 1) - 1;
		int lastRoot = size-1;
		int bigger = lastRoot;
		int biggerSize = poplarSize;
		
		//Find bigger poplar root
		int it = first;
		while (true) {
			int root = it + poplarSize - 1;
			if (root == lastRoot) break;
			if (a.get(bigger) < a.get(root)) {
				bigger = root;
				biggerSize = poplarSize;
			}
			sleep();
			it = root+1;
			
			size -= poplarSize;
			poplarSize = bitFloor(size + 1) - 1;
		}
		
		//Swap and sift if needed
		if (bigger != lastRoot) {
			a.swap(bigger, lastRoot);
			sleep();
			sift(bigger - (biggerSize - 1), biggerSize);
		}
	}
	
	//Smallest power of 2 <= n
	private int bitFloor(int n) {
		for (int i = 1; i <= 16; i <<= 1) n |= (n >> i);
		return n & ~(n >> 1);
	}
	
	private void makePoplar(int first, int size) throws InterruptedException {
		//if size < 16 insertion sort
		if (size < 2) return;
		
		makePoplar(first, size/2);
		makePoplar(first + size/2, size/2);
		sift(first, size);
	}
	
	private void sift(int first, int size) throws InterruptedException {
		if (size < 2) return;
		
		//Find root of semipoplar and subpoplars
		int root = first + (size - 1);
		int childRoot1 = root - 1;
		int childRoot2 = first + (size/2 - 1);
		
		//Find bigger root
		int maxRoot = root;
		if (a.get(maxRoot) < a.get(childRoot1)) maxRoot = childRoot1;
		sleep();
		if (a.get(maxRoot) < a.get(childRoot2)) maxRoot = childRoot2;
		sleep();
		
		//If a subpoplar root was bigger, swap it and sift recursively
		if (maxRoot != root) {
			a.swap(root, maxRoot);
			sleep();
			sift(maxRoot - (size / 2 - 1), size / 2);
		}
	}
}
