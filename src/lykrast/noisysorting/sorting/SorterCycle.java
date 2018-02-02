package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterCycle extends SorterAbstract {
	public SorterCycle(VisualArray array)
	{
		super(array);
	}

	@Override
	protected void sort() throws InterruptedException {
		int size = a.getSize();
		
		for (int start=0;start<size;start++)
		{
			int pos = start;
			a.mark(pos);
			for (int i=start+1;i<size;i++)
			{
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
	}

}
