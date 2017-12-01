package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterShell extends SorterAbstract {
	// https://oeis.org/A102549
	private static final int[] GAPS = {701, 301, 132, 57, 23, 10, 4, 1};
	
	public SorterShell(VisualArray array)
	{
		super(array);
	}

	@Override
	protected Object doInBackground() throws Exception {
		int size = a.getSize();
		
		for (int g : GAPS)
		{
			for (int i=g;i<size;i++)
			{
				for (int j=i;j >= g && a.getSilent(j-g) > a.getSilent(j);j -= g)
				{
					//Mid-sort cancel
					if (isCancelled())
					{
						a.sortFinished();
						return null;
					}
					
					a.swap(j, j-g);
					sleep();
				}
			}
		}
		
		a.sortFinished();
		return null;
	}

}
