package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterRadixMSDInPlace extends SorterAbstract {
	public SorterRadixMSDInPlace(VisualArray array)
	{
		super(array);
	}

	@Override
	protected Object doInBackground() throws Exception {
		//Finding the maximum
		int maxIndex = 0;
		for (int i=0;i<a.getSize();i++)
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
		
		int max = a.getSilent(maxIndex);
		//Compute the starting division
		int exp;
		for (exp=1;max/exp>0;exp*=2);
		exp /= 2;
		
		msdSort(0, a.getSize()-1, exp);

		//a.sortFinished();
		return null;
	}
	
	private void msdSort(int min, int max, int exp)
	{
		if (exp <= 0 || min >= max) return;
		int pointerL = min, pointerR = max;
		System.out.println(min + " - " + max + " | " + exp);
		
		a.mark(pointerR);
		while (pointerL <= pointerR)
		{
			if ((a.get(pointerL)/exp) % 2 == 1)
			{
				a.unmark(pointerR);
				a.swap(pointerL, pointerR);
				pointerR--;
				a.mark(pointerR);
			}
			else pointerL++;
			sleep();
		}
		a.unmark(pointerR);
		
		a.mark(min);
		a.mark(pointerL-1);
		//Workaround to when there's nothing in the 1s bucket
		if (pointerL < max) a.mark(pointerL);
		a.mark(max);
		
		msdSort(min, pointerL-1, exp/2);
		a.unmark(min);
		a.unmark(pointerL-1);
		msdSort(pointerL, max, exp/2);
		if (pointerL < max) a.unmark(pointerL);
		a.unmark(max);
	}

}
