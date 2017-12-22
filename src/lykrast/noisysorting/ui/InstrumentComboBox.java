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
		setSelectedIndex(0);
	}
	
	public void changeSoundMaker(ArraySoundMaker asm)
	{
		for (ItemListener i : getItemListeners()) removeItemListener(i);
		addItemListener(asm);
		fireItemStateChanged(new ItemEvent(this, ItemEvent.ITEM_STATE_CHANGED, getSelectedItem(), ItemEvent.SELECTED));
	}

}
