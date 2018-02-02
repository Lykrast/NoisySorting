package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterInsertionRecursive extends SorterAbstract {
	private static final int BASE_TRESHOLD = 64;
	
	public SorterInsertionRecursive(VisualArray array)
	{
		super(array);
	}

	// http://pramodganapathi.com/doc/Sorting%20Algorithms.pdf
	@Override
	protected void sort() throws InterruptedException {
		insertionSort(0, a.getSize()-1);
	}
	
	private void insertionSort(int l, int h) throws InterruptedException {
		if (l > h) return;
		if (h - l < BASE_TRESHOLD)
		{
			for (int i=l+1;i<=h;i++)
			{
				int j = i;
				boolean swapped = false; //visual thing
				while (j >= l+1 && a.get(j-1) > a.get(j))
				{
					swapped = true;
					a.swap(j-1, j);
					j--;
					sleep();
				}
				if (!swapped) sleep(); //only sleep if no sleep occured
			}
		}
		else
		{
			int m = (l + h)/2;
			insertionSort(l, m);
			insertionSort(m+1, h);
			merge(l, m, m+1, h);
		}
	}
	
	private void merge(int ll, int lh, int rl, int rh) throws InterruptedException {
		if (ll > lh || rl > rh) return;
		if (lh - ll < BASE_TRESHOLD)
		{
			if (a.get(lh) > a.get(rl))
			{
				sleep(); //sleep for the if comparaison
				for (int i=rl;i<=rh;i++)
				{
					int j = i;
					boolean swapped = false; //visual thing
					while (j >= rl+1 && a.get(j-1) > a.get(j))
					{
						swapped = true;
						a.swap(j-1, j);
						j--;
						sleep();
					}
					if (!swapped) sleep(); //only sleep if no sleep occured
					
					if (a.get(lh) > a.get(j))
					{
						sleep(); //sleep for the if comparaison
						a.swap(rl, lh);
						j = lh;
						swapped = false; //visual thing
						while (j >= ll+1 && a.get(j-1) > a.get(j))
						{
							swapped = true;
							a.swap(j-1, j);
							j--;
							sleep();
						}
						if (!swapped) sleep(); //only sleep if no sleep occured
					}
					else sleep(); //sleep for the if comparaison
				}
			}
			else sleep(); //sleep for the if comparaison
		}
		else
		{
			int lm = (ll + lh)/2;
			int rm = (rl + rh)/2;
			merge(ll, lm, rl, rm);
			merge(lm+1, lh, rm+1, rh);
			merge(lm+1, lh, rl, rm);
		}
	}

}
