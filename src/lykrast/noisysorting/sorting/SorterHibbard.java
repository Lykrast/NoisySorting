package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterHibbard extends SorterAbstract {
	public SorterHibbard(VisualArray array)
	{
		super(array);
	}

	//https://metacpan.org/source/JGAMBLE/Algorithm-Networksort-2.01/lib/Algorithm/Networksort.pm
	@Override
	protected Object doInBackground() throws Exception {
		int size = a.getSize();
		int lastBit = 1 << (int)Math.ceil(Math.log(a.getSize()-1)/Math.log(2));
		
		int x = 0, y = 1;
		sort:
		while (true)
		{
			System.out.println(x + " <-> " + y);
			swapChecked(x, y);
			
			int bit = 1;
			int xbit = x & bit;
			int ybit = y & bit;
			
			while (xbit != 0 && ybit == 0)
			{
				x &= ~bit;
				bit <<= 1;
				xbit = x & bit;
				ybit = y & bit;
			}

			if (xbit != 0)
			{
				y &= ~bit;
				continue;
			}

			if (ybit == 0)
			{
				x |= bit;
				y |= bit;
				if (y >= size) y &= ~bit;
				continue;
			}

			System.out.println("do");
			do
			{
				if (bit == lastBit) break sort;
				
				x &= ~bit;
				y &= ~bit;
				bit <<= 1;
				
				if ((y & bit) > 0)
				{
					x &= ~bit;
					continue sort;
				}
				
				x |= bit;
				y |= bit;
			} while (y >= size);
			
			if (y < size-1) bit = 1;
			x &= ~bit;
			y |= bit;
		}
		
		a.sortFinished();
		return null;
	}
	
	private void swapChecked(int i, int j)
	{
		//Mid-sort cancel
		if (isCancelled()) return;
		
		if (a.get(i) > a.get(j)) a.swap(i, j);
		sleep();
	}

}
