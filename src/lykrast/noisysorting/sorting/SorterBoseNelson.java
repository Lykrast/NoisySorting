package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterBoseNelson extends SorterAbstract {
	public SorterBoseNelson(VisualArray array)
	{
		super(array);
	}

	//http://collaboration.cmc.ec.gc.ca/science/rpn/biblio/ddj/Website/articles/CUJ/1993/9302/hegeman/list1.htm
	@Override
	protected Object doInBackground() throws Exception {
		star(0, a.getSize());
		a.sortFinished();
		return null;
	}
	
	private void swapChecked(int i, int j)
	{
		//Mid-sort cancel
		if (isCancelled()) return;
		
		if (a.get(i) > a.get(j)) a.swap(i, j);
		sleep();
	}
	
	private void star(int start, int len)
	{
		//Mid-sort cancel
		if (len <= 1 || isCancelled()) return;
		
		int mid = len/2;
		star(start, mid);
		star(start+mid, len-mid);
		bracket(start, mid, start+mid, len-mid);
	}
	
	private void bracket(int start1, int len1, int start2, int len2)
	{
		//Mid-sort cancel
		if (isCancelled()) return;
		
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
			bracket(start1, mid1, start2, mid2);
			bracket(start1+mid1, len1-mid1, start2+mid2, len2-mid2);
			bracket(start1+mid1, len1-mid1, start2, mid2);
		}
	}

}
