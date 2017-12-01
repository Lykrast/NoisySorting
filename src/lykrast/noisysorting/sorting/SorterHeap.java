package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterHeap extends SorterAbstract {
	public SorterHeap(VisualArray array)
	{
		super(array);
	}

	@Override
	protected Object doInBackground() throws Exception {
		int end = a.getSize() - 1;
		
		//Heapify
		int start = parent(end);
		while (start >= 0)
		{
			//Mid-sort cancel
			if (isCancelled())
			{
				a.sortFinished();
				return null;
			}
			
			siftDown(start,end);
			start--;
		}
		
		while (end > 0)
		{
			//Mid-sort cancel
			if (isCancelled())
			{
				a.sortFinished();
				return null;
			}
			
			a.swap(0, end);
			sleep();

			end--;
			siftDown(0,end);
		}
		
		a.sortFinished();
		return null;
	}
	
	private void siftDown(int min, int max)
	{
		int root = min;
		
		while (leftChild(root) <= max)
		{
			//Mid-sort cancel
			if (isCancelled()) return;
			
			int child = leftChild(root);
			int swap = root;
			
			if (a.get(swap) < a.get(child)) swap = child;
			sleep();
			
			if (child < max)
			{
				if (a.get(swap) < a.get(child+1)) swap = child+1;
				sleep();
			}
			
			if (swap == root) return;
			
			a.swap(root, swap);
			root = swap;
			sleep();
		}
	}
	
	private int parent(int i) {return (i-1)/2;}
	private int leftChild(int i) {return 2*i+1;}
	//private int rightChild(int i) {return 2*i+2;}

}
