package lykrast.noisysorting.sorting;

import java.util.Arrays;

import lykrast.noisysorting.array.VisualArray;

public class SorterAmericanFlag extends SorterAbstract {
	private static final int RADIX = 4;
	
	public SorterAmericanFlag(VisualArray array)
	{
		super(array);
	}

	//From https://en.wikipedia.org/wiki/American_flag_sort
	@Override
	protected void sort() throws InterruptedException {
		//Finding the maximum
		int maxIndex = 0;
		for (int i=0;i<a.getSize();i++)
		{			
			if (a.get(i) > a.get(maxIndex)) maxIndex = i;
			sleep();
		}
		
		int digit = (int)Math.floor(Math.log(a.getSilent(maxIndex))/Math.log(RADIX));
		americanFlagSort(0, a.getSize(), digit);
	}
	
	private void americanFlagSort(int start, int end, int digit) throws InterruptedException
	{
		if (start > end || start >= a.getSize()) return;
		int[] offsets = computeOffsets(start, end, digit);
		americanSwap(offsets, start, end, digit);
		if (digit > 0)
		{
			for (int i=0;i<RADIX-1;i++)
			{
				markValid(offsets[i]+start);
				markValid(offsets[i+1]+start-1);
			}
			markValid(offsets[RADIX-1]+start);
			markValid(end-1);
			
			for (int i=0;i<RADIX-1;i++)
			{
				americanFlagSort(offsets[i]+start, offsets[i+1]+start, digit-1);
				unmarkValid(offsets[i]+start);
				unmarkValid(offsets[i+1]+start-1);
			}
			americanFlagSort(offsets[RADIX-1]+start, end, digit-1);
			unmarkValid(offsets[RADIX-1]+start);
			unmarkValid(end-1);
		}
	}
	
	private void markValid(int index)
	{
		if (index < a.getSize()) a.mark(index);
	}
	
	private void unmarkValid(int index)
	{
		if (index < a.getSize()) a.unmark(index);
	}
	
	private int[] computeOffsets(int start, int end, int digit) throws InterruptedException
	{
		int[] counts = new int[RADIX];
		Arrays.fill(counts, 0);
		
		for (int i=start;i<end;i++)
		{
			counts[getRadixVal(a.get(i), digit)]++;
			sleep();
		}
		
		int[] offsets = new int[RADIX];
		int sum = 0;
		for (int i=0;i<RADIX;i++)
		{
			offsets[i] = sum;
			sum += counts[i];
		}
		
		return offsets;
	}
	
	private void americanSwap(int[] offsets, int start, int end, int digit) throws InterruptedException
	{
		if (offsets == null) return;
		int i = start;
		int[] nextFree = Arrays.copyOf(offsets, RADIX);
		int currentBlock = 0;
		while (currentBlock < RADIX-1)
		{
			if (i >= start + offsets[currentBlock+1])
			{
				currentBlock++;
				continue;
			}
			
			int val = getRadixVal(a.get(i), digit);
			sleep();
			if (val == currentBlock)
			{
				i++;
				continue;
			}
			
			int swap = nextFree[val] + start;
			a.swap(i, swap);
			nextFree[val]++;
			sleep();
		}
	}
	
	private int getRadixVal(int x, int digit)
	{
		return (int)Math.floor(x/Math.pow(RADIX, digit)) % RADIX;
	}

}
