package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterQuickHoare extends SorterAbstract {
	public SorterQuickHoare(VisualArray array)
	{
		super(array);
	}

	@Override
	protected Object doInBackground() throws Exception {
		quicksort(0, a.getSize()-1);
		a.sortFinished();
		return null;
	}
	
	private void quicksort(int min, int max)
	{
		if (min >= max || isCancelled()) return;
		
		int p = partition(min,max);
		quicksort(min, p);
		quicksort(p+1,max);
	}
	
	private int partition(int min, int max)
	{
		//The pivot is not supposed to change, but using index allows better visualization
		int pivot = min;
		int i = min;
		int j = max;
		
		while (true)
		{
			while (a.get(i) < a.get(pivot))
			{
				//Mid-sort cancel
				if (isCancelled()) return -1;
				
				i++;
				sleep();
			}
			//Only sleep here if no change was made before
			if (i == min) sleep();
			
			while (a.get(j) > a.get(pivot))
			{
				//Mid-sort cancel
				if (isCancelled()) return -1;
				
				j--;
				sleep();
			}
			//Only sleep here if no change was made before
			if (j == max) sleep();

			//Mid-sort cancel
			if (isCancelled()) return -1;
			
			if (i >= j) return j;
			
			//Swapping the pivot value for visualization
			if (pivot == i) pivot = j;
			else if (pivot == j) pivot = i;
			a.swap(i, j);
			sleep();
		}
	}

}
