package lykrast.noisysorting.sorting.sort;

import lykrast.noisysorting.sorting.VisualArray;

public class SorterSelection extends SorterAbstract {
	public SorterSelection(VisualArray array)
	{
		super(array);
	}

	@Override
	protected Object doInBackground() throws Exception {
		int size = a.getSize();
		
		for (int i=0;i<size;i++)
		{
			int minIndex = i;
			int minValue = a.getSilent(i);
			a.mark(minIndex);
			
			for (int j=i;j<size;j++)
			{
				//Mid-sort cancel
				if (isCancelled())
				{
					a.sortFinished();
					return null;
				}
				
				if (a.get(j) < minValue)
				{
					a.unmark(minIndex);
					minIndex = j;
					minValue = a.getSilent(j);
					a.mark(minIndex);
				}
				sleep();
			}
			
			a.unmark(minIndex);
			a.swap(i, minIndex);
			sleep();
		}

		a.sortFinished();
		return null;
	}

}
