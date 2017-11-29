package lykrast.noisysorting.sorting.sort;

import java.util.Arrays;

import lykrast.noisysorting.sorting.VisualArray;

public class SorterBead extends SorterAbstract {
	public SorterBead(VisualArray array)
	{
		super(array);
	}

	@Override
	protected Object doInBackground() throws Exception {
		int size = a.getSize();
		//A bit of cheating since the max of an array is always its size
		boolean[][] beads = new boolean[size][size];
		for (boolean[] b : beads)
		{
			Arrays.fill(b, false);
		}
		
		//Setting up the beads
		for (int i=0;i<size;i++)
		{
			int val = a.get(i);
			for (int j=0;j<val;j++)
			{
				beads[i][j] = true;
			}
			sleep();
		}
		
		//Collapsing the beads
		boolean sorted;
		do
		{
			sorted = true;
			for (int i=size-1;i>0;i--)
			{
				for (int j=0;j<size;j++)
				{
					if (!beads[i][j] && beads[i-1][j])
					{
						beads[i][j] = true;
						beads[i-1][j] = false;
						sorted = false;
					}
				}
			}
			displayBeads(beads);
			sleep();
			
		} while (!sorted);
		
		a.sortFinished();
		return null;
	}
	
	private void displayBeads(boolean[][] beads)
	{
		for (int i=0;i<a.getSize();i++)
		{
			int total = 0;
			int j = 0;
			while (j < a.getSize() && beads[i][j])
			{
				total++;
				j++;
			}
			if (a.getSilent(i) != total) a.set(i, total);
		}
	}

}
