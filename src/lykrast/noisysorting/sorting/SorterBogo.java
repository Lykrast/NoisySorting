package lykrast.noisysorting.sorting;

import java.util.Random;

import lykrast.noisysorting.array.VisualArray;

public class SorterBogo extends SorterAbstract {
	public SorterBogo(VisualArray array)
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

			for (int i=a.getSize()-1;i>0;i--)
			{
				a.swap(i,rand.nextInt(i+1));
			}
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
