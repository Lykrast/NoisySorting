package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterDoubleSlowInsertion extends SorterAbstract {
	public SorterDoubleSlowInsertion(VisualArray array)
	{
		super(array);
	}

	@Override
	protected Object doInBackground() throws Exception {
		dsiSort(0, a.getSize()-1);
		a.sortFinished();
		return null;
	}
	
	private void dsiSort(int min, int max)
	{
		//Mid-sort cancel
		if (min >= max || isCancelled()) return;
		
		if (a.get(min) > a.get(max)) a.swap(min, max);
		sleep();
		if (max - min == 1) return;
		
		a.mark(min);
		a.mark(max);
		dsiSort(min+1,max-1);
		a.unmark(min);
		a.unmark(max);

		//Mid-sort cancel
		if (isCancelled()) return;
		if (a.get(min) > a.get(max)) a.swap(min, max);
		sleep();

		//Mid-sort cancel
		if (isCancelled()) return;
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