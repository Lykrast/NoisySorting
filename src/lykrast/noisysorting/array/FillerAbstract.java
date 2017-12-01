package lykrast.noisysorting.array;

public abstract class FillerAbstract {
	/**
	 * Fills the given arrays with values ranging from 1 to the array's size.
	 * @param array the array to fill
	 */
	public abstract void fill(int[] array);
	
	@Override
	public String toString()
	{
		return "Unnamed";
	}
}
