package lykrast.noisysorting.sorting.sort;

import lykrast.noisysorting.sorting.VisualArray;

public class SorterComb extends SorterAbstract {
	private static final double SHRINK_FACTOR = 1.3;
	
	public SorterComb(VisualArray array)
	{
		super(array);
	}

	@Override
	protected Object doInBackground() throws Exception {
		int size = a.getSize();
		int gap = size;
		boolean sorted;
		
		do {
			gap = (int)Math.floor(gap / SHRINK_FACTOR);
			if (gap > 1) sorted = false;
			else
			{
				gap = 1;
				sorted = true;
			}
			
			for (int i=0;i<size-gap;i++)
			{
				//Mid-sort cancel
				if (isCancelled())
				{
					a.sortFinished();
					return null;
				}
				
				if (a.get(i) > a.get(i+gap))
				{
					sorted = false;
					a.swap(i, i+gap);
				}
				sleep();
			}
		}while (!sorted);
		
		a.sortFinished();
		return null;
	}

}
