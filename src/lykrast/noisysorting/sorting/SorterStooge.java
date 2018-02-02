package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterStooge extends SorterAbstract {
	public SorterStooge(VisualArray array)
	{
		super(array);
	}

	@Override
	protected void sort() throws InterruptedException {
		stoogeSort(0, a.getSize()-1);
	}
	
	private void stoogeSort(int min, int max) throws InterruptedException
	{
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
