package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterMergeInPlace extends SorterAbstract {
	public SorterMergeInPlace(VisualArray array)
	{
		super(array);
	}

	//https://github.com/liuxinyu95/AlgoXY/blob/algoxy/sorting/merge-sort/src/mergesort.c
	@Override
	protected Object doInBackground() throws Exception {
		imsort(0, a.getSize());
		a.sortFinished();
		return null;
	}
	
	private void imsort(int l, int u)
	{
		//Mid-sort cancel
		if (u - l <= 1 || isCancelled()) return;
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
				//Mid-sort cancel
				if (isCancelled()) return;
				
				a.swap(m, m-1);
				sleep();
			}
		}
	}
	
	//Sort [l, u[ and put result in w
	private void wsort(int l, int u, int w)
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
				//Mid-sort cancel
				if (isCancelled()) return;
				
				a.swap(l, w);
				l++;
				w++;
				sleep();
			}
		}
	}
	
	//Merge [i, m[ and [j, n[ into [w...]
	private void wmerge(int i, int m, int j, int n, int w)
	{
		while (i < m && j < n)
		{
			//Mid-sort cancel
			if (isCancelled()) return;
			
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
			//Mid-sort cancel
			if (isCancelled()) return;
			
			a.swap(i, w);
			i++;
			w++;
			sleep();
		}
		while (j < n)
		{
			//Mid-sort cancel
			if (isCancelled()) return;
			
			a.swap(j, w);
			j++;
			w++;
			sleep();
		}
	}

}
