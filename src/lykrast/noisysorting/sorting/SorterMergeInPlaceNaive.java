package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterMergeInPlaceNaive extends SorterAbstract {
	public SorterMergeInPlaceNaive(VisualArray array)
	{
		super(array);
	}

	//https://github.com/liuxinyu95/AlgoXY/blob/algoxy/sorting/merge-sort/src/mergesort.c
	@Override
	protected Object doInBackground() throws Exception {
		mergeSort(0, a.getSize()-1);
		a.sortFinished();
		return null;
	}
	
	private void mergeSort(int min, int max)
	{
		//Mid-sort cancel
		if (min >= max || isCancelled()) return;
		int middle = (min+max)/2;
		mergeSort(min,middle);
		mergeSort(middle+1,max);
		a.mark(min);
		a.mark(max);
		
		int l = min, m = middle+1;
		a.mark(m);
		while (l < m && m <= max)
		{
			//Mid-sort cancel
			if (isCancelled()) return;
			
			if (a.get(l) > a.get(m))
			{
				sleep();
				for (int i=m;i>l;i--)
				{
					//Mid-sort cancel
					if (isCancelled()) return;
					
					a.swap(i, i-1);
					sleep();
				}
				a.unmark(m);
				m++;
				if (m <= max) a.mark(m);
			}
			else sleep();
			l++;
		}
		if (m <= max) a.unmark(m);
		a.unmark(min);
		a.unmark(max);
	}

}
