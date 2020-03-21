package lykrast.noisysorting.ui.sortlist;

import java.util.ArrayList;
import java.util.List;

import lykrast.noisysorting.sorting.*;
import lykrast.noisysorting.ui.selector.sorter.*;

public class SortListDistribution extends SortList {
	private static final long serialVersionUID = 1L;
	private static SelectorAbstract[] sorts = new SelectorAbstract[0];
	
	static
	{
		List<SelectorAbstract> list = new ArrayList<>();
		int[] radixes = {2, 4, 8, 10};
		list.add(new Selector("Counting Sort", SorterCounting::new, "O(n*m) m largest value", "Counts how many elements are there of each value in the list in an external array, then uses that to reconstruct the list sorted."));
		for (int r : radixes)	list.add(new Selector("Radix Sort (LSD) - Radix " + r, a -> new SorterRadixLSD(a, r), "O(n*logm) m largest value", "Distributes the elements in external buckets depending on their least significant digit in base " + r + ", then repeats through all digits to sort the list."));
		for (int r : radixes)	list.add(new Selector("Radix Sort (MSD) - Radix " + r, a -> new SorterRadixMSD(a, r), "O(n*logm) m largest value", "Distributes the elements in external buckets depending on their most significant digit in base " + r + ", then recursively repeats through all digits to sort the list."));
		for (int r : radixes)	list.add(new Selector("In-place Radix Sort (LSD) - Radix " + r, a -> new SorterRadixLSDInPlace(a, r), "?", "A variant of Radix Sort (LSD) by w0rthy that does not use an external array to store buckets. Several pauses are omitted for aesthetic reasons."));
		list.add(new Selector("In-place Radix Sort (MSD)", SorterRadixMSDInPlace::new, "?", "A variant of Radix Sort (MSD) - Radix 2 that does not store its buckets externally."));
		for (int r : radixes) list.add(new Selector("American Flag Sort - Radix " + r, a -> new SorterAmericanFlag(a, r), "?", "A variant of Radix Sort (MSD) - Radix " + r + " that counts how many elements are in each bucket then swaps them into place instead of reconstructing the sublists."));
		list.add(new Selector("Shuffle Sort", SorterShuffle::new, "?", "A sort by John Cohen. Recursively sorts the first 1/8 of the list, then uses it as delimiters for buckets where all the remaining elements are placed. Then concatenates the buckets in order and recursively sorts them."));
		list.add(new Selector("Bead Sort", SorterBead::new, "?", "A sort by Joshua J. Arulanandham, Cristian S. Calude and Michael J. Dinneen. Turns the numbers in the list into beads then let them fall, sorting the list."));
		list.add(new Selector("Flash Sort", SorterFlash::new, "O(n^2)", "A sort by Karl-Dietrich Neubert. Estimates the position of each element in the list based on their value, assuming the distribution is uniform, then finishes sorting the array with Insertion Sort."));
		
		//Collections.sort(list);
		sorts = list.toArray(sorts);
	}
	
	public SortListDistribution()
	{
		super(sorts);
	}
	
	@Override
	public String toString()
	{
		return "Distribution";
	}

}
