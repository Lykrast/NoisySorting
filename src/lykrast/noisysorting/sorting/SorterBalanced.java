package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterBalanced extends SorterAbstract {
	public SorterBalanced(VisualArray array)
	{
		super(array);
	}

	//https://metacpan.org/source/JGAMBLE/Algorithm-Networksort-2.01/lib/Algorithm/Networksort.pm
	@Override
	protected Object doInBackground() throws Exception {
		int t = (int)Math.ceil(Math.log(a.getSize()-1)/Math.log(2));
		
		for (int k=0;k<t;k++)
		{
			for (int power = 1 << t; power > 1; power /= 2)
			{
				for (int i = 0; i < 1 << t; i += power)
				{
					for (int j = 0; j < power/2; j++)
					{
						//Mid-sort cancel
						if (isCancelled())
						{
							a.sortFinished();
							return null;
						}
						
						swapChecked(i+j, i+power-j-1);
					}
				}
			}
		}
		
		a.sortFinished();
		return null;
	}
	
	private void swapChecked(int i, int j)
	{
		//Mid-sort cancel
		if (i >= a.getSize() || j >= a.getSize() || isCancelled()) return;
		
		if (a.get(i) > a.get(j)) a.swap(i, j);
		sleep();
	}

}
