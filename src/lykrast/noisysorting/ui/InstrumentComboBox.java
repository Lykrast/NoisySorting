package lykrast.noisysorting.ui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.sound.midi.Instrument;
import javax.sound.midi.Synthesizer;
import javax.swing.JComboBox;

public class InstrumentComboBox extends JComboBox<Instrument> {
	private static final long serialVersionUID = 1L;
	
	public InstrumentComboBox(Synthesizer synth)
	{
		super(synth.getAvailableInstruments());
		setSelectedIndex(findSquare(synth));
	}
	
	private static int findSquare(Synthesizer synth) {
		Instrument[] instruments = synth.getAvailableInstruments();
		
		//Hacky way of finding the square wave
		//Defaults to first instrument if none is found
		int index = 0;
		for (int i = 0; i < instruments.length; i++) {
			if (instruments[i].getName().contains("Square")) {
				index = i;
				break;
			}
		}
		
		return index;
	}
	
	public void forceChangeEvent() {
		fireItemStateChanged(new ItemEvent(this, ItemEvent.ITEM_STATE_CHANGED, getSelectedItem(), ItemEvent.SELECTED));
	}
	
	public void changeSoundMaker(ArraySoundMaker asm)
	{
		for (ItemListener i : getItemListeners()) removeItemListener(i);
		addItemListener(asm);
		forceChangeEvent();
	}

}
