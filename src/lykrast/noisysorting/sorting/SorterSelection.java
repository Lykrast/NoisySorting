package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

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
			
			for (int j=i;j<size;j++)
			{
				//Mid-sort cancel
				if (isCancelled())
				{
					a.sortFinished();
					return null;
				}
				
				if (a.get(j) < a.get(minIndex))
				{
					minIndex = j;
				}
				sleep();
			}
			
			a.swap(i, minIndex);
			sleep();
		}

		a.sortFinished();
		return null;
	}

}
