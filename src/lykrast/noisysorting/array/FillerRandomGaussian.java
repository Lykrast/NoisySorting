package lykrast.noisysorting.array;

import java.util.Random;

public class FillerRandomGaussian extends FillerAbstract {

	@Override
	public void fill(int[] array) {
		Random rand = new Random();
		int size = array.length;
		//Compute values and find max and min
		double[] temp = new double[size];
		double min = Double.MAX_VALUE, max = Double.MIN_VALUE;
		for (int i=0;i<size;i++)
		{
			double d = rand.nextGaussian();
			if (d < min) min = d;
			if (d > max) max = d;
			temp[i] = d;
		}
		max -= min;
		for (int i=0;i<size;i++)
		{
			//Scale everything from 0 to 1
			double scaled = (temp[i] - min)/max;
			//Scale up to array size
			array[i] = (int) (scaled*(size-1)) + 1;
		}
	}
	
	@Override
	public String toString()
	{
		return "Random Gaussian";
	}

}
