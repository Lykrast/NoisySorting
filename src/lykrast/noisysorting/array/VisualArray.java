package lykrast.noisysorting.array;

import java.awt.Color;
import java.util.Arrays;
import java.util.Observable;
import java.util.Random;

public class VisualArray extends Observable {
	private int size;
	private int[] array;
	private int[] marked;
	
	public VisualArray(int size)
	{
		this.size = size;
		array = new int[size];
		marked = new int[size];
		fill();
		clearMark();
	}
	
	public int get(int index)
	{
		setUpdated(new VAEventSingle(index, Color.RED));
		return array[index];
	}
	
	public void set(int index, int value)
	{
		array[index] = value;
		setUpdated(new VAEventSingle(index, Color.RED));
	}
	
	public int getSilent(int index)
	{
		return array[index];
	}
	
	public int getSize()
	{
		return size;
	}
	
	public void swap(int i, int j)
	{
		swapSilent(i, j);
		setUpdated(new VAEventMultiple(new VAEventSingle(i, Color.RED), new VAEventSingle(j, Color.RED)));
	}
	
	public void swapSilent(int i, int j)
	{
		int buffer = array[i];
		//int bufferMark = marked[i];
		
		array[i] = array[j];
		//marked[i] = marked[j];
		array[j] = buffer;
		//marked[j] = bufferMark;
	}
	
	public void mark(int index)
	{
		marked[index]++;
		setUpdated(new VAEventSingle(index, Color.CYAN, false));
	}
	
	public void unmark(int index)
	{
		marked[index]--;
		if (isMarked(index)) setUpdated(new VAEventSingle(index, Color.CYAN, false));
		else setUpdated(new VAEventSingle(index, Color.WHITE, false));
	}
	
	private void clearMark()
	{
		Arrays.fill(marked, 0);
	}
	
	public boolean isMarked(int index)
	{
		return marked[index] > 0;
	}
	
	public void fill()
	{
		for (int i=0;i<size;i++)
		{
			array[i] = i+1;
		}
		sortFinished();
	}
	
	public void fillCubic()
	{
		for (int i=0;i<size;i++)
		{
			//Scale to [-1;1]
			double x = ((i+1.0)/size) * 2 - 1;
			x = x*x*x;
			//Scale back to array
			int val = (int) (((x+1)/2) * size);
			array[i] = val;
		}
		sortFinished();
	}
	
	public void fillMinMax()
	{
		array[0] = 1;
		array[size-1] = size;
		int middle = (size + 1)/2;
		for (int i=1;i<size-1;i++)
		{
			array[i] = middle;
		}
		sortFinished();
	}
	
	public void reverse()
	{
		int middle = size / 2;
		for (int i=0;i<middle;i++)
		{
			swapSilent(i,size-i-1);
		}
		sortFinished();
	}
	
	public void shuffle()
	{
		Random rand = new Random();
		for (int i=size-1;i>0;i--)
		{
			swapSilent(i,rand.nextInt(i+1));
		}
		setUpdated();
	}
	
	public void shuffleNear()
	{
		Random rand = new Random();
		for (int i=0;i<size-4;i+=4)
		{
			for (int j=i+4;j>i;j--) swapSilent(j,rand.nextInt(j-i)+i);
		}
		setUpdated();
	}
	
	public void sortFinished()
	{
		clearMark();
		setUpdated(new VAEventClear());
	}
	
	public void sendRefresh()
	{
		setUpdated(new VAEventRefresh());
	}
	
	private void setUpdated()
	{
		setChanged();
		notifyObservers();
	}
	
	private void setUpdated(VAEventAbstract event)
	{
		setChanged();
		notifyObservers(event);
	}

}
