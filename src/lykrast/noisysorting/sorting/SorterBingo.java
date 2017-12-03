package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterBingo extends SorterAbstract {
	public SorterBingo(VisualArray array)
	{
		super(array);
	}

	@Override
	protected Object doInBackground() throws Exception {
		int size = a.getSize();
		int min;
		int minIndex = 0;
		int insert = 0;
		a.mark(insert);
		
		//Finding the first minimum
		for (int j=1;j<size;j++)
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
		min = a.getSilent(minIndex);
		while (insert < size && a.get(insert) == min)
		{
			//Mid-sort cancel
			if (isCancelled())
			{
				a.sortFinished();
				return null;
			}
			
			a.unmark(insert);
			insert++;
			a.mark(insert);
			sleep();
		}
		
		while (insert < size)
		{
			//Storing the index for better visualization
			minIndex = insert;
			for (int j=insert+1;j<size;j++)
			{
				//Mid-sort cancel
				if (isCancelled())
				{
					a.sortFinished();
					return null;
				}
				
				if (a.get(j) == min)
				{
					a.unmark(insert);
					//Ensuring minIndex doesn't get changed here
					if (minIndex == insert) minIndex = j;
					else if (minIndex == j) minIndex = insert;
					a.swap(insert, j);
					insert++;
					a.mark(insert);
				}
				else if (a.get(j) < a.get(minIndex))
				{
					minIndex = j;
				}
				sleep();
			}
			while (insert < size && a.get(insert) == min)
			{
				//Mid-sort cancel
				if (isCancelled())
				{
					a.sortFinished();
					return null;
				}
				
				a.unmark(insert);
				insert++;
				a.mark(insert);
				sleep();
			}
			min = a.getSilent(minIndex);
		}

		a.sortFinished();
		return null;
	}

}
