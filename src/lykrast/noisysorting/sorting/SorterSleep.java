package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterSleep extends SorterAbstract {
	private final int[] shadow;
	private int shadowPointer;
	
	public SorterSleep(VisualArray array)
	{
		super(array);
		shadow = new int[a.getSize()];
		shadowPointer = 0;
	}

	@Override
	protected Object doInBackground() throws Exception {
		Sleeper[] sleepers = new Sleeper[a.getSize()];
		
		for (int i=0;i<a.getSize();i++)
		{
			//Mid-sort cancel
			if (isCancelled())
			{
				cleanup(sleepers);
				return null;
			}
			
			sleepers[i] = new Sleeper(i);
			sleep();
		}
		
		for (Sleeper s : sleepers) s.start();
		
		for (Sleeper s : sleepers)
		{
			//Mid-sort cancel
			if (isCancelled())
			{
				cleanup(sleepers);
				return null;
			}
			
			s.join();
		}
		
		for (int i=0;i<a.getSize();i++)
		{
			//Mid-sort cancel
			if (isCancelled())
			{
				a.sendRefresh();
				return null;
			}
			
			a.set(i, shadow[i]);
			sleep();
		}

		a.sortFinished();
		return null;
	}
	
	private void cleanup(Sleeper[] sleepers)
	{
		for (Sleeper s : sleepers) if (s != null) s.interrupt();
		a.sortFinished();
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
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			append(index);
		}
	}

}
