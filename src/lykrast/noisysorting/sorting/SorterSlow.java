package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterSlow extends SorterAbstract {
	public SorterSlow(VisualArray array) {
		super(array);
	}

	@Override
	protected void sort() throws InterruptedException {
		slowSort(0, a.getSize() - 1);
	}

	private void slowSort(int min, int max) throws InterruptedException {
		// Mid-sort cancel
		if (min >= max) return;
		int middle = (min + max) / 2;
//		a.mark(max);
//		a.mark(middle);
		slowSort(min, middle);
		slowSort(middle + 1, max);
//		a.unmark(max);
//		a.unmark(middle);
		if (a.get(max) < a.get(middle)) a.swap(max, middle);
		sleep();
		slowSort(min, max - 1);
	}

}
