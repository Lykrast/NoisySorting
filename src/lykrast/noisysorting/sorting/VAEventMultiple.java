package lykrast.noisysorting.sorting;

public class VAEventMultiple extends VAEventAbstract {
	private VAEventAbstract[] array;
	
	public VAEventMultiple(VAEventAbstract... events)
	{
		array = events;
	}
	
	public VAEventAbstract[] getEvents()
	{
		return array;
	}

}
