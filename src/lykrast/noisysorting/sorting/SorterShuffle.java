package lykrast.noisysorting.sorting;

import java.util.Arrays;

import lykrast.noisysorting.array.VisualArray;

public class SorterShuffle extends SorterAbstract {
	private static final int KEY_NUMBER = 8;
	
	public SorterShuffle(VisualArray array)
	{
		super(array);
	}

	@Override
	protected void sort() throws InterruptedException {
		shuffleSort(0, a.getSize()-1);
	}
	
	private void shuffleSort(int min, int max) throws InterruptedException
	{
		if (min >= max) return;
		int size = max - min + 1;
		
		int keyN = (size)/KEY_NUMBER;
		if (keyN < 1) keyN = 1;
		
		shuffleSort(min, min + keyN - 1);
		
		for (int i=min;i<min+keyN;i++) a.mark(i);
		
		int[][] buckets = new int[keyN+1][size];
		int[] pointers = new int[keyN+1];
		Arrays.fill(pointers, 0);

		//Fill the buckets
		for (int i=min+keyN;i<=max;i++)
		{			
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
			for (int j=0;j<pointers[i];j++)
			{
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
			a.set(pos, buckets[keyN][j]);
			pos++;
			sleep();
		}

		shuffleSort(min, keyPos[0]-1);
		for (int i=0;i<keyN-1;i++)
		{
			shuffleSort(keyPos[i]+1, keyPos[i+1]-1);
			a.unmark(keyPos[i]);
		}
		shuffleSort(keyPos[keyN-1]+1, max);
		a.unmark(keyPos[keyN-1]);
	}

}
