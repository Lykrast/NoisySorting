package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterMerge3Way extends SorterAbstract {
	public SorterMerge3Way(VisualArray array)
	{
		super(array);
	}

	//Mostly from http://www.geeksforgeeks.org/3-way-merge-sort/
	@Override
	protected void sort() throws InterruptedException {
		mergeSort3(0, a.getSize()-1);
	}
	
	private void mergeSort3(int min, int max) throws InterruptedException
	{
		int size = max - min + 1;
		int mid1 = min + (size/3);
		int mid2 = min + 2 * (size/3) + 1;
		//No idea why it sometimes gets out of the array, but that fixes it
		mid1 = Math.max(1, mid1);
		mid2 = Math.min(a.getSize()-1, mid2);
		mergeSort3(min,mid1-1);
		mergeSort3(mid1,mid2-1);
		mergeSort3(mid2,max);
		a.mark(min);
		a.mark(mid1-1);
		a.mark(mid1);
		a.mark(mid2-1);
		a.mark(mid2);
		a.mark(max);
		
		int[] temp = new int[size];
		//Left, Middle, Right and Temp
		int pL = min, pM = mid1, pR = mid2, pT = 0;
		//Merge 3
		while (pL < mid1 && pM < mid2 && pR <= max)
		{
			if (a.get(pL) < a.get(pM))
			{
				if (a.get(pL) < a.get(pR)) temp[pT++] = a.get(pL++);
				else temp[pT++] = a.get(pR++);
			}
			else
			{
				if (a.get(pM) < a.get(pR)) temp[pT++] = a.get(pM++);
				else temp[pT++] = a.get(pR++);
			}
			sleep();
		}
		
		//Merge remaining of 1 and 2
		while (pL < mid1 && pM < mid2)
		{			
			if (a.get(pL) < a.get(pM)) temp[pT++] = a.get(pL++);
			else temp[pT++] = a.get(pM++);
			sleep();
		}
		
		//Merge remaining of 2 and 3
		while (pM < mid2 && pR <= max)
		{
			if (a.get(pM) < a.get(pR)) temp[pT++] = a.get(pM++);
			else temp[pT++] = a.get(pR++);
			sleep();
		}
		
		//Merge remaining of 1 and 3
		while (pL < mid1 && pR <= max)
		{
			if (a.get(pL) < a.get(pR)) temp[pT++] = a.get(pL++);
			else temp[pT++] = a.get(pR++);
			sleep();
		}
		
		//Merge remaining of 1
		while (pL < mid1)
		{
			temp[pT++] = a.get(pL++);
			sleep();
		}
		
		//Merge remaining of 2
		while (pM < mid2)
		{
			temp[pT++] = a.get(pM++);
			sleep();
		}
		
		//Merge remaining of 3
		while (pR <= max)
		{
			temp[pT++] = a.get(pR++);
			sleep();
		}
		
		a.unmark(mid1-1);
		a.unmark(mid1);
		a.unmark(mid2-1);
		a.unmark(mid2);
		//Copy the temp array back in the main one
		for (int i=0;i<size;i++)
		{
			a.set(i+min, temp[i]);
			sleep();
		}
		
		a.unmark(min);
		a.unmark(max);
	}

}
