package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterStrand extends SorterAbstract {
	public SorterStrand(VisualArray array)
	{
		super(array);
	}

	@Override
	protected void sort() throws InterruptedException {
		int size = a.getSize();
		int pos = 0;
		for (int i=1;i<size;i++)
		{
			if (a.get(i) >= a.get(pos))
			{
				pos++;
				//a.swap(i, pos);
				//Cheaty way of visualizing the list extraction/insertion
				int j = i;
				while (j > pos)
				{
					a.swapSilent(j, j-1);
					j--;
				}
				//Flash it for visualization
				a.get(pos);
			}
			sleep();
		}
		
		while (pos < size-1)
		{
			int pos2 = pos+1;
			for (int i=pos+2;i<size;i++)
			{
				if (a.get(i) >= a.get(pos2))
				{
					pos2++;
					//a.swap(i, pos2);
					//Cheaty way of visualizing the list extraction/insertion
					int j = i;
					while (j > pos2)
					{
						a.swapSilent(j, j-1);
						j--;
					}
					//Flash it for visualization
					a.get(pos2);
				}
				sleep();
			}
			a.mark(0);
			a.mark(pos2);
			a.mark(pos);
			a.mark(pos+1);
			int[] temp = new int[pos2 + 1];
			int pointerL = 0, pointerR = pos+1;
			for (int i=0;i<=pos2;i++)
			{
				if (pointerL <= pos && (pointerR > pos2 || a.get(pointerL) <= a.get(pointerR)))
				{
					temp[i] = a.get(pointerL);
					pointerL++;
				}
				else
				{
					temp[i] = a.get(pointerR);
					pointerR++;
				}
				sleep();
			}
			a.unmark(pos);
			a.unmark(pos+1);
			
			for (int i=0;i<=pos2;i++)
			{
				a.set(i, temp[i]);
				sleep();
			}
			a.unmark(0);
			a.unmark(pos2);
			
			pos = pos2;
		}
	}

}
