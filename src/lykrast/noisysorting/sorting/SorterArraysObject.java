package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterArraysObject extends SorterAbstract {
	public SorterArraysObject(VisualArray array)
	{
		super(array);
	}

	//http://developer.classpath.org/doc/java/util/Arrays-source.html
	//Line 2417
	//Rewritten a bit to fit sleeps in
	@Override
	protected void sort() throws InterruptedException {
		mergeSort(0, a.getSize());
	}
	
	private void mergeSort(int min, int max) throws InterruptedException {
		if (min > max) return;
		
		for (int chunk = min; chunk < max; chunk += 6)
		{
			int end = Math.min(chunk + 6, max);
			for (int i = chunk+1; i < end; i++)
			{
				if (a.get(i-1) > a.get(i))
				{
					int j = i;
					int value = a.getSilent(j);
					sleep();
					do {
						a.set(j, a.get(j-1));
						j--;
						sleep();
					}
					while (j > chunk && a.get(j-1) > value);
					a.set(j, value);
					sleep();
				}
				else sleep();
			}
		}
		
		int len = max - min;
		if (len <= 6) return;
		
		int[] dest = new int[len];
		
		for (int size = 6; size < len; size <<= 1)
		{
			for (int start = min; start < max; start += size << 1)
			{
				int mid = start + size;
				int end = Math.min(max, mid + size);
				
				if (mid >= end || a.get(mid - 1) <= a.get(mid))
				{
					//TODO
				}
			}
		}
	}

}
