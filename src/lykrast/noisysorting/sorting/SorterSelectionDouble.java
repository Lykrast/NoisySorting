package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterSelectionDouble extends SorterAbstract {
	public SorterSelectionDouble(VisualArray array)
	{
		super(array);
	}

	@Override
	protected void sort() throws InterruptedException {
		int size = a.getSize();
		
		for (int i=0, s=size-1;i<s;i++,s--)
		{
			int minIndex = i;
			int maxIndex = i;
			
			for (int j=i;j<=s;j++)
			{				
				if (a.get(j) < a.get(minIndex))
				{
					minIndex = j;
				}
				if (a.get(j) > a.get(maxIndex))
				{
					maxIndex = j;
				}
				sleep();
			}
			
			a.swap(i, minIndex);
			//Ensuring maxIndex still points to the same value
			if (maxIndex == i) maxIndex = minIndex;
			else if (maxIndex == minIndex) maxIndex = i;
			a.swap(s, maxIndex);
			sleep();
		}
	}

}
