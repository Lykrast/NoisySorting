package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterSwapInsertionMerge extends SorterAbstract {
	public SorterSwapInsertionMerge(VisualArray array)
	{
		super(array);
	}

	@Override
	protected Object doInBackground() throws Exception {
		simSort(0, a.getSize()-1);
		a.sortFinished();
		return null;
	}
	
	private void simSort(int min, int max)
	{
		//Mid-sort cancel
		if (min >= max || isCancelled()) return;
		if (max - min == 1)
		{
			if (a.get(min) > a.get(max)) a.swap(min, max);
			sleep();
			return;
		}
		int middle = (min+max)/2;
		simSort(min,middle);
		simSort(middle+1,max);
		a.mark(min);
		a.mark(middle);
		a.mark(middle+1);
		a.mark(max);
		for (int i=max;i>middle;i--)
		{
			//Mid-sort cancel
			if (isCancelled()) return;
			
			a.mark(i);
			if (a.get(i) < a.get(middle))
			{
				a.swap(i, middle);
				int j = middle;
				
				while (j > min && a.get(j-1) > a.get(j))
				{
					//Mid-sort cancel
					if (isCancelled()) return;
					sleep();
					a.swap(j, j-1);
					j--;
				}
			}
			sleep();
			a.unmark(i);
		}
		a.unmark(min);
		a.unmark(middle);
		a.unmark(middle+1);
		a.unmark(max);
	}

}
