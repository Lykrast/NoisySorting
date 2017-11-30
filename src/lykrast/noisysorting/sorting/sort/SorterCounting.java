package lykrast.noisysorting.sorting.sort;

import java.util.Arrays;

import lykrast.noisysorting.sorting.VisualArray;

public class SorterCounting extends SorterAbstract {
	public SorterCounting(VisualArray array)
	{
		super(array);
	}

	@Override
	protected Object doInBackground() throws Exception {
		int size = a.getSize();
		//Finding the maximum
		int maxIndex = 0;
		for (int i=0;i<size;i++)
		{
			//Mid-sort cancel
			if (isCancelled())
			{
				a.sortFinished();
				return null;
			}
			
			if (a.get(i) > a.get(maxIndex)) maxIndex = i;
			sleep();
		}
		
		//Counting the numbers
		int[] counts = new int[a.getSilent(maxIndex)+1];
		Arrays.fill(counts, 0);
		
		for (int i=0;i<size;i++)
		{
			//Mid-sort cancel
			if (isCancelled())
			{
				a.sortFinished();
				return null;
			}
			
			counts[a.get(i)]++;
			sleep();
		}
		
		//Computing the positions
		for (int i=1;i<counts.length;i++) counts[i] += counts[i-1];
		
		//Building the sorted array
		int[] tmp = new int[size];
		for (int i=0;i<size;i++)
		{
			tmp[counts[a.getSilent(i)]-1] = a.getSilent(i);
			counts[a.getSilent(i)]--;
		}
		
		//Copying to visible array
		for (int i=0;i<size;i++)
		{
			//Mid-sort cancel
			if (isCancelled())
			{
				a.sortFinished();
				return null;
			}
			
			a.set(i, tmp[i]);
			sleep();
		}

		a.sortFinished();
		return null;
	}

}
