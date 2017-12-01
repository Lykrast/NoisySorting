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

	protected void sleep() {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {}
		a.sendRefresh();
	}
	
	public static synchronized void setTimeout(long time)
	{
		timeout = Math.max(1, time);
	}

}