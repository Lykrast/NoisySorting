package lykrast.noisysorting.ui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

import lykrast.noisysorting.array.VAEventAbstract;
import lykrast.noisysorting.array.VAEventClear;
import lykrast.noisysorting.array.VAEventMultiple;
import lykrast.noisysorting.array.VAEventRefresh;
import lykrast.noisysorting.array.VAEventSingle;
import lykrast.noisysorting.array.VisualArray;

public class ArraySoundMaker implements Observer, ItemListener {
	private Synthesizer synth;
	private VisualArray array;
	private MidiChannel[] mc;
	private double scale;
	private int volume;
	private boolean[] releaseNext;
	private Instrument current;
	
	private static final int PITCH_MARGIN = 30;
	
	public ArraySoundMaker(VisualArray array, int vol)
	{
		this.array = array;
		try {
			synth = MidiSystem.getSynthesizer();
			synth.open();
			mc = synth.getChannels();
			//First instrument is force loaded once the combo box is created
			this.array.addObserver(this);
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
		scale = (127.0 - PITCH_MARGIN*2)/array.getSize();
		releaseNext = new boolean[128];
		Arrays.fill(releaseNext, false);
		volume = vol;
	}
	
	public void loadInstrument(Instrument i)
	{
		if (current != null) synth.unloadInstrument(current);
		current = i;
		synth.loadInstrument(current);
		mc[0].programChange(current.getPatch().getProgram());
	}
	
	public InstrumentComboBox getInstrumentBox()
	{
		InstrumentComboBox box = new InstrumentComboBox(synth);
		box.addItemListener(this);
		box.forceChangeEvent();
		return box;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED)
		{
			System.out.println(e.getItem());
			loadInstrument((Instrument)e.getItem());
		}
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
			int note = (int) Math.round(array.getSilent(event.getIndex()) * scale) + PITCH_MARGIN;
			note = 127 - note;
			mc[0].noteOn(note, volume);
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
			Arrays.fill(releaseNext, false);
			mc[0].allNotesOff();
		}
	}
	
	public void cleanup()
	{
		synth.close();
	}
	
	public void setVolume(int vol)
	{
		volume = vol;
	}

}
