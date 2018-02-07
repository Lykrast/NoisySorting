package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterArraysPrimitive extends SorterAbstract {
	public SorterArraysPrimitive(VisualArray array)
	{
		super(array);
	}

	//http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/6-b14/java/util/Arrays.java#Arrays.sort%28int%5B%5D%29
	//Rewritten a bit to fit sleeps in
	@Override
	protected void sort() throws InterruptedException {
		sort1(0, a.getSize());
	}
	
	private void sort1(int off, int len) throws InterruptedException
	{
		if (len < 7) {
            for (int i=off; i<len+off; i++)
                for (int j=i; j>off; j--)
                {
                	if (a.get(j-1)>a.get(j))
                	{
                		a.swap(j, j-1);
                        sleep();
                	}
                	else
                	{
                		sleep();
                		break;
                	}
                }
            return;
        }
		
		// Choose a partition element, v
        int m = off + (len >> 1);       // Small arrays, middle element
        if (len > 7) {
            int l = off;
            int n = off + len - 1;
            if (len > 40) {        // Big arrays, pseudomedian of 9
                int s = len/8;
                l = med3(l,     l+s, l+2*s);
                m = med3(m-s,   m,   m+s);
                n = med3(n-2*s, n-s, n);
            }
            m = med3(l, m, n); // Mid-size, med of 3
        }
        int v = a.getSilent(m);
        
        // Establish Invariant: v* (<v)* (>v)* v*
        int ia = off, b = ia, c = off + len - 1, d = c;
        while(true) {
            while (b <= c && a.get(b) <= v) {
                if (a.get(b) == v)
                    a.swap(ia++, b);
                b++;
                sleep();
            }
            while (c >= b && a.get(c) >= v) {
                if (a.get(c) == v)
                    a.swap(c, d--);
                c--;
                sleep();
            }
            if (b > c)
                break;
            a.swap(b++, c--);
            sleep();
        }

        // Swap partition elements back to middle
        int s, n = off + len;
        s = Math.min(ia-off, b-ia  );
        vecswap(off, b-s, s);
        s = Math.min(d-c,   n-d-1);
        vecswap(b,   n-s, s);

        // Recursively sort non-partition-elements
        if ((s = b-ia) > 1)
            sort1(off, s);
        if ((s = d-c) > 1)
            sort1(n-s, s);
	}
	
	/**
     * Returns the index of the median of the three indexed integers.
     */
    private int med3(int a, int b, int c) throws InterruptedException {
    	int med = (this.a.get(a) < this.a.get(b) ?
                (this.a.get(b) < this.a.get(c) ? b : this.a.get(a) < this.a.get(c) ? c : a) :
                (this.a.get(b) > this.a.get(c) ? b : this.a.get(a) > this.a.get(c) ? c : a));
    	sleep();
        return med;
    }
    
    /**
     * Swaps x[a .. (a+n-1)] with x[b .. (b+n-1)].
     */
    private void vecswap(int a, int b, int n) throws InterruptedException {
        for (int i=0; i<n; i++, a++, b++)
        {
            this.a.swap(a, b);
            sleep();
        }
    }

}
