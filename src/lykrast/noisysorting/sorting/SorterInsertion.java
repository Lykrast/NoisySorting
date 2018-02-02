package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterInsertion extends SorterAbstract {
	public SorterInsertion(VisualArray array)
	{
		super(array);
	}

	@Override
	protected void sort() throws InterruptedException {
		int size = a.getSize();
		
		for (int i=1;i<size;i++)
		{
			a.mark(i);
			int j = i;
			
			while (j > 0 && a.get(j-1) > a.get(j))
			{
				a.swap(j, j-1);
				j--;
				sleep();
			}
			//Only sleep here if no change was made before
			if (j == i) sleep();
			a.unmark(i);
		}
	}

}
