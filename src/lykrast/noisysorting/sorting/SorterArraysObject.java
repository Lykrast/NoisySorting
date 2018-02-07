package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterArraysObject extends SorterAbstract {
	public SorterArraysObject(VisualArray array)
	{
		super(array);
	}

	//http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/6-b14/java/util/Arrays.java#Arrays.sort%28java.lang.Object%5B%5D%29
	//Rewritten a bit to fit sleeps in
	@Override
	protected void sort() throws InterruptedException {
		mergeSort(0, a.getSize()-1);
	}
	
	private void mergeSort(int min, int max) throws InterruptedException
	{
		
	}

}
