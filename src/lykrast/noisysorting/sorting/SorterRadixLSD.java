package lykrast.noisysorting.sorting;

import java.util.Arrays;

import lykrast.noisysorting.array.VisualArray;

public class SorterRadixLSD extends SorterAbstract {
	private static final int RADIX = 4;
	
	public SorterRadixLSD(VisualArray array)
	{
		super(array);
	}

	//From http://www.geeksforgeeks.org/radix-sort/
	@Override
	protected void sort() throws InterruptedException {
		//Finding the maximum
		int maxIndex = 0;
		for (int i=0;i<a.getSize();i++)
		{
			if (a.get(i) > a.get(maxIndex)) maxIndex = i;
			sleep();
		}
		
		int max = a.getSilent(maxIndex);
		for (int exp=1;max/exp>0;exp*=RADIX)
			countSort(exp);
	}
	
	private void countSort(int exp) throws InterruptedException
	{
		int size = a.getSize();
		int[] counts = new int[RADIX];
		Arrays.fill(counts, 0);
		
		for (int i=0;i<size;i++)
		{
			counts[(a.get(i)/exp) % RADIX]++;
			sleep();
		}
		
		//Computing the positions
		for (int i=1;i<RADIX;i++) counts[i] += counts[i-1];
		
		//Building the sorted array
		int[] tmp = new int[size];
		for (int i=size-1;i>=0;i--)
		{
			tmp[counts[(a.getSilent(i)/exp) % RADIX]-1] = a.getSilent(i);
			counts[(a.getSilent(i)/exp) % RADIX]--;
		}
		
		//Copying to visible array
		for (int i=0;i<size;i++)
		{
			a.set(i, tmp[i]);
			sleep();
		}
	}

}
