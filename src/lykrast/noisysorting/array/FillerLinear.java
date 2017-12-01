package lykrast.noisysorting.array;

public class FillerLinear extends FillerAbstract {

	@Override
	public void fill(int[] array) {
		for (int i=0;i<array.length;i++)
		{
			array[i] = i+1;
		}
	}
	
	@Override
	public String toString()
	{
		return "Linear";
	}

}
