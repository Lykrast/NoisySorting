package lykrast.noisysorting.sorting.sort;

import java.util.Random;

import lykrast.noisysorting.sorting.VisualArray;

public class SorterBozo extends SorterAbstract {
	public SorterBozo(VisualArray array)
	{
		super(array);
	}

	@Override
	protected Object doInBackground() throws Exception {
		Random rand = new Random();
		while (!isSorted())
		{
			//Mid-sort cancel
			if (isCancelled())
			{
				a.sortFinished();
				return null;
			}
			
			a.swap(rand.nextInt(a.getSize()), rand.nextInt(a.getSize()));
			sleep();
		}
		
		a.sortFinished();
		return null;
	}
	
	private boolean isSorted()
	{
		for (int i=0;i<a.getSize()-1;i++)
		{
			if (a.getSilent(i)>a.getSilent(i+1)) return false;
		}
		return true;
	}

}
