package lykrast.noisysorting.sorting;

import java.util.Arrays;

import lykrast.noisysorting.array.VisualArray;

public class SorterAmericanFlag extends SorterAbstract {
	private int radix;
	
	public SorterAmericanFlag(VisualArray array, int radix)
	{
		super(array);
		this.radix = radix;
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
		
		int digit = (int)Math.floor(Math.log(a.getSilent(maxIndex))/Math.log(radix));
		americanFlagSort(0, a.getSize(), digit);
	}
	
	private void americanFlagSort(int start, int end, int digit) throws InterruptedException
	{
		if (start > end || start >= a.getSize()) return;
		int[] offsets = computeOffsets(start, end, digit);
		americanSwap(offsets, start, end, digit);
		if (digit > 0)
		{
			for (int i=0;i<radix-1;i++)
			{
				markValid(offsets[i]+start);
				markValid(offsets[i+1]+start-1);
			}
			markValid(offsets[radix-1]+start);
			markValid(end-1);
			
			for (int i=0;i<radix-1;i++)
			{
				americanFlagSort(offsets[i]+start, offsets[i+1]+start, digit-1);
				unmarkValid(offsets[i]+start);
				unmarkValid(offsets[i+1]+start-1);
			}
			americanFlagSort(offsets[radix-1]+start, end, digit-1);
			unmarkValid(offsets[radix-1]+start);
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
		int[] counts = new int[radix];
		Arrays.fill(counts, 0);
		
		for (int i=start;i<end;i++)
		{
			counts[getRadixVal(a.get(i), digit)]++;
			sleep();
		}
		
		int[] offsets = new int[radix];
		int sum = 0;
		for (int i=0;i<radix;i++)
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
		int[] nextFree = Arrays.copyOf(offsets, radix);
		int currentBlock = 0;
		while (currentBlock < radix-1)
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
		return (int)Math.floor(x/Math.pow(radix, digit)) % radix;
	}

}
