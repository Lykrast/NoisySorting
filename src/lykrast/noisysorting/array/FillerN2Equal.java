package lykrast.noisysorting.array;

public class FillerN2Equal extends FillerAbstract {

	@Override
	public void fill(int[] array) {
		int size = array.length;
		array[0] = 1;
		array[size-1] = size;
		int middle = (size + 1)/2;
		for (int i=1;i<size-1;i++)
		{
			array[i] = middle;
		}
	}
	
	@Override
	public String toString()
	{
		return "N-2 Equal";
	}

}
