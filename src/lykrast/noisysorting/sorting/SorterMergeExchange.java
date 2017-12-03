package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterMergeExchange extends SorterAbstract {
	public SorterMergeExchange(VisualArray array)
	{
		super(array);
	}

	//From Donald Knuth, The Art of Computer Programming, vol. 3 : Sorting and Searching
	//Algorithm M page 111
	@Override
	protected Object doInBackground() throws Exception {
		int size = a.getSize();
		int t, p;
		//Find lowest t such that 2^t >= size
		for (t=0; 1<<t < size; t++);
		
		for (p=1<<(t-1); p > 0; p/=2)
		{
			int q = 1<<(t-1), r = 0, d = p;
			while (true)
			{
				for (int i=0;i<size-d;i++)
				{
					//Mid-sort cancel
					if (isCancelled())
					{
						a.sortFinished();
						return null;
					}
					
					if ((i & p) == r)
					{
						if (a.get(i) > a.get(i+d)) a.swap(i, i+d);
						sleep();
					}
				}
				
				if (q == p) break;
				else
				{
					d = q-p;
					q /= 2;
					r = p;
				}
			}
		}
		
		a.sortFinished();
		return null;
	}

}
