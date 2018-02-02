package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterGnome extends SorterAbstract {
	public SorterGnome(VisualArray array)
	{
		super(array);
	}

	@Override
	protected void sort() throws InterruptedException {
		int size = a.getSize();
		int pos = 0;
		
		while (pos < size)
		{			
			if (pos == 0 || a.get(pos) >= a.get(pos-1))
			{
				pos++;
			}
			else
			{
				a.swap(pos, pos-1);
				pos--;
			}
			sleep();
		}
	}

}
