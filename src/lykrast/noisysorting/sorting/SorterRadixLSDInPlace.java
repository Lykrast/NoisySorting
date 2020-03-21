package lykrast.noisysorting.sorting;

import java.util.Arrays;

import lykrast.noisysorting.array.VisualArray;

public class SorterRadixLSDInPlace extends SorterAbstract {
	private int radix;
	
	public SorterRadixLSDInPlace(VisualArray array, int radix) {
		super(array);
		this.radix = radix;
	}

	//From https://github.com/w0rthy/ArrayVisualizer/blob/master/src/array/visualizer/sort/RadixLSDInPlace.java
	@Override
	protected void sort() throws InterruptedException {
		//Finding the maximum
		int maxIndex = 0;
		for (int i=0;i<a.getSize();i++)
		{
			if (a.get(i) > a.get(maxIndex)) maxIndex = i;
			sleep();
		}
		
		int maxPower = (int)(Math.log(a.getSilent(maxIndex))/Math.log(radix));
		for(int p = 0; p <= maxPower; p++)
			partialSort(p);
	}
	
	private void partialSort(int exp) throws InterruptedException {
		int[] vregs = new int[radix-1];
		Arrays.fill(vregs, a.getSize()-1);
		for (int i = 0; i < vregs.length; i++) {
			a.mark(vregs[i]);
		}
		
		int pos = 0;
		a.mark(pos);
		for (int i = 0; i < a.getSize(); i++) {
			int digit = (int) (a.get(pos) / Math.pow(radix, exp)) % radix;
			if (digit == 0) {
				a.unmark(pos);
				pos++;
				a.mark(pos);
			}
			else {
				swapUpTo(pos, vregs[digit-1]);
				for (int j = digit-1; j > 0; j--) {
					a.unmark(vregs[j-1]);
					vregs[j-1]--;
					a.mark(vregs[j-1]);
				}
			}
			sleep();
		}
		
		for (int i = 0; i < vregs.length; i++) {
			a.unmark(vregs[i]);
		}
		a.unmark(pos);
	}
	
	private void swapUpTo(int pos, int to) {
		if (to - pos > 0)
			for (int i = pos; i < to; i++)
				a.swapSilent(i, i + 1);
		else
			for (int i = pos; i > to; i--)
				a.swapSilent(i, i - 1);
	}

}
