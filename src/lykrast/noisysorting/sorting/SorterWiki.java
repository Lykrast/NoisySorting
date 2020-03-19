package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterWiki extends SorterAbstract {
	//https://github.com/BonzaiThePenguin/WikiSort
	//https://github.com/BonzaiThePenguin/WikiSort/blob/master/WikiSort.java
	//Rewritten for bad reasons don't do that
	
	//TODO do the cached version
	private int cacheSize = 0;

	public SorterWiki(VisualArray array) {
		super(array);
	}

	@Override
	protected void sort() throws InterruptedException {
		int size = a.getSize();
		
		//Sort for size 1, 2 or 3
		if (size < 4) {
			if (size == 3) {
				if (a.get(1) < a.get(0)) a.swap(0, 1);
				sleep();
				if (a.get(2) < a.get(1)) {
					a.swap(2, 1);
					sleep();
					if (a.get(1) < a.get(0)) a.swap(0, 1);
				}
				sleep();
			}
			else if (size == 2) {
				if (a.get(1) < a.get(0)) a.swap(1, 0);
				sleep();
			}
			
			return;
		}
		
		//Sort groups with networks, simplified to no longer be in place since doesn't matter here
		RangeIterator iterator = new RangeIterator(size, 4);
		while (!iterator.finished()) {
			Range range = iterator.nextRange();
			switch (range.length()) {
				case 8:
					networkSwap(range, 0, 1);
					networkSwap(range, 2, 3);
					networkSwap(range, 4, 5);
					networkSwap(range, 6, 7);
					networkSwap(range, 0, 2);
					networkSwap(range, 1, 3);
					networkSwap(range, 4, 6);
					networkSwap(range, 5, 7);
					networkSwap(range, 1, 2);
					networkSwap(range, 5, 6);
					networkSwap(range, 0, 4);
					networkSwap(range, 3, 7);
					networkSwap(range, 1, 5);
					networkSwap(range, 2, 6);
					networkSwap(range, 1, 4);
					networkSwap(range, 3, 6);
					networkSwap(range, 2, 4);
					networkSwap(range, 3, 5);
					networkSwap(range, 3, 4);
					break;
				case 7:
					networkSwap(range, 1, 2);
					networkSwap(range, 3, 4);
					networkSwap(range, 5, 6);
					networkSwap(range, 0, 2);
					networkSwap(range, 3, 5);
					networkSwap(range, 4, 6);
					networkSwap(range, 0, 1);
					networkSwap(range, 4, 5);
					networkSwap(range, 2, 6);
					networkSwap(range, 0, 4);
					networkSwap(range, 1, 5);
					networkSwap(range, 0, 3);
					networkSwap(range, 2, 5);
					networkSwap(range, 1, 3);
					networkSwap(range, 2, 4);
					networkSwap(range, 2, 3);
					break;
				case 6:
					networkSwap(range, 1, 2);
					networkSwap(range, 4, 5);
					networkSwap(range, 0, 2);
					networkSwap(range, 3, 5);
					networkSwap(range, 0, 1);
					networkSwap(range, 3, 4);
					networkSwap(range, 2, 5);
					networkSwap(range, 0, 3);
					networkSwap(range, 1, 4);
					networkSwap(range, 2, 4);
					networkSwap(range, 1, 3);
					networkSwap(range, 2, 3);
					break;
				case 5:
					networkSwap(range, 0, 1);
					networkSwap(range, 3, 4);
					networkSwap(range, 2, 4);
					networkSwap(range, 2, 3);
					networkSwap(range, 1, 4);
					networkSwap(range, 0, 3);
					networkSwap(range, 0, 2);
					networkSwap(range, 1, 3);
					networkSwap(range, 1, 2);
					break;
				case 4:
					networkSwap(range, 0, 1);
					networkSwap(range, 2, 3);
					networkSwap(range, 0, 2);
					networkSwap(range, 1, 3);
					networkSwap(range, 1, 2);
					break;
			}
		}
		if (size < 8) return;
		
		//Ranges to keep track of
		Range buffer1 = new Range(), buffer2 = new Range();
		Range blockA = new Range(), blockB = new Range();
		Range lastA = new Range(), lastB = new Range();
		Range firstA = new Range();
		Range A = new Range(), B = new Range();
		
		Pull[] pull = new Pull[2];
		pull[0] = new Pull();
		pull[1] = new Pull();
		
		//Merge higher levels
		while (true) {
			//Special branch if everything fits in the cache
			if (iterator.length() < cacheSize) {
				//TODO
			}
			else {
				int blockSize = (int)Math.sqrt(iterator.length());
				int bufferSize = (iterator.length() / blockSize) + 1;
				
				int index, last, count, pullIndex = 0;
				buffer1.set(0, 0);
				buffer2.set(0, 0);
				
				pull[0].reset();
				pull[1].reset();
				
				//Prepare to find 2 internal buffers
				int find = bufferSize + bufferSize;
				boolean findSeparate = false;
				
				if (blockSize <= cacheSize) {
					//If every blocks fits, only one buffer needed
					find = bufferSize;
				}
				else if (find > iterator.length()) {
					//Buffers will need different subarrays
					find = bufferSize;
					findSeparate = true;
				}
				
				//Go find the buffers
				iterator.begin();
				while (!iterator.finished()) {
					A = iterator.nextRange();
					B = iterator.nextRange();
					
					for (last = A.start, count = 1; count < find; last = index, count++) {
						index = findLastForward(a.getSilent(last), new Range(last + 1, A.end), find - count);
						if (index == A.end) break;
					}
					index = last;
					
					if (count >= bufferSize) {
						pull[pullIndex].range.set(A.start, B.end);
						pull[pullIndex].count = count;
						pull[pullIndex].from = index;
						pull[pullIndex].to = A.start;
						pullIndex = 1;
						
						if (count == bufferSize + bufferSize) {
							//Single continuous section, will be used as both buffers
							buffer1.set(A.start, A.start + bufferSize);
							buffer2.set(A.start + bufferSize, A.start + count);
							break;
						}
						else if (find == bufferSize + bufferSize) {
							//One buffer found, but another is needed
							buffer1.set(A.start, A.start + count);
							find = bufferSize;
						}
						else if (blockSize <= cacheSize) {
							//One buffer found and only needed one
							buffer1.set(A.start, A.start + count);
							break;
						}
						else if (findSeparate) {
							//One buffer found, need to find the other
							buffer1.set(A.start, A.start + count);
							findSeparate = false;
						}
						else {
							//Found a second buffer
							buffer2.set(A.start, A.start + count);
							break;
						}
					}
					else if (pullIndex == 0 && count > buffer1.length()) {
						//Keep track of the largest found buffer
						buffer1.set(A.start, A.start + count);

						pull[pullIndex].range.set(A.start, B.end);
						pull[pullIndex].count = count;
						pull[pullIndex].from = index;
						pull[pullIndex].to = A.start;
					}
					
					//Check B for unique values
					for (last = B.end - 1, count = 1; count < find; last = index - 1, count++) {
						index = findFirstBackward(a.getSilent(last), new Range(B.start, last), find - count);
						if (index == B.start) break;
					}
					index = last;
					
					if (count >= bufferSize) {
						pull[pullIndex].range.set(A.start, B.end);
						pull[pullIndex].count = count;
						pull[pullIndex].from = index;
						pull[pullIndex].to = B.end;
						pullIndex = 1;
						
						if (count == bufferSize + bufferSize) {
							//Single continuous section, will be used as both buffers
							buffer1.set(B.end - count, B.end - bufferSize);
							buffer2.set(B.end - bufferSize, B.end);
							break;
						}
						else if (find == bufferSize + bufferSize) {
							//One buffer found, but another is needed
							buffer1.set(B.end - count, B.end);
							find = bufferSize;
						}
						else if (blockSize <= cacheSize) {
							//One buffer found and only needed one
							buffer1.set(B.end - count, B.end);
							break;
						}
						else if (findSeparate) {
							//One buffer found, need to find the other
							buffer1.set(B.end - count, B.end);
							findSeparate = false;
						}
						else {
							//Found a second buffer
							if (pull[0].range.start == A.start) pull[0].range.end -= pull[1].count;
							buffer2.set(B.end - count, B.end);
							break;
						}
					}
					else if (pullIndex == 0 && count > buffer1.length()) {
						//Keep track of the largest found buffer
						buffer1.set(B.end - count, B.end);
						
						pull[pullIndex].range.set(A.start, B.end);
						pull[pullIndex].count = count;
						pull[pullIndex].from = index;
						pull[pullIndex].to = B.end;
					}
				}
				
				//Pull out the ranges to use as buffers
				for (pullIndex = 0; pullIndex < 2; pullIndex++) {
					int length = pull[pullIndex].count;
					
					if (pull[pullIndex].to < pull[pullIndex].from) {
						//Pulling values out to the left = start of A
						index = pull[pullIndex].from;
						for (count = 1; count < length; count++) {
							index = findFirstBackward(a.getSilent(index-1), new Range(pull[pullIndex].to, pull[pullIndex].from - (count-1)), length - count);
							Range range = new Range(index + 1, pull[pullIndex].from + 1);
							rotate(range.length() - count, range, true);
							pull[pullIndex].from = index + count;
						}
					}
					else if (pull[pullIndex].to > pull[pullIndex].from) {
						//Pulling values out to the right = end of B
						index = pull[pullIndex].from + 1;
						for (count = 1; count < length; count++) {
							index = findLastForward(a.getSilent(index), new Range(index, pull[pullIndex].to), length - count);
							Range range = new Range(pull[pullIndex].from, index - 1);
							rotate(count, range, true);
							pull[pullIndex].from = index - 1 - count;
						}
					}
				}
				
				//Adjust block and buffer size depending on the values found
				bufferSize = buffer1.length();
				blockSize = (iterator.length() / bufferSize) + 1;
				
				//Start to merge
				iterator.begin();
				while (!iterator.finished()) {
					A = iterator.nextRange();
					B = iterator.nextRange();
					
					//Remove parts of A or B used by the buffers
					int start = A.start;
					if (start == pull[0].range.start) {
						if (pull[0].from > pull[0].to) {
							A.start += pull[0].count;
							//Nothing to merge if the buffer takes all of A or B
							if (A.length() == 0) continue;
						}
						else if (pull[0].from < pull[0].to) {
							B.end -= pull[0].count;
							if (B.length() == 0) continue;
						}
					}
					if (start == pull[1].range.start) {
						if (pull[1].from > pull[1].to) {
							A.start += pull[1].count;
							if (A.length() == 0) continue;
						}
						else if (pull[1].from < pull[1].to) {
							B.end -= pull[1].count;
							if (B.length() == 0) continue;
						}
					}
					
					if (a.get(B.end - 1) < a.get(A.start)) {
						//Ranges are in reverse order, rotate to fix
						sleep();
						rotate(A.length(), new Range(A.start, B.end), true);
					}
					else {
						sleep();
						if (a.get(A.end) < a.get(A.end - 1)) {
							//Ranges aren't in order, need to be merged
							sleep();
							
							//Break A into blocks, firstA is uneven-sized first A block
							blockA.set(A.start, A.end);
							firstA.set(A.start, A.start + blockA.length() % blockSize);
							
							//Swap first value of each A block with value in buffer1
							int indexA = buffer1.start;
							for (index = firstA.end; index < blockA.end; index += blockSize) {
								a.swap(indexA, index);
								indexA++;
								sleep();
							}
							
							//Start rolling the A blocks through the B blocks
							lastA.set(firstA.start, firstA.end);
							lastB.set(0, 0);
							blockB.set(B.start, B.start + Math.min(blockSize, B.length()));
							blockA.start += firstA.length();
							indexA = buffer1.start;
							
							if (lastA.length() <= cacheSize) {
								//TODO cache
							}
							else if (buffer2.length() > 0) blockSwap(lastA.start, buffer2.start, lastA.length());
							
							if (blockA.length() > 0) {
								while (true) {
									//Separate for proper wait
									//(lastB.length() > 0 && a.get(lastB.end - 1) >= a.get(indexA)) || blockB.length() == 0
									boolean tmp = blockB.length() == 0;
									if (!tmp && lastB.length() > 0) {
										tmp = a.get(lastB.end - 1) >= a.get(indexA);
										sleep();
									}
									if (tmp) {
										//Find where to split previous B block
										int bSplit = binaryFirst(a.getSilent(indexA), lastB);
										int bRemaining = lastB.end - bSplit;
										
										//Swap minimum A block to beginning of A blocks
										int minA = blockA.start;
										for (int findA = minA + blockSize; findA < blockA.end; findA += blockSize) {
											if (a.get(findA) < a.get(minA)) minA = findA;
											sleep();
										}
										blockSwap(blockA.start, minA, blockSize);
										
										//Swap first item of previous A block back with its original value in buffer1
										a.swap(blockA.start, indexA);
										indexA++;
										sleep();
										
										//Locally merge previous A block with the B values that follow
										//Use external cache if it fits
										if (lastA.length() <= cacheSize); //TODO
										//Else use second internal buffer
										else if (buffer2.length() > 0) mergeInternal(lastA, new Range(lastA.end, bSplit), buffer2);
										//Else use a strictly in place merge
										else mergeInPlace(lastA, new Range(lastA.end, bSplit));
										
										if (buffer2.length() > 0 || blockSize <= cacheSize) {
											//Copy previous A block into the cache or buffer
											if (blockSize <= cacheSize); //TODO
											else blockSwap(blockA.start, buffer2.start, blockSize);
											
											blockSwap(bSplit, blockA.start + blockSize - bRemaining, bRemaining);
										}
										//No buffer2 to speed up rotation
										else rotate(blockA.start - bSplit, new Range(bSplit, blockA.start + blockSize), true);
										
										//Update range for remaining blocks
										lastA.set(blockA.start - bRemaining, blockA.start - bRemaining + blockSize);
										lastB.set(lastA.end, lastA.end + bRemaining);
										
										//If there are no more A blocks, step is finished
										blockA.start += blockSize;
										if (blockA.length() == 0) break;
									}
									else if (blockB.length() < blockSize) {
										//Move last B block to before the A blocks
										rotate(-blockB.length(), new Range(blockA.start, blockB.end), false);
										
										lastB.set(blockA.start, blockA.start + blockB.length());
										blockA.start += blockB.length();
										blockA.end += blockB.length();
										blockB.end = blockB.start;
									}
									else {
										//Roll leftmost A block to the end
										blockSwap(blockA.start, blockB.start, blockSize);
										lastB.set(blockA.start, blockA.start + blockSize);
										
										blockA.start += blockSize;
										blockA.end += blockSize;
										blockB.start += blockSize;
										blockB.end += blockSize;
										
										if (blockB.end > B.end) blockB.end = B.end;
									}
								}
							}
							
							//Merge last A block with remaining B values
							if (lastA.length() <= cacheSize); //TODO
							else if (buffer2.length() > 0) mergeInternal(lastA, new Range(lastA.end, B.end), buffer2);
							else mergeInPlace(lastA, new Range(lastA.end, B.end));
						}
						else sleep();
					}
				}
				
				//Sort the second buffer back then redistribute the buffers back
				insertionSort(buffer2);
				
				for (pullIndex = 0; pullIndex < 2; pullIndex++) {
					int unique = pull[pullIndex].count * 2;
					if (pull[pullIndex].from > pull[pullIndex].to) {
						//Values were pulled out to the left, redistribute back to the right
						Range buffer = new Range(pull[pullIndex].range.start, pull[pullIndex].range.start + pull[pullIndex].count);
						while (buffer.length() > 0) {
							index = findFirstForward(a.getSilent(buffer.start), new Range(buffer.end, pull[pullIndex].range.end), unique);
							int amount = index - buffer.end;
							rotate(buffer.length(), new Range(buffer.start, index), true);
							buffer.start += amount + 1;
							buffer.end += amount;
							unique -= 2;
						}
					}
					else if (pull[pullIndex].from < pull[pullIndex].to) {
						//Values were pulled out to the right, redistribute back to the left
						Range buffer = new Range(pull[pullIndex].range.end - pull[pullIndex].count, pull[pullIndex].range.end);
						while (buffer.length() > 0) {
							index = findLastBackward(a.getSilent(buffer.end - 1), new Range(pull[pullIndex].range.start, buffer.start), unique);
							int amount = buffer.start - index;
							rotate(amount, new Range(index, buffer.end), true);
							buffer.start -= amount;
							buffer.end -= (amount + 1);
							unique -= 2;
						}
					}
				}
			}
			
			if (!iterator.nextLevel()) break;
		}
	}
	
	private void networkSwap(Range range, int x, int y) throws InterruptedException {
		x += range.start;
		y += range.start;
		if (a.get(x) > a.get(y)) a.swap(x, y);
		sleep();
	}
	
	//Combine linear and binary search to reduce comparaisons when possible
	private int findFirstForward(int value, Range range, int unique) throws InterruptedException {
		if (range.length() == 0) return range.start;
		int index, skip = Math.max(range.length() / unique, 1);
		
		for (index = range.start + skip; a.get(index-1) < value; index += skip) {
			sleep();
			if (index >= range.end - skip) return binaryFirst(value, new Range(index, range.end));
		}
		
		return binaryFirst(value, new Range(index - skip, index));
	}
	
	private int findLastForward(int value, Range range, int unique) throws InterruptedException {
		if (range.length() == 0) return range.start;
		int index, skip = Math.max(range.length() / unique, 1);
		
		for (index = range.start + skip; value >= a.get(index-1); index += skip) {
			sleep();
			if (index >= range.end - skip) return binaryLast(value, new Range(index, range.end));
		}
		
		return binaryLast(value, new Range(index - skip, index));
	}
	
	private int findFirstBackward(int value, Range range, int unique) throws InterruptedException {
		if (range.length() == 0) return range.start;
		int index, skip = Math.max(range.length() / unique, 1);
		
		for (index = range.end - skip; index > range.start && a.get(index-1) >= value; index -= skip) {
			sleep();
			if (index < range.start + skip) return binaryFirst(value, new Range(range.start, index));
		}
		
		return binaryFirst(value, new Range(index, index + skip));
	}
	
	private int findLastBackward(int value, Range range, int unique) throws InterruptedException {
		if (range.length() == 0) return range.start;
		int index, skip = Math.max(range.length() / unique, 1);
		
		for (index = range.end - skip; index > range.start && value < a.get(index-1); index -= skip) {
			sleep();
			if (index < range.start + skip) return binaryLast(value, new Range(range.start, index));
		}
		
		return binaryLast(value, new Range(index, index + skip));
	}
	
	//Find index of last value in range equal to a[index], plus 1
	private int binaryLast(int value, Range range) throws InterruptedException {
		int start = range.start, end = range.end - 1;
		while (start < end) {
			int mid = start + (end - start)/2;
			if (value >= a.get(mid)) start = mid + 1;
			else end = mid;
			sleep();
		}
		if (start == range.end - 1) {
			//Separate for proper wait
			if (value >= a.get(start)) start++;
			sleep();
		}
		return start;
	}
	
	//Find index of last value in range equal to a[index]
	private int binaryFirst(int value, Range range) throws InterruptedException {
		int start = range.start, end = range.end - 1;
		while (start < end) {
			int mid = start + (end - start)/2;
			if (a.get(mid) < value) start = mid + 1;
			else end = mid;
			sleep();
		}
		if (start == range.end - 1) {
			//Separate for proper wait
			if (a.get(start) < value) start++;
			sleep();
		}
		return start;
	}
	
	//Rotate values in array ([0 1 2 3] becomes [1 2 3 0] if we rotate by 1)
	private void rotate(int amount, Range range, boolean useCache) throws InterruptedException {
		if (range.length() == 0) return;
		
		int split = (amount >= 0 ? range.start : range.end) + amount;
		Range range1 = new Range(range.start, split);
		Range range2 = new Range(split, range.end);
		
		if (useCache) {
			//TODO cache rotate
		}
		
		reverse(range1);
		reverse(range2);
		reverse(range);
	}
	
	private void reverse(Range range) throws InterruptedException {
		for (int index = range.length()/2 - 1; index >= 0; index--) {
			a.swap(range.start + index, range.end - index - 1);
			sleep();
		}
	}
	
	//Swap a series of values
	private void blockSwap(int start1, int start2, int blockSize) throws InterruptedException {
		for (int index = 0; index < blockSize; index++) {
			a.swap(start1 + index, start2 + index);
			sleep();
		}
	}
	
	//Merge with an internal buffer
	private void mergeInternal(Range A, Range B, Range buffer) throws InterruptedException {
		int aCount = 0, bCount = 0, insert = 0;
		
		if (B.length() > 0 && A.length() > 0) {
			while (true) {
				if (a.get(B.start + bCount) >= a.get(buffer.start + aCount)) {
					sleep();
					a.swap(A.start + insert, buffer.start + aCount);
					aCount++;
					insert++;
					sleep();
					if (aCount >= A.length()) break;
				}
				else {
					sleep();
					a.swap(A.start + insert, B.start + bCount);
					bCount++;
					insert++;
					sleep();
					if (bCount >= B.length()) break;
				}
			}
		}
		
		blockSwap(buffer.start + aCount, A.start + insert, A.length() - aCount);
	}
	
	//Merge without a buffer, bad complexity but works in the specific situations it is used
	private void mergeInPlace(Range A, Range B) throws InterruptedException {
		if (A.length() == 0 || B.length() == 0) return;
		
		A = new Range(A.start, A.end);
		B = new Range(B.start, B.end);
		
		while (true) {
			//Find first place in B where first item in A fits
			int mid = binaryFirst(a.getSilent(A.start), B);
			
			//Rotate A in place
			int amount = mid - A.end;
			rotate(-amount, new Range(A.start, mid), true);
			if (B.end == mid) break;
			
			//Calculate the new ranges
			B.start = mid;
			A.set(A.start + amount, B.start);
			A.start = binaryLast(A.start, A);
			if (A.length() == 0) break;
		}
	}
	
	private void insertionSort(Range range) throws InterruptedException {
		for (int i = range.start + 1; i < range.end; i++) {
			for (int j = i; j > range.start && a.get(j) < a.get(j - 1); j--) {
				a.swap(j, j-1);
				sleep();
			}
		}
	}
	
	private static class Range {
		public int start, end;
		
		public Range() {
			this(0,0);
		}

		public Range(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		public void set(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		public int length() {
			return end - start;
		}
	}
	

	private static class Pull {
		public int from, to, count;
		public Range range;
		
		public Pull() {
			range = new Range();
		}
		
		public void reset() {
			range.set(0,0);
			from = 0;
			to = 0;
			count = 0;
		}
	}
	
	private static class RangeIterator {
		public int size, power;
		public int numerator, decimal;
		public int denominator, decimalStep, numeratorStep;
		
		// 63 -> 32, 64 -> 64, etc.
		private int floorPowerOfTwo(int value) {
			int x = value;
			x = x | (x >> 1);
			x = x | (x >> 2);
			x = x | (x >> 4);
			x = x | (x >> 8);
			x = x | (x >> 16);
			return x - (x >> 1);
		}
		
		public RangeIterator(int size, int minLevel) {
			this.size = size;
			power = floorPowerOfTwo(size);
			denominator = power / minLevel;
			numeratorStep = size % denominator;
			decimalStep = size / denominator;
			begin();
		}
		
		public void begin() {
			numerator = 0;
			decimal = 0;
		}
		
		public Range nextRange() {
			int start = decimal;
			
			decimal += decimalStep;
			numerator += numeratorStep;
			if (numerator >= denominator) {
				numerator -= denominator;
				decimal++;
			}
			
			return new Range(start, decimal);
		}
		
		public boolean nextLevel() {
			decimalStep += decimalStep;
			numeratorStep += numeratorStep;
			if (numeratorStep >= denominator) {
				numeratorStep -= denominator;
				decimalStep++;
			}
			
			return decimalStep < size;
		}
		
		public boolean finished() {
			return decimal >= size;
		}
		
		public int length() {
			return decimalStep;
		}
	}

}
