package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterBitonic extends SorterAbstract {
	public SorterBitonic(VisualArray array)
	{
		super(array);
	}

	//From http://www.iti.fh-flensburg.de/lang/algorithmen/sortieren/bitonic/oddn.htm
	@Override
	protected void sort() throws InterruptedException {
		bitonicSort(0, a.getSize(), true);
	}
	
	private void bitonicSort(int min, int length, boolean asc) throws InterruptedException
	{
		//Mid-sort cancel
		if (length <= 1) return;
		int middle = length/2;
		bitonicSort(min, middle, !asc);
		bitonicSort(min+middle, length-middle, asc);
		bitonicMerge(min, length, asc);
	}
	
	private void bitonicMerge(int min, int length, boolean asc) throws InterruptedException
	{
		//Mid-sort cancel
		if (length <= 1) return;
		a.mark(min);
		a.mark(min+length-1);
		
		int m = greatestPowerOfTwoLessThan(length);
		for (int i=min;i<min+length-m;i++)
		{			
			if (asc == (a.get(i) > a.get(i+m))) a.swap(i, i+m);
			sleep();
		}
		
		bitonicMerge(min, m, asc);
		bitonicMerge(min+m, length-m, asc);
		a.unmark(min);
		a.unmark(min+length-1);
	}
	
	private int greatestPowerOfTwoLessThan(int n)
	{
		int k = 1;
		while (k > 0 && k < n) k = k << 1;
		return k >>> 1;
	}

}
