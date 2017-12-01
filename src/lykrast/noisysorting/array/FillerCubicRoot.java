package lykrast.noisysorting.array;

public class FillerCubicRoot extends FillerAbstract {

	@Override
	public void fill(int[] array) {
		int size = array.length;
		for (int i=0;i<size;i++)
		{
			//Scale to [-1;1]
			double x = ((i+1.0)/size) * 2 - 1;
			x = Math.cbrt(x);
			//Scale back to array
			int val = (int) (((x+1)/2) * size);
			array[i] = val;
		}
	}
	
	@Override
	public String toString()
	{
		return "Cubic Root";
	}

}
