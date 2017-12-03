package lykrast.noisysorting.array;

import java.util.Random;

public class FillerAppended extends FillerAbstract {

	//Inspired by https://github.com/BonzaiThePenguin/WikiSort/blob/master/WikiSort.cpp
	@Override
	public void fill(int[] array) {
		Random rand = new Random();
		int size = array.length;
		//First 4/5 is ordered, last 1/5 is random
		int append = (4*size)/5 - 1;
		for (int i=0;i<append;i++) array[i] = i+1;
		for (int i=append;i<size;i++) array[i] = rand.nextInt(size)+1;
	}
	
	@Override
	public String toString()
	{
		return "Random Appended";
	}

}
