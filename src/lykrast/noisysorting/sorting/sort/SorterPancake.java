package lykrast.noisysorting.sorting.sort;

import lykrast.noisysorting.sorting.VisualArray;

public class SorterPancake extends SorterAbstract {
	public SorterPancake(VisualArray array)
	{
		super(array);
	}

	@Override
	protected Object doInBackground() throws Exception {
		int size = a.getSize();
		
		for (int i=size;i>1;i--)
		{
			int maxIndex = 0;
			for (int j=1;j<i;j++)
			{
				//Mid-sort cancel
				if (isCancelled())
				{
					a.sortFinished();
					return null;
				}
				
				if (a.get(j) > a.get(maxIndex)) maxIndex = j;
				sleep();
			}
			
			if (maxIndex == i-1) continue;
			
			if (maxIndex > 0)
			{
				flip(maxIndex+1);
			}
			
			flip(i);
		}

		a.sortFinished();
		return null;
	}
	
	private void flip(int max)
	{
		a.mark(max-1);
		int middle = max / 2;
		for (int i=0;i<middle;i++)
		{
			//Mid-sort cancel
			if (isCancelled()) return;
			
			a.swap(i,max-i-1);
			sleep();
		}
		a.unmark(max-1);
	}

}
