package lykrast.noisysorting.sorting;

import java.util.Random;

import lykrast.noisysorting.array.VisualArray;

public class SorterBogo extends SorterAbstract {
	public SorterBogo(VisualArray array)
	{
		super(array);
	}

	@Override
	protected void sort() throws InterruptedException {
		Random rand = new Random();
		while (!a.isSorted())
		{
			for (int i=a.getSize()-1;i>0;i--)
			{
				a.swap(i,rand.nextInt(i+1));
			}
			sleep();
		}
	}

}
