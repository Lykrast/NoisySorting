package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterQuickLomuto extends SorterAbstract {
	public SorterQuickLomuto(VisualArray array)
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
		quicksort(min, p-1);
		quicksort(p+1,max);
	}
	
	private int partition(int min, int max) throws InterruptedException
	{
		//The pivot is not supposed to change, but using index allows better visualization
		int pivot = max;
		int j = min - 1;
		
		for (int i=min;i<max;i++)
		{			
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
