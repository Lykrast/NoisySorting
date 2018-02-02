package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterSelectionRecursive extends SorterAbstract {
	private static final int BASE_TRESHOLD = 64;
	
	public SorterSelectionRecursive(VisualArray array)
	{
		super(array);
	}

	// http://pramodganapathi.com/doc/Sorting%20Algorithms.pdf
	@Override
	protected void sort() throws InterruptedException {
		selectionSort(0, a.getSize()-1);
	}
	
	private void selectionSort(int l, int h) throws InterruptedException {
		if (l > h) return;
		if (h - l < BASE_TRESHOLD)
		{
			for (int i=l;i<h;i++)
			{
				int min = i;
				for (int j=i+1;j<=h;j++)
				{
					if (a.get(j) < a.get(min)) min = j;
					sleep();
				}
				a.swap(i, min);
				sleep();
			}
		}
		else
		{
			int m = (l + h)/2;
			partition(l, m, m+1, h);
			selectionSort(l, m);
			selectionSort(m+1, h);
		}
	}
	
	private void partition(int ll, int lh, int rl, int rh) throws InterruptedException {
		if (ll > lh || rl > rh) return;
		if (lh - ll < BASE_TRESHOLD)
		{
			for (int i=ll;i<=lh;i++)
			{
				int min = i;
				for (int j=rl;j<=rh;j++)
				{
					if (a.get(j) < a.get(min)) min = j;
					sleep();
				}
				a.swap(i, min);
				sleep();
			}
		}
		else
		{
			int lm = (ll + lh)/2;
			int rm = (rl + rh)/2;
			partition(ll, lm, rl, rm);
			partition(lm+1, lh, rm+1, rh);
			partition(ll, lm, rm+1, rh);
			partition(lm+1, lh, rl, rm);
		}
	}

}
