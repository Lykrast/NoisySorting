package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterDoubleSlowInsertion extends SorterAbstract {
	public SorterDoubleSlowInsertion(VisualArray array)
	{
		super(array);
	}

	@Override
	protected void sort() throws InterruptedException {
		dsiSort(0, a.getSize()-1);
	}
	
	private void dsiSort(int min, int max) throws InterruptedException
	{
		//Mid-sort cancel
		if (min >= max) return;
		
		if (a.get(min) > a.get(max)) a.swap(min, max);
		sleep();
		if (max - min == 1) return;
		
		a.mark(min);
		a.mark(max);
		dsiSort(min+1,max-1);
		a.unmark(min);
		a.unmark(max);
		
		if (a.get(min) > a.get(max)) a.swap(min, max);
		sleep();

		boolean change = false;
		if (a.get(min) > a.get(min+1))
		{
			change = true;
			a.swap(min, min+1);
		}
		if (a.get(max-1) > a.get(max))
		{
			change = true;
			a.swap(max-1, max);
		}
		sleep();
		if (change) dsiSort(min+1,max-1);
	}

}
