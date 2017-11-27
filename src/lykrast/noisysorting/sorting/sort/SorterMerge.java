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
		if (min >= max || isCancelled()) return;
		int middle = (min+max)/2;
		mergeSort(min,middle);
		mergeSort(middle+1,max);
		a.mark(min);
		a.mark(max);
		a.mark(middle);
		a.mark(middle+1);
		int[] temp = new int[max - min + 1];
		int pointerL = min, pointerR = middle+1;
		for (int i=min;i<=max;i++)
		{
			//Mid-sort cancel
			if (isCancelled())
			{
				a.sortFinished();
				return;
			}
			if (pointerL <= middle && (pointerR > max || a.get(pointerL) <= a.get(pointerR)))
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
		a.unmark(middle);
		a.unmark(middle+1);
		
		for (int i=min;i<=max;i++)
		{
			//Mid-sort cancel
			if (isCancelled())
			{
				a.sortFinished();
				return;
			}
			
			a.set(i, temp[i-min]);
			sleep();
		}
		a.unmark(min);
		a.unmark(max);
	}

}
