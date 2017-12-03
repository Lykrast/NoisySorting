package lykrast.noisysorting.ui;

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

}
