package lykrast.noisysorting.ui;

import javax.swing.JScrollPane;

public class SortScrollPane extends JScrollPane {
	private static final long serialVersionUID = 1L;
	private SortList list;
	
	public SortScrollPane(SortList l)
	{
		super(l, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		list = l;
	}
	
	public SortList getList()
	{
		return list;
	}
	
	public String toString()
	{
		return list.toString();
	}

}
