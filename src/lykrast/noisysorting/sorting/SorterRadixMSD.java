package lykrast.noisysorting.sorting;

import java.util.Arrays;

import lykrast.noisysorting.array.VisualArray;

public class SorterRadixMSD extends SorterAbstract {
	private int radix = 4;
	
	public SorterRadixMSD(VisualArray array, int radix)
	{
		super(array);
		this.radix = radix;
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
		for (exp=1;max/exp>0;exp*=radix);
		exp /= radix;
		
		msdSort(0, a.getSize()-1, exp);
	}
	
	private void msdSort(int min, int max, int exp) throws InterruptedException
	{
		if (exp <= 0 || min >= max) return;
		int size = max - min + 1;
		int[] counts = new int[radix];
		Arrays.fill(counts, 0);
		
		a.mark(min);
		a.mark(max);
		
		for (int i=min;i<=max;i++)
		{
			counts[(a.get(i)/exp) % radix]++;
			sleep();
		}
		
		//Computing the positions
		int[] starting = new int[radix];
		for (int i=1;i<radix;i++) counts[i] += counts[i-1];
		for (int i=0;i<radix;i++)
		{
			starting[i] = counts[i]-1+min;
		}
		
		//Building the sorted array
		int[] tmp = new int[size];
		for (int i=max;i>=min;i--)
		{
			tmp[counts[(a.getSilent(i)/exp) % radix]-1] = a.getSilent(i);
			counts[(a.getSilent(i)/exp) % radix]--;
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
			msdSort(min, starting[0], exp/radix);
			for (int i=1;i<radix;i++)
			{
				msdSort(starting[i-1]+1, starting[i], exp/radix);
			}
		}
	}

}
