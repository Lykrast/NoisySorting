package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterBubble extends SorterAbstract {
	public SorterBubble(VisualArray array)
	{
		super(array);
	}

	@Override
	protected void sort() throws InterruptedException {
		int size = a.getSize();
		boolean sorted;
		
		do {
			sorted = true;
			
			for (int i=0;i<size-1;i++)
			{
				if (a.get(i) > a.get(i+1))
				{
					sorted = false;
					a.swap(i, i+1);
				}
				sleep();
			}
		}while (!sorted);
	}

}
