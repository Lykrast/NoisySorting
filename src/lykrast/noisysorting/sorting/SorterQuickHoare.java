package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterQuickHoare extends SorterAbstract {
	public SorterQuickHoare(VisualArray array)
	{
		super(array);
	}

	@Override
	protected void sort() throws InterruptedException {
		quicksort(0, a.getSize()-1);
	}
	
	private void quicksort(int min, int max) throws InterruptedException
	{
		if (min >= max) return;
		
		int p = partition(min,max);
		quicksort(min, p);
		quicksort(p+1,max);
	}
	
	private int partition(int min, int max) throws InterruptedException
	{
		//The pivot is not supposed to change, but using index allows better visualization
		int pivot = min;
		int i = min;
		int j = max;
		
		while (true)
		{
			while (a.get(i) < a.get(pivot))
			{
				i++;
				sleep();
			}
			//Only sleep here if no change was made before
			if (i == min) sleep();
			
			while (a.get(j) > a.get(pivot))
			{
				j--;
				sleep();
			}
			//Only sleep here if no change was made before
			if (j == max) sleep();
			
			if (i >= j) return j;
			
			//Swapping the pivot value for visualization
			if (pivot == i) pivot = j;
			else if (pivot == j) pivot = i;
			a.swap(i, j);
			sleep();
		}
	}

}
