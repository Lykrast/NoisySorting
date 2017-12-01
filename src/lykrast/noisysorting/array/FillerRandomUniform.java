package lykrast.noisysorting.array;

import java.util.Random;

public class FillerRandomUniform extends FillerAbstract {

	@Override
	public void fill(int[] array) {
		Random rand = new Random();
		int size = array.length;
		for (int i=0;i<size;i++)
		{
			array[i] = rand.nextInt(size)+1;
		}
	}
	
	@Override
	public String toString()
	{
		return "Random Uniform";
	}

}
