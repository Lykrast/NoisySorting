package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterBubbleRecursive extends SorterAbstract {
	private static final int BASE_TRESHOLD = 64;
	
	public SorterBubbleRecursive(VisualArray array)
	{
		super(array);
	}

	// http://pramodganapathi.com/doc/Sorting%20Algorithms.pdf
	@Override
	protected void sort() throws InterruptedException {
		bubbleSort(0, a.getSize()-1);
	}
	
	private void bubbleSort(int l, int h) throws InterruptedException {
		if (l > h) return;
		if (h - l < BASE_TRESHOLD)
		{
			for (int i=l;i<=h;i++)
			{
				for (int j=l;j<l+h-i;j++)
				{
					if (a.get(j) > a.get(j+1)) a.swap(j, j+1);
					sleep();
				}
			}
		}
		else
		{
			int m = (l + h)/2;
			partition(l, m, m+1, h);
			bubbleSort(l, m);
			bubbleSort(m+1, h);
		}
	}
	
	private void partition(int ll, int lh, int rl, int rh) throws InterruptedException {
		if (ll > lh || rl > rh) return;
		if (lh - ll < BASE_TRESHOLD)
		{
			for (int i=rl;i<=rh;i++)
			{
				for (int j=ll;j<lh;j++)
				{
					if (a.get(j) > a.get(j+1)) a.swap(j, j+1);
					sleep();
				}
				if (a.get(lh) > a.get(i)) a.swap(lh, i);
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
