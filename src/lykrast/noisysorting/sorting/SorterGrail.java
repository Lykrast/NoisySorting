package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterGrail extends SorterAbstract {
	//https://github.com/Mrrl/GrailSort
	
	//TODO do the cached version
	private int cacheSize = 0;
	private int[] extCache = null;

	//TODO some more cleanup
	public SorterGrail(VisualArray array) {
		super(array);
	}

	@Override
	protected void sort() throws InterruptedException {
		int len = a.getSize();
		
		if (len < 16) {
			sortIns(0, len);
			return;
		}
		
		//Find the keys
		int lblock = 1;
		while (lblock * lblock < len) lblock *= 2;
		int nkeys = ((len-1) / lblock) + 1;
		int findkeys = findKeys(len, nkeys+lblock);
		boolean havebuf = true;
		
		if (findkeys < nkeys+lblock) {
			if (findkeys < 4) {
				lazyStableSort(len);
				return;
			}
			nkeys = lblock;
			while (nkeys > findkeys) nkeys /= 2;
			havebuf = false;
			lblock = 0;
		}
		//Make the blocks
		int ptr = lblock + nkeys;
		int cbuf = havebuf ? lblock : nkeys;
		if (havebuf) buildBlocks(ptr, len-ptr, cbuf, extCache, cacheSize);
		else buildBlocks(ptr, len-ptr, cbuf, null, 0);
		
		//Combine the blocks
		while (len-ptr > (cbuf *= 2)) {
			int lb = lblock;
			boolean chavebuf = havebuf;
			if (!havebuf) {
				if (nkeys > 4 && nkeys/8*nkeys >= cbuf) {
					lb = nkeys / 2;
					chavebuf = true;
				}
				else {
					int nk = 1;
					long s = cbuf * findkeys / 2;
					while (nk < nkeys && s != 0) {
						nk *= 2;
						s /= 8;
					}
					lb = (2*cbuf)/nk;
				}
			}
			
			combineBlocks(0, ptr, len-ptr, cbuf, lb, chavebuf, chavebuf && lb <= cacheSize ? extCache : null);
		}
		
		sortIns(0, ptr);
		mergeWithoutBuffer(0, ptr, len-ptr);
	}
	
	private void sortIns(int pos, int len) throws InterruptedException {
		for (int i = 1; i < len; i++) {
			for (int j = i-1; j >= 0 && a.get(pos+j+1) < a.get(pos+j); j--) {
				a.swap(pos+j+1, pos+j);
				sleep();
			}
		}
	}
	
	private int findKeys(int len, int nkeys) throws InterruptedException {
		int h = 1, h0 = 0;
		int u = 1, r;
		while (u < len && h < nkeys) {
			r = binSearchLeft(h0, h, u);
			//No sleep on that get because it looks awkward
			if (r == h || a.get(u) != a.get(h0 + r)) {
				rotate(h0, h, u-(h0+h));
				h0 = u - h;
				rotate(h0+r, h-r, 1);
				h++;
			}
			u++;
		}
		rotate(0, h0, h);
		return h;
	}
	
	private int binSearchLeft(int pos, int len, int key) throws InterruptedException {
		int min = -1, max = len;
		while (min < max-1) {
			int mid = min + ((max-min) >> 1);
			if (a.get(pos+mid) >= a.get(key)) max = mid;
			else min = mid;
			sleep();
		}
		return max;
	}
	
	private int binSearchRight(int pos, int len, int key) throws InterruptedException {
		int min = -1, max = len;
		while (min < max-1) {
			int mid = min + ((max-min) >> 1);
			if (a.get(pos+mid) > a.get(key)) max = mid;
			else min = mid;
			sleep();
		}
		return max;
	}
	
	private void rotate(int pos, int l1, int l2) throws InterruptedException {
		while (l1 > 0 && l2 > 0) {
			if (l1 <= l2) {
				swapN(pos, pos+l1, l1);
				pos += l1;
				l2 -= l1;
			}
			else {
				swapN(pos+(l1-l2), pos+l1, l2);
				l1 -= l2;
			}
		}
	}
	
	private void swapN(int o1, int o2, int n) throws InterruptedException {
		while (n-- > 0) {
			a.swap(o1++, o2++);
			sleep();
		}
	}
	
	private void lazyStableSort(int len) throws InterruptedException {
		for (int m = 1; m < len; m += 2) {
			if (a.get(m-1) > a.get(m)) a.swap(m-1, m);
			sleep();
		}
		for (int h = 2; h < len; h *= 2) {
			int p0 = 0;
			int p1 = len - 2*h;
			while (p0 <= p1) {
				mergeWithoutBuffer(p0, h, h);
				p0 += 2*h;
			}
			int rest = len - p0;
			if (rest > h) mergeWithoutBuffer(p0, h, rest-h);
		}
	}
	
	private void mergeWithoutBuffer(int pos, int len1, int len2) throws InterruptedException {
		int h;
		if (len1 < len2) {
			while (len1 > 0) {
				h = binSearchLeft(pos + len1, len2, pos);
				if (h != 0) {
					rotate(pos, len1, h);
					pos += h;
					len2 -= h;
				}
				if (len2 == 0) break;
				//Changed from do while to have a proper wait if a comparaison is done
				while (true) {
					pos++;
					len1--;
					
					if (len1 <= 0) break;
					boolean cmp = a.get(pos) <= a.get(pos+len1);
					sleep();
					if (!cmp) break;
				}
			}
		}
		else {
			while (len2 > 0) {
				h = binSearchRight(pos, len1, pos+(len1+len2-1));
				if (h != len1) {
					rotate(pos+h, len1-h, len2);
					len1 = h;
				}
				if (len1 == 0) break;
				//Changed from do while to have a proper wait if a comparaison is done
				while (true) {
					len2--;
					
					if (len2 <= 0) break;
					boolean cmp = a.get(pos+len1-1) <= a.get(pos+len1+len2-1);
					sleep();
					if (!cmp) break;
				}
			}
		}
	}
	
	private void buildBlocks(int pos, int len, int K, int[] extbuf, int lextbuf) throws InterruptedException {
		int h, kbuf;
		kbuf = K < lextbuf ? K : lextbuf;
		while ((kbuf & (kbuf-1)) != 0) kbuf &= kbuf-1;
		
		if (kbuf > 0) {
			System.err.println("You're not supposed to be here");
			//TODO version with external cache
			//since the cache is at 0, lextbuf will always be 0 so kbuf will also be 0
			
			//Part of code to make h initialized for the compiler
			for (h = 2; h < kbuf; h *= 2);
		}
		else {
			for (int m = 1; m < len; m += 2) {
				int u = 0;
				if (a.get(pos+(m-1)) > a.get(pos+m)) u = 1;
				sleep();
				
				a.swap(pos+(m-3), pos+(m-1+u));
				sleep();
				a.swap(pos+(m-2), pos+(m-u));
				sleep();
			}
			if (len % 2 != 0) {
				a.swap(pos+(len-1), pos+(len-3));
				sleep();
			}
			pos -= 2;
			h = 2;
		}
		for (; h < K; h *= 2) {
			int p0 = 0;
			int p1 = len - 2*h;
			
			while (p0 <= p1) {
				mergeLeft(pos+p0, h, h, -h);
				p0 += 2*h;
			}
			
			int rest = len-p0;
			if (rest > h) mergeLeft(pos+p0, h, rest-h, -h);
			else rotate(pos+p0-h, h, rest);
			
			pos -= h;
		}
		
		int restk = len % (2*K);
		int p = len - restk;
		if (restk <= K) rotate(pos+p, restk, K);
		else mergeRight(pos+p, K, restk-K, K);
		
		while (p > 0) {
			p -= 2*K;
			mergeRight(pos+p, K, K, K);
		}
	}
	
	private void mergeLeft(int pos, int l1, int l2, int m) throws InterruptedException {
		int p0 = 0, p1 = l1;
		l2 += l1;
		
		while (p1 < l2) {
			if (p0 == l1 || a.get(pos+p0) > a.get(pos+p1)) a.swap(pos+(m++), pos+(p1++));
			else a.swap(pos+(m++), pos+(p0++));
			sleep();
		}
		
		if (m != p0) swapN(pos+m, pos+p0, l1-p0);
	}
	
	private void mergeRight(int pos, int l1, int l2, int m) throws InterruptedException {
		int p0 = l1+l2+m-1, p2 = l1+l2-1, p1 = l1-1;
		
		while (p1 >= 0) {
			if (p2 < l1 || a.get(pos+p1) > a.get(pos+p2)) a.swap(pos+(p0--), pos+(p1--));
			else a.swap(pos+(p0--), pos+(p2--));
			sleep();
		}
		
		if (p2 != p0) {
			while (p2 >= l1) {
				a.swap(pos+(p0--), pos+(p2--));
				sleep();
			}
		}
	}
	
	private void combineBlocks(int keys, int pos, int len, int ll, int lblock, boolean havebuf, int[] extbuf) throws InterruptedException {
		int nblk, midkey, lrest, u, p, v, kc, nbl2, llast;
		int arr1;
		
		int m = len / (2*ll);
		lrest = len % (2*ll);
		//nkeys = (2*ll) / lblock;
		if (lrest <= ll) {
			len -= lrest;
			lrest = 0;
		}
		//TODO cached version
		if (extbuf != null);
		for (int b = 0; b <= m; b++) {
			if (b == m && lrest == 0) break;
			
			arr1 = pos + b*2*ll;
			nblk = (b == m ? lrest : 2*ll) / lblock;
			sortIns(keys, nblk+(b == m ? 1 : 0));
			midkey = ll / lblock;
			
			for (u = 1; u < nblk; u++) {
				p = u-1;
				for (v = u; v < nblk; v++) {
					kc = Integer.compare(a.get(arr1+p*lblock), a.get(arr1+v*lblock));
					if (kc > 0 || (kc == 0 && a.get(keys+p) > a.get(keys+v))) p = v;
					sleep();
				}
				if (p != u-1) {
					swapN(arr1+(u-1)*lblock, arr1+p*lblock, lblock);
					a.swap(keys+(u-1), keys+p);
					if (midkey == u-1 || midkey == p) midkey ^= (u-1)^p;
					sleep();
				}
			}
			nbl2 = llast = 0;
			if (b == m) llast = lrest % lblock;
			if (llast != 0) {
				while (nbl2 < nblk && a.get(arr1+nblk*lblock) < a.get(arr1+(nblk-nbl2-1)*lblock)) {
					nbl2++;
					sleep();
				}
			}
			//TODO cached version
			if (extbuf != null);
			else mergeBuffersLeft(keys, keys+midkey, arr1, nblk-nbl2, lblock, havebuf, nbl2, llast);
		}
		if (extbuf != null) {
			//TODO cached version
		}
		else if (havebuf) {
			while (--len >= 0) {
				a.swap(pos+len, pos+len-lblock);
				sleep();
			}
		}
	}
	
	private void mergeBuffersLeft(int keys, int midkey, int pos, int nblock, int lblock, boolean havebuf, int nblock2, int llast) throws InterruptedException {
		int l, prest, lrest, frest, pidx, cidx, fnext;
		
		if (nblock == 0) {
			l = nblock2*lblock;
			if (havebuf) mergeLeft(pos, l, llast, -lblock);
			else mergeWithoutBuffer(pos, l, llast);
			return;
		}
		
		lrest = lblock;
		frest = a.get(keys) < a.get(midkey) ? 0 : 1;
		pidx = lblock;
		sleep();
		
		for (cidx = 1; cidx < nblock; cidx++, pidx += lblock) {
			prest = pidx - lrest;
			fnext = a.get(keys+cidx) < a.get(midkey) ? 0 : 1;
			sleep();
			if (fnext == frest) {
				if (havebuf) swapN(pos+prest-lblock, pos+prest, lrest);
				prest = pidx;
				lrest = lblock;
			}
			else {
				if (havebuf) {
					int[] res = smartMergeWithBuffer(pos+prest, lrest, frest, lblock, lblock);
					lrest = res[0];
					frest = res[1];
				}
				else {
					int[] res = smartMergeWithoutBuffer(pos+prest, lrest, frest, lblock);
					lrest = res[0];
					frest = res[1];
				}
			}
		}
		
		prest = pidx - lrest;
		if (llast != 0) {
			//plast = pidx + lblock*nblock2;
			if (frest != 0) {
				if (havebuf) swapN(pos+prest-lblock, pos+prest, lrest);
				prest = pidx;
				lrest = lblock*nblock2;
				frest = 0;
			}
			else lrest += lblock*nblock2;
			
			if (havebuf) mergeLeft(pos+prest, lrest, llast, -lblock);
			else mergeWithoutBuffer(pos+prest, lrest, llast);
		}
		else if (havebuf) swapN(pos+prest, pos+(prest-lblock), lrest);
	}
	
	private int[] smartMergeWithBuffer(int pos, int alen1, int atype, int len2, int lkeys) throws InterruptedException {
		int p0 = -lkeys, p1 = 0, p2 = alen1, q1 = p2, q2 = p2+len2;
		int ftype = 1 - atype;
		
		while (p1 < q1 && p2 < q2) {
			if (Integer.compare(a.get(pos+p1), a.get(pos+p2)) - ftype < 0) a.swap(pos+(p0++), pos+(p1++));
			else a.swap(pos+(p0++), pos+(p2++));
			sleep();
		}
		if (p1 < q1) {
			alen1 = q1 - p1;
			while (p1 < q1) {
				a.swap(pos+(--q1), pos+(--q2));
				sleep();
			}
		}
		else {
			alen1 = q2 - p2;
			atype = ftype;
		}
		return new int[]{alen1, atype};
	}
	
	private int[] smartMergeWithoutBuffer(int pos, int alen1, int atype, int _len2) throws InterruptedException {
		int len1, len2, ftype, h;
		
		if (_len2 == 0) return new int[]{alen1, atype};
		
		len1 = alen1;
		len2 = _len2;
		ftype = 1 - atype;
		//Separate ifs for the proper wait if compared
		if (len1 != 0) {
			if (Integer.compare(a.get(pos+(len1-1)), a.get(pos+len1)) - ftype >= 0) {
				sleep();
				while (len1 != 0) {
					h = ftype != 0 ? binSearchLeft(pos+len1, len2, pos) : binSearchRight(pos+len1, len2, pos);
					if (h != 0) {
						rotate(pos, len1, h);
						pos += h;
						len2 -= h;
					}
					if (len2 == 0) {
						alen1 = len1;
						return new int[]{alen1, atype};
					}
					//Changed from do while to have a proper wait if a comparaison is done
					while (true) {
						pos++;
						len1--;
						
						if (len1 == 0) break;
						boolean cmp = a.get(pos) < a.get(pos+len1);
						sleep();
						if (!cmp) break;
					}
				}
			}
			else sleep();
		}
		
		alen1 = len2;
		atype = ftype;
		
		return new int[]{alen1, atype};
	}

}
