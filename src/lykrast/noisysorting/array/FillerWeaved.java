package lykrast.noisysorting.array;

public class FillerWeaved extends FillerAbstract {

	@Override
	public void fill(int[] array) {
		for (int i=0;i<array.length;i+=2)
		{
			array[i] = (i+2)/2;
		}
		for (int i=1;i<array.length;i+=2)
		{
			array[i] = array.length-(i+1)/2+1;
		}
	}
	
	@Override
	public String toString()
	{
		return "Weaved";
	}

}
