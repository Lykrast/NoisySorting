package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterMergeBottomUp extends SorterAbstract {
	public SorterMergeBottomUp(VisualArray array)
	{
		super(array);
	}

	//Mostly from https://www.akawebdesign.com/2012/04/13/javascript-mergesort-top-down-vs-bottom-up/
	@Override
	protected Object doInBackground() throws Exception {
		int size = a.getSize();
		for (int s=1;s<size;s*=2)
		{
			for (int min=0;min<size-s;min+=s*2)
			{
				//Mid-sort cancel
				if (isCancelled())
				{
					a.sortFinished();
					return null;
				}
				
				int mid = min + s - 1;
				int max = Math.min(min + s*2 - 1, size-1);
				merge(min, mid, max);
			}
		}
		
		a.sortFinished();
		return null;
	}
	
	//Merge [min,mid] with [mid+1,max]
	private void merge(int min, int mid, int max)
	{
		//Mid-sort cancel
		if (min >= max || isCancelled()) return;
		a.mark(min);
		a.mark(max);
		a.mark(mid);
		a.mark(mid+1);
		int[] temp = new int[max - min + 1];
		int pointerL = min, pointerR = mid+1;
		for (int i=min;i<=max;i++)
		{
			//Mid-sort cancel
			if (isCancelled()) return;
			if (pointerL <= mid && (pointerR > max || a.get(pointerL) <= a.get(pointerR)))
			{
				temp[i-min] = a.get(pointerL);
				pointerL++;
			}
			else
			{
				temp[i-min] = a.get(pointerR);
				pointerR++;
			}
			sleep();
		}
		a.unmark(mid);
		a.unmark(mid+1);
		
		for (int i=min;i<=max;i++)
		{
			//Mid-sort cancel
			if (isCancelled()) return;
			
			a.set(i, temp[i-min]);
			sleep();
		}
		a.unmark(min);
		a.unmark(max);
	}

}
