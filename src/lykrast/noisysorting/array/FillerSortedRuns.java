package lykrast.noisysorting.array;

import java.util.Arrays;
import java.util.Random;

public class FillerSortedRuns extends FillerAbstract {

	@Override
	public void fill(int[] array) {
		Random rand = new Random();
		
		//Make random numbers
		int size = array.length;
		for (int i = 0; i < size; i++) {
			array[i] = rand.nextInt(size) + 1;
		}
		
		//Determine number of runs random between 2 and log2(size)
		int nb = 1;
		while (1 << (nb+1) < size) nb++;
		nb = rand.nextInt(nb) + 2;
		
		//Give each run a weight to make it proportionally bigger than the others
		int[] weights = new int[nb];
		double ratio = 0;
		for (int i = 0; i < nb; i++) {
			weights[i] = rand.nextInt(7) + 1;
			ratio += weights[i];
		}
		ratio = size / ratio;
		for (int i = 1; i < nb; i++) weights[i] += weights[i-1];
		
		//Sort the runs
		for (int i = 0, start = 0; i < nb; i++) {
			int end = i == nb-1 ? size : (int)(weights[i] * ratio);
			Arrays.sort(array, start, end);
			start = end;
		}
	}

	@Override
	public String toString() {
		return "Random Sorted Runs";
	}

}
