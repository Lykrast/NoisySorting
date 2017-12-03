package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterCombInsertion extends SorterAbstract {
	private static final double SHRINK_FACTOR = 1.3;
	
	public SorterCombInsertion(VisualArray array)
	{
		super(array);
	}

	//Based on that one french Wikipedia article that I couldn't really find the source
	//Pretty sure I also saw something similar somewhere else but I lost the link
	//https://fr.wikipedia.org/wiki/Tri_%C3%A0_peigne#Comb_sort_se_terminant_par_une_autre_m.C3.A9thode_de_tri
	@Override
	protected Object doInBackground() throws Exception {
		int size = a.getSize();
		//Comb Sort
		int gap = size;
		
		do {
			gap = (int)Math.floor(gap / SHRINK_FACTOR);
			
			for (int i=0;i<size-gap;i++)
			{
				//Mid-sort cancel
				if (isCancelled())
				{
					a.sortFinished();
					return null;
				}
				
				if (a.get(i) > a.get(i+gap)) a.swap(i, i+gap);
				sleep();
			}
		}while (gap > 8);
		
		//Insertion sort once the gap is small enough
		for (int i=1;i<size;i++)
		{
			//Mid-sort cancel
			if (isCancelled())
			{
				a.sortFinished();
				return null;
			}
			
			a.mark(i);
			int j = i;
			
			while (j > 0 && a.get(j-1) > a.get(j))
			{
				//Mid-sort cancel
				if (isCancelled())
				{
					a.sortFinished();
					return null;
				}
				
				a.swap(j, j-1);
				j--;
				sleep();
			}
			//Only sleep here if no change was made before
			if (j == i) sleep();
			a.unmark(i);
		}
		
		a.sortFinished();
		return null;
	}

}
