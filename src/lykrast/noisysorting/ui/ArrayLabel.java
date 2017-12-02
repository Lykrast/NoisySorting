package lykrast.noisysorting.ui;

import java.awt.Graphics;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import lykrast.noisysorting.array.VAEventAbstract;
import lykrast.noisysorting.array.VAEventClear;
import lykrast.noisysorting.array.VAEventMultiple;
import lykrast.noisysorting.array.VAEventRefresh;
import lykrast.noisysorting.array.VAEventSingle;
import lykrast.noisysorting.array.VAItemStatus;
import lykrast.noisysorting.array.VisualArray;

public abstract class ArrayLabel extends JLabel implements Observer {
	private static final long serialVersionUID = 1L;
	protected VisualArray array;
	protected VAItemStatus[] status;
	protected boolean[] changeNext;
	
	public ArrayLabel(VisualArray array)
	{
		this.array = array;
		status = new VAItemStatus[array.getSize()];
		changeNext = new boolean[array.getSize()];
		Arrays.fill(status, VAItemStatus.DEFAULT);
		Arrays.fill(changeNext, false);
		array.addObserver(this);
	}
	
	public VisualArray getArray()
	{
		return array;
	}
	
	public void setArray(VisualArray array)
	{
		this.array.deleteObserver(this);
		this.array = array;
		status = new VAItemStatus[array.getSize()];
		changeNext = new boolean[array.getSize()];
		Arrays.fill(status, VAItemStatus.DEFAULT);
		Arrays.fill(changeNext, false);
		this.array.addObserver(this);
	}

	@Override
	public void update(Observable arg0, Object arg1) {		
		if (arg1 instanceof VAEventAbstract) processVAEvent((VAEventAbstract)arg1);
		
		revalidate();
		repaint();
	}
	
	private void processVAEvent(VAEventAbstract ev)
	{
		if (ev instanceof VAEventSingle)
		{
			VAEventSingle event = (VAEventSingle)ev;
			status[event.getIndex()] = event.getStatus();
			if (event.isTemporary()) changeNext[event.getIndex()] = true;
		}
		else if (ev instanceof VAEventMultiple)
		{
			VAEventAbstract[] events = ((VAEventMultiple)ev).getEvents();
			for (VAEventAbstract event : events) processVAEvent(event);
		}
		else if (ev instanceof VAEventRefresh)
		{
			for (int i=0; i<array.getSize();i++)
			{
				if (changeNext[i])
				{
					changeNext[i] = false;
					if (array.isMarked(i)) status[i] = VAItemStatus.MARKED;
					else status[i] = VAItemStatus.DEFAULT;
				}
			}
		}
		else if (ev instanceof VAEventClear)
		{
			Arrays.fill(status, VAItemStatus.DEFAULT);
			Arrays.fill(changeNext, false);
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics modified = g.create();
		paintArray(modified);
		modified.dispose();
	}
	
	protected abstract void paintArray(Graphics g);

}
