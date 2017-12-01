package lykrast.noisysorting.sorting.sort;

import java.util.Arrays;

import lykrast.noisysorting.sorting.VisualArray;

public class SorterJ extends SorterAbstract {
	private static final int KEY_NUMBER = 8;
	private static final int STRAND_TRESHOLD = 35;
	
	public SorterJ(VisualArray array)
	{
		super(array);
	}

	@Override
	protected Object doInBackground() throws Exception {
		jSort(0, a.getSize()-1);
		a.sortFinished();
		return null;
	}
	
	private void jSort(int min, int max)
	{
		if (min >= max || isCancelled()) return;
		int size = max - min + 1;
		if (size == 2)
		{
			if (a.get(min) > a.get(max)) a.swap(min, max);
		}
		else if (size <= STRAND_TRESHOLD) strandSort(min, max);
		else shuffleSort(min, max);
	}
	
	private void strandSort(int min, int max)
	{
		int size = max - min + 1;
		int pos = min;
		for (int i=min+1;i<size;i++)
		{
			//Mid-sort cancel
			if (isCancelled())
			{
				a.sortFinished();
				return;
			}
			
			if (a.get(i) >= a.get(pos))
			{
				pos++;
				//a.swap(i, pos);
				//Cheaty way of visualizing the list extraction/insertion
				int j = i;
				while (j > pos)
				{
					a.swapSilent(j, j-1);
					j--;
				}
				//Flash it for visualization
				a.get(pos);
			}
			sleep();
		}
		
		while (pos < min+size-1)
		{
			int pos2 = pos+1;
			for (int i=pos+2;i<min+size;i++)
			{
				//Mid-sort cancel
				if (isCancelled())
				{
					a.sortFinished();
					return;
				}
				
				if (a.get(i) >= a.get(pos2))
				{
					pos2++;
					//a.swap(i, pos2);
					//Cheaty way of visualizing the list extraction/insertion
					int j = i;
					while (j > pos2)
					{
						a.swapSilent(j, j-1);
						j--;
					}
					//Flash it for visualization
					a.get(pos2);
				}
				sleep();
			}
			a.mark(min);
			a.mark(pos2);
			a.mark(pos);
			a.mark(pos+1);
			int[] temp = new int[pos2 + 1];
			int pointerL = min, pointerR = pos+1;
			for (int i=min;i<=pos2;i++)
			{
				//Mid-sort cancel
				if (isCancelled())
				{
					a.sortFinished();
					return;
				}
				if (pointerL <= pos && (pointerR > pos2 || a.get(pointerL) <= a.get(pointerR)))
				{
					temp[i] = a.get(pointerL);
					pointerL++;
				}
				else
				{
					temp[i] = a.get(pointerR);
					pointerR++;
				}
				sleep();
			}
			a.unmark(pos);
			a.unmark(pos+1);
			
			for (int i=min;i<=pos2;i++)
			{
				//Mid-sort cancel
				if (isCancelled())
				{
					a.sortFinished();
					return;
				}
				
				a.set(i, temp[i]);
				sleep();
			}
			a.unmark(min);
			a.unmark(pos2);
			
			pos = pos2;
		}
	}
	
	private void shuffleSort(int min, int max)
	{
		int size = max - min + 1;
		int keyN = (size)/KEY_NUMBER;
		if (keyN < 1) keyN = 1;
		
		jSort(min, min + keyN - 1);
		
		for (int i=min;i<min+keyN;i++) a.mark(i);
		
		int[][] buckets = new int[keyN+1][size];
		int[] pointers = new int[keyN+1];
		Arrays.fill(pointers, 0);

		//Fill the buckets
		for (int i=min+keyN;i<=max;i++)
		{
			//Mid-sort cancel
			if (isCancelled()) return;
			
			//First bucket
			if (a.get(i) < a.get(min))
			{
				buckets[0][pointers[0]] = a.getSilent(i);
				pointers[0]++;
				sleep();
			}
			else if (a.get(i) > a.get(min + keyN - 1))
			{
				buckets[keyN][pointers[keyN]] = a.getSilent(i);
				pointers[keyN]++;
				sleep();
			}
			else
			{
				int minK = 0;
				int maxK = keyN - 1;
				int j = maxK - minK;
				
				while (j > 1)
				{
					//Mid-sort cancel
					if (isCancelled()) return;
					
					int midK = (minK + maxK)/2;
					
					if (a.get(i) < a.get(min+midK))
					{
						maxK = midK;
					}
					else
					{
						minK = midK;
					}
					j = maxK - minK;
					sleep();
				}
				buckets[minK+1][pointers[minK+1]] = a.getSilent(i);
				pointers[minK+1]++;
				sleep();
			}
		}
		
		int[] keys = new int[keyN];
		int[] keyPos = new int[keyN];
		for (int i=min;i<min+keyN;i++)
		{
			a.unmark(i);
			keys[i-min]=a.getSilent(i);
		}

		int pos = min;
		for (int i=0;i<keyN;i++)
		{
			//Mid-sort cancel
			if (isCancelled()) return;
			
			for (int j=0;j<pointers[i];j++)
			{
				//Mid-sort cancel
				if (isCancelled()) return;
				
				a.set(pos, buckets[i][j]);
				pos++;
				sleep();
			}
			keyPos[i] = pos;
			a.mark(pos);
			a.set(pos, keys[i]);
			pos++;
			sleep();
		}
		
		for (int j=0;j<pointers[keyN];j++)
		{
			//Mid-sort cancel
			if (isCancelled()) return;
			
			a.set(pos, buckets[keyN][j]);
			pos++;
			sleep();
		}

		jSort(min, keyPos[0]-1);
		for (int i=0;i<keyN-1;i++)
		{
			//Mid-sort cancel
			if (isCancelled()) return;

			jSort(keyPos[i]+1, keyPos[i+1]-1);
			a.unmark(keyPos[i]);
		}
		jSort(keyPos[keyN-1]+1, max);
		a.unmark(keyPos[keyN-1]);
	}

}
