package lykrast.noisysorting.sorting.sort;

import lykrast.noisysorting.sorting.VisualArray;

public class SorterGnome extends SorterAbstract {
	public SorterGnome(VisualArray array)
	{
		super(array);
	}

	@Override
	protected Object doInBackground() throws Exception {
		int size = a.getSize();
		int pos = 0;
		
		while (pos < size)
		{
			//Mid-sort cancel
			if (isCancelled())
			{
				a.sortFinished();
				return null;
			}
			
			if (pos == 0 || a.get(pos) >= a.get(pos-1))
			{
				pos++;
			}
			else
			{
				a.swap(pos, pos-1);
				pos--;
			}
			sleep();
		}
		
		a.sortFinished();
		return null;
	}

}
