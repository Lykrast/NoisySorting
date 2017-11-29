package lykrast.noisysorting.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import lykrast.noisysorting.sorting.VAEventAbstract;
import lykrast.noisysorting.sorting.VAEventClear;
import lykrast.noisysorting.sorting.VAEventMultiple;
import lykrast.noisysorting.sorting.VAEventRefresh;
import lykrast.noisysorting.sorting.VAEventSingle;
import lykrast.noisysorting.sorting.VisualArray;

public class ArrayLabel extends JLabel implements Observer {
	private static final long serialVersionUID = 1L;
	private VisualArray array;
	private Color[] colors;
	private boolean[] changeNext;
	
	public ArrayLabel(VisualArray array)
	{
		this.array = array;
		colors = new Color[array.getSize()];
		changeNext = new boolean[array.getSize()];
		Arrays.fill(colors, Color.WHITE);
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
		colors = new Color[array.getSize()];
		changeNext = new boolean[array.getSize()];
		Arrays.fill(colors, Color.WHITE);
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
			colors[event.getIndex()] = event.getColor();
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
					if (array.isMarked(i)) colors[i] = Color.CYAN;
					else colors[i] = Color.WHITE;
				}
			}
		}
		else if (ev instanceof VAEventClear)
		{
			Arrays.fill(colors, Color.WHITE);
			Arrays.fill(changeNext, false);
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Rectangle bb = g.getClipBounds();
		Graphics modified = g.create();
		modified.setColor(Color.BLACK);
		modified.fillRect(bb.x, bb.y, bb.width, bb.height);
		
		int size = array.getSize();
		int startX = bb.x + 10;
		int endX = bb.x + bb.width - 10;
		int startY = bb.y + 10;
		int endY = bb.y + bb.height - 10;
		double itemWidth = (endX-startX)/(double)size;
		double itemHeight = (endY-startY)/(double)size;
		itemWidth = Math.max(1, itemWidth);
		int itemPadding = itemWidth >= 3.0 ? 1 : 0;

		for (int i=0;i<array.getSize();i++)
		{
			modified.setColor(colors[i]);
			int ai = array.getSilent(i);
			modified.fillRect((int)Math.ceil(startX + i*itemWidth) + itemPadding, (int)Math.ceil(startY + (size-ai)*itemHeight), 
					(int)Math.floor(itemWidth) - itemPadding, (int)Math.floor(ai*itemHeight));
		}
	}

}
