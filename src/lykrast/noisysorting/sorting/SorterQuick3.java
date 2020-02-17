package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterQuick3 extends SorterAbstract {
	//https://www.toptal.com/developers/sorting-algorithms/quick-sort-3-way
	public SorterQuick3(VisualArray array) {
		super(array);
	}

	@Override
	protected void sort() throws InterruptedException {
		sort(0, a.getSize() - 1);
	}

	private void sort(int min, int max) throws InterruptedException {
		if (min >= max) return;
		
		//3-way partition
		int i = min, k = min, p = max;
		while (i < p) {
			if (a.get(i) < a.get(max)) {
				sleep();
				a.swap(i++, k++);
				sleep();
			}
			else {
				sleep();
				if (a.get(i) == a.get(max)) {
					sleep();
					a.swap(i, --p);
				}
				else i++;
				sleep();
			}
		}
		
		//move pivots to center
		int m = Math.min(p-k, max-p+1);
		for (int j = 0; j <= m-1; j++) {
			a.swap(k + j, max-m+1 + j);
		}

		//recursive sorts
		sort(min, k-1);
		sort(max-p+k+1, max);
	}

}
