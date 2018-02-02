package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterBalanced extends SorterAbstract {
	public SorterBalanced(VisualArray array)
	{
		super(array);
	}

	//https://metacpan.org/source/JGAMBLE/Algorithm-Networksort-2.01/lib/Algorithm/Networksort.pm
	@Override
	protected void sort() throws InterruptedException {
		int t = (int)Math.ceil(Math.log(a.getSize()-1)/Math.log(2));
		
		for (int k=0;k<t;k++)
		{
			for (int power = 1 << t; power > 1; power /= 2)
			{
				for (int i = 0; i < 1 << t; i += power)
				{
					for (int j = 0; j < power/2; j++)
					{						
						swapChecked(i+j, i+power-j-1);
					}
				}
			}
		}
	}
	
	private void swapChecked(int i, int j) throws InterruptedException
	{
		if (i >= a.getSize() || j >= a.getSize()) return;
		
		if (a.get(i) > a.get(j)) a.swap(i, j);
		sleep();
	}

}
