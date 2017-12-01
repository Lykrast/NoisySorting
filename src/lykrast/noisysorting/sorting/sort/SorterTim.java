package lykrast.noisysorting.sorting.sort;

import java.util.Stack;

import lykrast.noisysorting.sorting.VisualArray;

public class SorterTim extends SorterAbstract {
	public SorterTim(VisualArray array)
	{
		super(array);
	}

	// https://www.infopulse.com/blog/timsort-sorting-algorithm/
	// https://hg.python.org/cpython/file/tip/Objects/listsort.txt
	@Override
	protected Object doInBackground() throws Exception {
		int size = a.getSize();
		//Calculation of the Minrun.
		int minrun = getMinrun(size);
		
		//Splitting into Runs and Their Sorting.
		//And Merging
		Stack<Run> stack = new Stack<>();
		int pos = 0;
		while (pos != -1 && pos < size-1)
		{
			//Mid-sort cancel
			if (isCancelled())
			{
				a.sortFinished();
				return null;
			}
			
			int end = findRun(pos, minrun);
			stack.push(new Run(pos, end));
			pos = end+1;
			
			mergeStack(stack);
		}
		mergeForce(stack);
		
		a.sortFinished();
		return null;
	}
	
	private int getMinrun(int n)
	{
		int r = 0;
		while (n >= 64)
		{
			r |= n & 1;
			n >>= 1;
		}
		return n + r;
	}
	
	//Finds a run and return right after where it ends
	//-1 when we get out of the array
	private int findRun(int pos, int minrun)
	{
		//2 size runs are already sorted, so we end here
		if (pos >= a.getSize()-2) return a.getSize()-1;
		//Find the run
		a.mark(pos);
		int end = pos;
		if (a.get(pos+1) >= a.get(pos)) end = findRunAscending(pos);
		else
		{
			end = findRunDescending(pos);
			flip(pos, end);
		}
		a.unmark(pos);
		
		//Runs less than minrun are boosted
		if (end - pos + 1 < minrun)
		{
			end = Math.min(pos + minrun - 1, a.getSize()-1);
			insertionSort(pos, end);
		}
		return end;
	}
	
	private int findRunAscending(int pos)
	{
		while (pos <= a.getSize()-2 && a.get(pos+1) >= a.get(pos))
		{
			//Mid-sort cancel
			if (isCancelled()) return -1;
			
			pos++;
			sleep();
		}
		return pos;
	}
	
	private int findRunDescending(int pos)
	{
		while (pos <= a.getSize()-2 && a.get(pos+1) < a.get(pos))
		{
			//Mid-sort cancel
			if (isCancelled()) return -1;
			
			pos++;
			sleep();
		}
		return pos;
	}
	
	private void flip(int min, int max)
	{
		a.mark(min);
		a.mark(max);
		int middle = (max + min) / 2;
		for (int i=min;i<=middle;i++)
		{
			//Mid-sort cancel
			if (isCancelled()) return;
			
			a.swap(i,max+min-i);
			sleep();
		}
		a.unmark(min);
		a.unmark(max);
	}
	
	private void insertionSort(int min, int max)
	{
		a.mark(min);
		a.mark(max);
		for (int i=min+1;i<=max;i++)
		{
			//Mid-sort cancel
			if (isCancelled()) return;
			
			a.mark(i);
			int j = i;
			
			while (j > min && a.get(j-1) > a.get(j))
			{
				//Mid-sort cancel
				if (isCancelled()) return;
				
				a.swap(j, j-1);
				j--;
				sleep();
			}
			//Only sleep here if no change was made before
			if (j == i) sleep();
			a.unmark(i);
		}
		a.unmark(min);
		a.unmark(max);
	}
	
