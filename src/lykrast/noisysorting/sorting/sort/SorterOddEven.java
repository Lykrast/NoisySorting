package lykrast.noisysorting.sorting.sort;

import lykrast.noisysorting.sorting.VisualArray;

public class SorterOddEven extends SorterAbstract {
	public SorterOddEven(VisualArray array)
	{
		super(array);
	}

	@Override
	protected Object doInBackground() throws Exception {
		int size = a.getSize();
		boolean sorted;
		
		do {
			sorted = true;
			
			for (int i=1;i<size-1;i+=2)
			{
				//Mid-sort cancel
				if (isCancelled())
				{
					a.sortFinished();
					return null;
				}
				
				if (a.get(i) > a.get(i+1))
				{
					sorted = false;
					a.swap(i, i+1);
				}
				sleep();
			}
			
			for (int i=0;i<size-1;i+=2)
			{
				//Mid-sort cancel
				if (isCancelled())
				{
					a.sortFinished();
					return null;
				}
				
				if (a.get(i) > a.get(i+1))
				{
					sorted = false;
					a.swap(i, i+1);
				}
				sleep();
			}
		}while (!sorted);
		
		a.sortFinished();
		return null;
	}

}
