package lykrast.noisysorting.sorting.sort;

import lykrast.noisysorting.sorting.VisualArray;

public class SorterCocktail extends SorterAbstract {
	public SorterCocktail(VisualArray array)
	{
		super(array);
	}

	@Override
	protected Object doInBackground() throws Exception {
		int size = a.getSize();
		boolean sorted;
		boolean reverse = false;
		
		do {
			sorted = true;
			
			if (reverse)
			{
				for (int i=size-1;i>0;i--)
				{
					//Mid-sort cancel
					if (isCancelled())
					{
						a.sortFinished();
						return null;
					}
					
					if (a.get(i) < a.get(i-1))
					{
						sorted = false;
						a.swap(i, i-1);
					}
					sleep();
				}
			}
			else
			{
				for (int i=0;i<size-1;i++)
				{
					//Mid-sort cancel
					if (isCancelled())
					{
						a.sortFinished();
						return null;
					}
					
					if (a.get(i) > a.get(i+1))
					{
						sorted = false;
						a.swap(i, i+1);
					}
					sleep();
				}
			}
			
			reverse = !reverse;
		}while (!sorted);

		a.sortFinished();
		return null;
	}

}
