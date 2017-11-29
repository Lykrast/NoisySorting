package lykrast.noisysorting.sorting.sort;

import lykrast.noisysorting.sorting.VisualArray;

public class SorterQuickLomuto extends SorterAbstract {
	public SorterQuickLomuto(VisualArray array)
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
		quicksort(min, p-1);
		quicksort(p+1,max);
	}
	
	private int partition(int min, int max)
	{
		//The pivot is not supposed to change, but using index allows better visualization
		int pivot = max;
		int j = min - 1;
		
		for (int i=min;i<max;i++)
		{
			//Mid-sort cancel
			if (isCancelled()) return -1;
			
			if (a.get(i) <= a.get(pivot))
			{
				j++;
				a.swap(i, j);
			}
			sleep();
		}
		
		if (a.get(max) < a.get(j+1)) a.swap(max, j+1);
		sleep();
		return j + 1;
	}

}
