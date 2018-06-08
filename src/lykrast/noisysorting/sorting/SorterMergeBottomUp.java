package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterMergeBottomUp extends SorterAbstract {
	public SorterMergeBottomUp(VisualArray array)
	{
		super(array);
	}

	//Mostly from https://www.akawebdesign.com/2012/04/13/javascript-mergesort-top-down-vs-bottom-up/
	@Override
	protected void sort() throws InterruptedException {
		int size = a.getSize();
		for (int s=1;s<size;s*=2)
		{
			for (int min=0;min<size-s;min+=s*2)
			{				
				int mid = min + s - 1;
				int max = Math.min(min + s*2 - 1, size-1);
				merge(min, mid, max);
			}
		}
	}
	
	//Merge [min,mid] with [mid+1,max]
	private void merge(int min, int mid, int max) throws InterruptedException
	{
		if (min >= max) return;
		a.mark(min);
		a.mark(max);
		a.mark(mid);
		a.mark(mid+1);
		int[] temp = new int[max - min + 1];
		int pointerL = min, pointerR = mid+1;
		for (int i=min;i<=max;i++)
		{
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
			a.set(i, temp[i-min]);
			sleep();
		}
		a.unmark(min);
		a.unmark(max);
	}

}
