package lykrast.noisysorting.array;

import java.util.Arrays;
import java.util.Observable;
import java.util.Random;

public class VisualArray extends Observable {
	private int size;
	private int[] array;
	private int[] marked;
	private FillerAbstract filler;
	
	public VisualArray(int size)
	{
		this(size, new FillerLinear());
	}
	
	public VisualArray(int size, FillerAbstract filler)
	{
		this.size = size;
		array = new int[size];
		marked = new int[size];
		this.filler = filler;
		fill();
		clearMark();
	}
	
	public int get(int index)
	{
		setUpdated(new VAEventSingle(index, VAItemStatus.SELECTED));
		return array[index];
	}
	
	public void set(int index, int value)
	{
		array[index] = value;
		setUpdated(new VAEventSingle(index, VAItemStatus.CHANGED));
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
		setUpdated(new VAEventMultiple(new VAEventSingle(i, VAItemStatus.CHANGED), new VAEventSingle(j, VAItemStatus.CHANGED)));
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
		setUpdated(new VAEventSingle(index, VAItemStatus.MARKED, false));
	}
	
	public void unmark(int index)
	{
		marked[index]--;
		if (isMarked(index)) setUpdated(new VAEventSingle(index, VAItemStatus.MARKED, false));
		else setUpdated(new VAEventSingle(index, VAItemStatus.DEFAULT, false));
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
		filler.fill(array);
		sortFinished();
	}
	
	public FillerAbstract getFiller()
	{
		return filler;
	}
	
	public void setFiller(FillerAbstract filler)
	{
		this.filler = filler;
		fill();
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
	
	public boolean isSorted()
	{
		for (int i=1;i<size;i++)
		{
			if (array[i-1]>array[i]) return false;
		}
		return true;
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
