package lykrast.noisysorting.array;

import java.awt.Color;

public class VAEventSingle extends VAEventAbstract {
	private int index;
	private Color color;
	private boolean temporary;
	
	public VAEventSingle(int i, Color c)
	{
		this(i,c,true);
	}
	
	public VAEventSingle(int i, Color c, boolean temporary)
	{
		index = i;
		color = c;
		this.temporary = temporary;
	}
	
	public int getIndex()
	{
		return index;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public boolean isTemporary()
	{
		return temporary;
	}

}
