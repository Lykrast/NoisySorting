package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterPermutation extends SorterAbstract {
	public SorterPermutation(VisualArray array)
	{
		super(array);
	}

	@Override
	protected void sort() throws InterruptedException {
		permute(0, a.getSize());
	}
	
	// https://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
	private boolean permute(int start, int end) throws InterruptedException {
		if (start+1 >= end) return a.isSorted();
		
		for (int i=start;i<end;i++)
		{
			a.swap(start, i);
			sleep();
			if (permute(start+1, end)) return true;
			a.swap(start, i);
			sleep();
		}
		
		return false;
	}

}
