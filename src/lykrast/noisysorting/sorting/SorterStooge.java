package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterStooge extends SorterAbstract {
	public SorterStooge(VisualArray array)
	{
		super(array);
	}

	@Override
	protected Object doInBackground() throws Exception {
		stoogeSort(0, a.getSize()-1);
		a.sortFinished();
		return null;
	}
	
	private void stoogeSort(int min, int max)
	{
		//Mid-sort cancel
		if (isCancelled()) return;
		
		a.mark(min);
		a.mark(max);
		if (a.get(min) > a.get(max)) a.swap(min, max);
		sleep();
		if (max - min + 1 > 2)
		{
			int limiter = (max - min + 1)/3;
			stoogeSort(min, max-limiter);
			stoogeSort(min+limiter, max);
			stoogeSort(min, max-limiter);
		}
		a.unmark(min);
		a.unmark(max);
	}

}
