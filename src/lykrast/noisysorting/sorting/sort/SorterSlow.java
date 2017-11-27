package lykrast.noisysorting.sorting.sort;

import lykrast.noisysorting.sorting.VisualArray;

public class SorterSlow extends SorterAbstract {
	public SorterSlow(VisualArray array)
	{
		super(array);
	}

	@Override
	protected Object doInBackground() throws Exception {
		slowSort(0, a.getSize()-1);
		a.sortFinished();
		return null;
	}
	
	private void slowSort(int min, int max)
	{
		//Mid-sort cancel
		if (min >= max || isCancelled()) return;
		int middle = (min+max)/2;
		a.mark(max);
		a.mark(middle);
		slowSort(min,middle);
		slowSort(middle+1,max);
		a.unmark(max);
		a.unmark(middle);
		if (a.get(max) < a.get(middle)) a.swap(max, middle);
		sleep();
		slowSort(min, max-1);
	}

}
