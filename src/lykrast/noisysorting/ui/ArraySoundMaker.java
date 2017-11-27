package lykrast.noisysorting.ui;

import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

import lykrast.noisysorting.sorting.VAEventAbstract;
import lykrast.noisysorting.sorting.VAEventClear;
import lykrast.noisysorting.sorting.VAEventMultiple;
import lykrast.noisysorting.sorting.VAEventRefresh;
import lykrast.noisysorting.sorting.VAEventSingle;
import lykrast.noisysorting.sorting.VisualArray;

public class ArraySoundMaker implements Observer {
	private Synthesizer synth;
	private VisualArray array;
	private MidiChannel[] mc;
	private double scale;
	private boolean[] releaseNext;
	
	public ArraySoundMaker(VisualArray array)
	{
		try {
			synth = MidiSystem.getSynthesizer();
			synth.open();
			mc = synth.getChannels();
			Instrument[] instr = synth.getDefaultSoundbank().getInstruments();
			synth.loadInstrument(instr[0]);
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
		this.array = array;
		this.array.addObserver(this);
		scale = 128.0/array.getSize();
		releaseNext = new boolean[128];
		Arrays.fill(releaseNext, false);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof VAEventAbstract) processVAEvent((VAEventAbstract)arg1);
	}
	
	private void processVAEvent(VAEventAbstract ev)
	{
		if (ev instanceof VAEventSingle)
		{
			VAEventSingle event = (VAEventSingle)ev;
			int note = (int) Math.round(array.getSilent(event.getIndex()) * scale) - 1;
			note = 127 - note;
			mc[0].noteOn(note, 600);
			releaseNext[note] = true;
		}
		else if (ev instanceof VAEventMultiple)
		{
			VAEventAbstract[] events = ((VAEventMultiple)ev).getEvents();
			for (VAEventAbstract event : events) processVAEvent(event);
		}
		else if (ev instanceof VAEventRefresh)
		{
			for (int i=0;i<128;i++)
			{
				if (releaseNext[i])
				{
					releaseNext[i] = false;
					mc[0].noteOff(i);
				}
			}
		}
		else if (ev instanceof VAEventClear)
		{
			mc[0].allNotesOff();
		}
	}

}
