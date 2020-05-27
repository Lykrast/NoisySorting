package lykrast.noisysorting.ui.arraylabel;

import java.awt.Graphics;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import lykrast.noisysorting.array.VAEvent;
import lykrast.noisysorting.array.VAEventSingle;
import lykrast.noisysorting.array.VAItemStatus;
import lykrast.noisysorting.array.VisualArray;

public abstract class ArrayLabel extends JLabel implements Observer {
	private static final long serialVersionUID = 1L;
	protected VisualArray array;
	protected VAItemStatus[] status;
	protected boolean[] changeNext;

	public ArrayLabel(VisualArray array) {
		this.array = array;
		status = new VAItemStatus[array.getSize()];
		changeNext = new boolean[array.getSize()];
		Arrays.fill(status, VAItemStatus.DEFAULT);
		Arrays.fill(changeNext, false);
		array.addObserver(this);
	}

	public VisualArray getArray() {
		return array;
	}

	public void setArray(VisualArray array) {
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
		if (arg1 instanceof VAEvent) processVAEvent((VAEvent) arg1);
	}

	private void processVAEvent(VAEvent ev) {
		switch (ev.getType()) {
			case SINGLE:
				VAEventSingle event = ev.toSingle();
				status[event.getIndex()] = event.getStatus();
				if (event.isTemporary()) changeNext[event.getIndex()] = true;
				break;
			case REFRESH:
				for (int i = 0; i < array.getSize(); i++) {
					if (changeNext[i]) {
						changeNext[i] = false;
						if (array.isMarked(i)) status[i] = VAItemStatus.MARKED;
						else status[i] = VAItemStatus.DEFAULT;
					}
				}
				break;
			case CLEAR:
				Arrays.fill(status, VAItemStatus.DEFAULT);
				Arrays.fill(changeNext, false);
			case FRAME:
				revalidate();
				repaint();
				break;
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
