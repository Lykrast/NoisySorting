package lykrast.noisysorting.array;

public class VAEventSingle extends VAEventAbstract {
	private int index;
	private VAItemStatus status;
	private boolean temporary;
	
	public VAEventSingle(int i, VAItemStatus s)
	{
		this(i,s,true);
	}
	
	public VAEventSingle(int i, VAItemStatus s, boolean temporary)
	{
		index = i;
		status = s;
		this.temporary = temporary;
	}
	
	public int getIndex()
	{
		return index;
	}
	
	public VAItemStatus getStatus()
	{
		return status;
	}
	
	public boolean isTemporary()
	{
		return temporary;
	}

}
