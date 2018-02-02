package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterSleep extends SorterAbstract {
	private final int[] shadow;
	private int shadowPointer;
	private Sleeper[] sleepers;
	private boolean sorting;
	
	public SorterSleep(VisualArray array)
	{
		super(array);
		shadow = new int[a.getSize()];
		shadowPointer = 0;
		sorting = false;
	}

	@Override
	protected void sort() throws InterruptedException {
		sleepers = new Sleeper[a.getSize()];
		
		for (int i=0;i<a.getSize();i++)
		{
			sleepers[i] = new Sleeper(i);
			sleep();
		}
		
		sorting = true; 
		for (Sleeper s : sleepers) s.start();
		
		for (Sleeper s : sleepers)
		{			
			s.join();
		}
		sorting = false;
		
		for (int i=0;i<a.getSize();i++)
		{			
			a.set(i, shadow[i]);
			sleep();
		}
	}
	
	@Override
	protected void cleanup()
	{
		if (sorting) for (Sleeper s : sleepers) s.interrupt();
	}
	
	private synchronized void append(int index)
	{
		a.sendRefresh();
		shadow[shadowPointer] = a.get(index);
		shadowPointer++;
	}
	
	private class Sleeper extends Thread {
		private int index, value;
		
		private Sleeper(int index)
		{
			this.index = index;
			value = a.get(index);
		}
		
		@Override
		public void run() {
			try {
				sleep(value * timeout);
				append(index);
			} catch (InterruptedException e) {}
		}
	}

}
