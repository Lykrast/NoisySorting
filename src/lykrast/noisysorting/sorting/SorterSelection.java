package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterSelection extends SorterAbstract {
	public SorterSelection(VisualArray array)
	{
		super(array);
	}

	@Override
	protected void sort() throws InterruptedException {
		int size = a.getSize();
		
		for (int i=0;i<size;i++)
		{
			int minIndex = i;
			
			for (int j=i;j<size;j++)
			{				
				if (a.get(j) < a.get(minIndex))
				{
					minIndex = j;
				}
				sleep();
			}
			
			a.swap(i, minIndex);
			sleep();
		}
	}

}
