package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterBoseNelson extends SorterAbstract {
	public SorterBoseNelson(VisualArray array)
	{
		super(array);
	}

	//http://collaboration.cmc.ec.gc.ca/science/rpn/biblio/ddj/Website/articles/CUJ/1993/9302/hegeman/list1.htm
	@Override
	protected void sort() throws InterruptedException {
		split(0, a.getSize());
	}
	
	private void swapChecked(int i, int j) throws InterruptedException
	{
		if (a.get(i) > a.get(j)) a.swap(i, j);
		sleep();
	}
	
	private void split(int start, int len) throws InterruptedException
	{
		//Mid-sort cancel
		if (len <= 1) return;
		
		int mid = len/2;
		split(start, mid);
		split(start+mid, len-mid);
		merge(start, mid, start+mid, len-mid);
	}
	
	private void merge(int start1, int len1, int start2, int len2) throws InterruptedException
	{
		if (len1 == 1 && len2 == 1) swapChecked(start1, start2);
		else if (len1 == 1 && len2 == 2)
		{
			swapChecked(start1, start2+1);
			swapChecked(start1, start2);
		}
		else if (len1 == 2 && len2 == 1)
		{
			swapChecked(start1, start2);
			swapChecked(start1+1, start2);
		}
		else
		{
			int mid1 = len1/2;
			int mid2 = len1 % 2 == 1 ? len2/2 : (len2+1)/2;
			merge(start1, mid1, start2, mid2);
			merge(start1+mid1, len1-mid1, start2+mid2, len2-mid2);
			merge(start1+mid1, len1-mid1, start2, mid2);
		}
	}

}
