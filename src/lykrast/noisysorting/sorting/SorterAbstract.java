package lykrast.noisysorting.sorting;

import javax.swing.SwingWorker;

import lykrast.noisysorting.array.VisualArray;

public abstract class SorterAbstract extends SwingWorker<Object, Object> {
	public static long timeout = 100;
	protected VisualArray a;
	
	public SorterAbstract(VisualArray array)
	{
		super();
		a = array;
	}
	
	@Override
	protected Object doInBackground() throws Exception {
		try {
			sort();
		}
		catch (InterruptedException e) {
			cleanup();
		}
		
		a.sortFinished();
		return null;
	}
	
	protected abstract void sort() throws InterruptedException;
	protected void cleanup() {}

	protected void sleep() throws InterruptedException {
		Thread.sleep(timeout);
		a.sendRefresh();
	}
	
	public static synchronized void setTimeout(long time)
	{
		timeout = Math.max(1, time);
	}

}