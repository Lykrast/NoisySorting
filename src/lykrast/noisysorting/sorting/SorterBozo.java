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
		while (!a.isSorted())
		{
			a.swap(rand.nextInt(a.getSize()), rand.nextInt(a.getSize()));
			sleep();
		}
	}

}
