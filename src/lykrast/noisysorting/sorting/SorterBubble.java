package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterBubble extends SorterAbstract {
	public SorterBubble(VisualArray array)
	{
		super(array);
	}

	@Override
	protected Object doInBackground() throws Exception {
		int size = a.getSize();
		boolean sorted;
		
		do {
			sorted = true;
			
			for (int i=0;i<size-1;i++)
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
