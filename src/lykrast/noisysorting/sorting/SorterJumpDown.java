package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterJumpDown extends SorterAbstract {
	public SorterJumpDown(VisualArray array)
	{
		super(array);
	}

	// https://users.cs.duke.edu/~ola/bubble/bubble.html
	@Override
	protected void sort() throws InterruptedException {
		for (int i=a.getSize()-1;i>0;i--)
		{
			for (int j=0;j<i;j++)
			{
				if (a.get(j) > a.get(i)) a.swap(j, i);
				sleep();
			}
		}
	}

}
