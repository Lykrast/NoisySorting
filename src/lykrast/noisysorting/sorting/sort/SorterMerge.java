package lykrast.noisysorting.sorting.sort;

import lykrast.noisysorting.sorting.VisualArray;

public class SorterMerge extends SorterAbstract {
	public SorterMerge(VisualArray array)
	{
		super(array);
	}

	@Override
	protected Object doInBackground() throws Exception {
		mergeSort(0, a.getSize()-1);
		a.sortFinished();
		return null;
	}
	
	private void mergeSort(int min, int max)
	{
		//Mid-sort cancel
		if (max - min < 2 || isCancelled()) return;
		int middle = (min+max)/2;
		mergeSort(min,middle);
		mergeSort(middle+1,max);
		int[] temp = new int[min - max];
		int pointerL = min, pointerR = middle;
		for (int i=min;i<=max;i++)
		{
			if (i < middle && (pointerR >= max || a.get(pointerL) <= a.get(pointerR)))
			{
				temp[i-min] = a.getSilent(pointerL);
				pointerL++;
			}
			else
			{
				temp[i-min] = a.getSilent(pointerR);
				pointerR++;
			}
			sleep();
		}
		
		for (int i=min;i<=max;i++)
		{
			a.set(i, temp[i-min]);
			sleep();
		}
	}

}
