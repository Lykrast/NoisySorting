package lykrast.noisysorting.sorting;

import java.util.Arrays;

import lykrast.noisysorting.array.VisualArray;

public class SorterRadixMSD extends SorterAbstract {
	private static final int RADIX = 4;
	
	public SorterRadixMSD(VisualArray array)
	{
		super(array);
	}

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
		//Compute the starting division
		int exp;
		for (exp=1;max/exp>0;exp*=RADIX);
		exp /= RADIX;
		
		msdSort(0, a.getSize()-1, exp);
	}
	
	private void msdSort(int min, int max, int exp) throws InterruptedException
	{
		if (exp <= 0 || min >= max) return;
		int size = max - min + 1;
		int[] counts = new int[RADIX];
		Arrays.fill(counts, 0);
		
		a.mark(min);
		a.mark(max);
		
		for (int i=min;i<=max;i++)
		{
			counts[(a.get(i)/exp) % RADIX]++;
			sleep();
		}
		
		//Computing the positions
		int[] starting = new int[RADIX];
		for (int i=1;i<RADIX;i++) counts[i] += counts[i-1];
		for (int i=0;i<RADIX;i++)
		{
			starting[i] = counts[i]-1+min;
		}
		
		//Building the sorted array
		int[] tmp = new int[size];
		for (int i=max;i>=min;i--)
		{
			tmp[counts[(a.getSilent(i)/exp) % RADIX]-1] = a.getSilent(i);
			counts[(a.getSilent(i)/exp) % RADIX]--;
		}
		
		//Copying to visible array
		for (int i=min;i<=max;i++)
		{
			a.set(i, tmp[i-min]);
			sleep();
		}
		
		a.unmark(min);
		a.unmark(max);
		
		//Recursive sorting
		if (exp > 1)
		{			
			msdSort(min, starting[0], exp/RADIX);
			for (int i=1;i<RADIX;i++)
			{
				msdSort(starting[i-1]+1, starting[i], exp/RADIX);
			}
		}
	}

}
