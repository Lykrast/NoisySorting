package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterBatcher extends SorterAbstract {
	public SorterBatcher(VisualArray array)
	{
		super(array);
	}

	@Override
	protected void sort() throws InterruptedException {
		oddEvenSort(0, a.getSize()-1);
	}
	
	private void oddEvenSort(int min, int max) throws InterruptedException
	{
		if (min >= max) return;
		
		//From https://gist.github.com/stbuehler/883635
		//Makes sure it works on non 2^k sized arrays
		int size = max - min + 1;
		int len = 2;
		while (len < size) len <<= 1;
		len >>= 1;
		oddEvenSort(min, min+len-1);
		oddEvenSort(min+len, max);
		oddEvenMerge(min, max, 1);
	}
	
	private void oddEvenMerge(int min, int max, int r) throws InterruptedException
	{
		a.mark(min);
		a.mark(max);
		int step = r*2;
		if (step < max - min + 1)
		{
			oddEvenMerge(min, max, step);
			oddEvenMerge(min+r, max, step);
			for (int i=min+r;i<=max-r;i+=step) compareSwap(i, i+r);
		}
		else compareSwap(min, min+r);
		a.unmark(min);
		a.unmark(max);
	}
	
	private void compareSwap(int i, int j) throws InterruptedException
	{
		//Sort is made to work on 2^k size arrays, so this prevents overflowing
		if (j >= a.getSize()) return;
		if (a.get(i) > a.get(j)) a.swap(i, j);
		sleep();
	}

}
