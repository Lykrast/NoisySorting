package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterMergeInPlace extends SorterAbstract {
	public SorterMergeInPlace(VisualArray array)
	{
		super(array);
	}

	//https://github.com/liuxinyu95/AlgoXY/blob/algoxy/sorting/merge-sort/src/mergesort.c
	@Override
	protected void sort() throws InterruptedException {
		imsort(0, a.getSize());
	}
	
	private void imsort(int l, int u) throws InterruptedException
	{
		//Mid-sort cancel
		if (u - l <= 1) return;
		int m = l + (u - l) / 2;
		int w = l + u - m;
		
		wsort(l, m, w);
		while (w - l > 2)
		{
			int n = w;
			w = l + (n - l + 1) / 2;
			wsort(w, n, l);
			wmerge(l, l + n - w, n, u, w);
		}
		for (int n = w; n > l; n--)
		{
			for (m = n; m < u && a.get(m) < a.get(m-1); m++)
			{
				a.swap(m, m-1);
				sleep();
			}
		}
	}
	
	//Sort [l, u[ and put result in w
	private void wsort(int l, int u, int w) throws InterruptedException
	{
		if (u - l > 1)
		{
			int m = l + (u - l) / 2;
			imsort(l, m);
			imsort(m, u);
			wmerge(l, m, m, u, w);
		}
		else
		{
			while (l < u)
			{
				a.swap(l, w);
				l++;
				w++;
				sleep();
			}
		}
	}
	
	//Merge [i, m[ and [j, n[ into [w...]
	private void wmerge(int i, int m, int j, int n, int w) throws InterruptedException
	{
		while (i < m && j < n)
		{
			if (a.get(i) < a.get(j))
			{
				a.swap(i, w);
				i++;
			}
			else
			{
				a.swap(j, w);
				j++;
			}
			w++;
			sleep();
		}
		while (i < m)
		{
			a.swap(i, w);
			i++;
			w++;
			sleep();
		}
		while (j < n)
		{
			a.swap(j, w);
			j++;
			w++;
			sleep();
		}
	}

}
