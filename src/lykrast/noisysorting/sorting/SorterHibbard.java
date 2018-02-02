package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterHibbard extends SorterAbstract {
	public SorterHibbard(VisualArray array)
	{
		super(array);
	}

	//https://metacpan.org/source/JGAMBLE/Algorithm-Networksort-2.01/lib/Algorithm/Networksort.pm
	@Override
	protected void sort() throws InterruptedException {
		int size = a.getSize();
		int lastBit = 1 << (int)Math.ceil(Math.log(a.getSize()-1)/Math.log(2));
		
		int x = 0, y = 1;
		sort:
		while (true)
		{
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
	}
	
	private void swapChecked(int i, int j) throws InterruptedException
	{
		if (a.get(i) > a.get(j)) a.swap(i, j);
		sleep();
	}

}
