package lykrast.noisysorting.sorting;

import java.util.Random;

import lykrast.noisysorting.array.VisualArray;

public class SorterBozo extends SorterAbstract {
	public SorterBozo(VisualArray array)
	{
		super(array);
	}

	@Override
	protected void sort() throws InterruptedException {
		Random rand = new Random();
		while (!isSorted())
		{
			a.swap(rand.nextInt(a.getSize()), rand.nextInt(a.getSize()));
			sleep();
		}
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