	//Merge during the run making
	private void mergeStack(Stack<Run> stack)
	{
		while (stack.size() >= 3)
		{
			Run x = stack.pop();
			Run y = stack.pop();
			Run z = stack.pop();

			// Invariants
			if (z.size() > y.size() + x.size() && y.size() > x.size())
			{
				stack.push(z);
				stack.push(y);
				stack.push(x);
				break;
			}
			else
			{
				if (x.size() < z.size())
				{
					//Check invariants again
					Run xy = merge(y, x);
					if (xy.size() > z.size())
					{
						stack.push(merge(z,xy));
					}
					else
					{
						stack.push(z);
						stack.push(xy);
					}
				}
				else
				{
					//Check invariants again
					Run yz = merge(z, y);
					if (x.size() > yz.size())
					{
						stack.push(merge(yz,x));
					}
					else
					{
						stack.push(yz);
						stack.push(x);
					}
				}
			}
		}
	}
	
	//Merges all remaining runs to end the algorithm
	private void mergeForce(Stack<Run> stack)
	{
		while (stack.size() > 1)
		{
			if (stack.size() > 2)
			{
				Run x = stack.pop();
				Run y = stack.pop();
				Run z = stack.pop();
				
				if (x.size() < z.size())
				{
					stack.push(z);
					stack.push(merge(y,x));
				}
				else
				{
					stack.push(merge(z,y));
					stack.push(x);
				}
			}
			else
			{
				Run x = stack.pop();
				Run y = stack.pop();
				stack.push(merge(y,x));
			}
		}
	}
	
	private Run merge(Run x, Run y)
	{
		//x should come right before y in the array
		if (x.size() <= y.size()) return mergeLow(x,y);
		else return mergeHigh(x,y);
	}
	
	//TODO Galloping
	private Run mergeLow(Run x, Run y)
	{
		//x is the smaller one
		a.mark(x.start);
		a.mark(x.end);
		a.mark(y.start);
		a.mark(y.end);
		Run merged = new Run(x.start, y.end);
		
		//Copy x into a temp array
		int[] temp = new int[x.size()];
		for (int i=x.start;i<=x.end;i++)
		{
			//Mid-sort cancel
			if (isCancelled()) return null;
			
			temp[i-x.start] = a.get(i);
			sleep();
		}
		a.unmark(x.end);
		a.unmark(y.start);
		
		int pointerX = 0, pointerY = y.start, store = x.start;
		while (store <= y.end)
		{
			//Mid-sort cancel
			if (isCancelled()) return null;
			
			if (pointerX < x.size() && (pointerY > y.end || temp[pointerX] <= a.get(pointerY)))
			{
				a.set(store, temp[pointerX]);
				pointerX++;
			}
			else
			{
				a.set(store, a.get(pointerY));
				pointerY++;
			}
			store++;
			sleep();
		}
		
		a.unmark(x.start);
		a.unmark(y.end);
		return merged;
	}
	
	private Run mergeHigh(Run x, Run y)
	{
		//y is the smaller one
		a.mark(x.start);
		a.mark(x.end);
		a.mark(y.start);
		a.mark(y.end);
		Run merged = new Run(x.start, y.end);
		
		//Copy y into a temp array
		int[] temp = new int[y.size()];
		for (int i=y.start;i<=y.end;i++)
		{
			//Mid-sort cancel
			if (isCancelled()) return null;
			
			temp[i-y.start] = a.get(i);
			sleep();
		}
		a.unmark(x.end);
		a.unmark(y.start);
		
		int pointerX = x.end, pointerY = y.size()-1, store = y.end;
		while (store >= x.start)
		{
			//Mid-sort cancel
			if (isCancelled()) return null;
			
			if (pointerX >= x.start && (pointerY < 0 || a.get(pointerX) > temp[pointerY]))
			{
				a.set(store, a.get(pointerX));
				pointerX--;
			}
			else
			{
				a.set(store, temp[pointerY]);
				pointerY--;
			}
			store--;
			sleep();
		}
		
		a.unmark(x.start);
		a.unmark(y.end);
		return merged;
	}
	
	private static class Run {
		private int start;
		private int end;
		
		private Run(int start, int end)
		{
			this.start = start;
			this.end = end;
		}
		
		private int size()
		{
			return end - start + 1;
		}
	}

}
