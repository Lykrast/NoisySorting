package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterCycle extends SorterAbstract {
	public SorterCycle(VisualArray array)
	{
		super(array);
	}

	@Override
	protected Object doInBackground() throws Exception {
		int size = a.getSize();
		
		for (int start=0;start<size;start++)
		{
			int pos = start;
			a.mark(pos);
			for (int i=start+1;i<size;i++)
			{
				//Mid-sort cancel
				if (isCancelled())
				{
					a.sortFinished();
					return null;
				}
				
				if (a.get(i) < a.get(start))
				{
					a.unmark(pos);
					pos++;
					a.mark(pos);
				}
				sleep();
			}
			
			if (pos == start)
			{
				a.unmark(pos);
				continue;
			}
			
			while (a.get(start) == a.get(pos))
			{
				//Mid-sort cancel
				if (isCancelled())
				{
					a.sortFinished();
					return null;
				}
				
				a.unmark(pos);
				pos++;
				a.mark(pos);
				sleep();
			}
			
			a.unmark(pos);
			a.swap(start, pos);
			sleep();
			
			while (pos != start)
			{
				pos = start;
				a.mark(pos);
				for (int i=start+1;i<size;i++)
				{
					//Mid-sort cancel
					if (isCancelled())
					{
						a.sortFinished();
						return null;
					}
					
					if (a.get(i) < a.get(start))
					{
						a.unmark(pos);
						pos++;
						a.mark(pos);
					}
					sleep();
				}
				
				if (pos == start)
				{
					a.unmark(pos);
					break;
				}
				
				while (a.get(start) == a.get(pos))
				{
					//Mid-sort cancel
					if (isCancelled())
					{
						a.sortFinished();
						return null;
					}
					
					a.unmark(pos);
					pos++;
					a.mark(pos);
					sleep();
				}
				
				a.unmark(pos);
				a.swap(start, pos);
				sleep();
			}
		}

		a.sortFinished();
		return null;
	}

}
