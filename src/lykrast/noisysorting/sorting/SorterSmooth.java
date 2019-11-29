package lykrast.noisysorting.sorting;

import lykrast.noisysorting.array.VisualArray;

public class SorterSmooth extends SorterAbstract {
	// https://fr.wikibooks.org/wiki/Impl%C3%A9mentation_d%27algorithmes_classiques/Algorithmes_de_tri/Smoothsort
	public SorterSmooth(VisualArray array) {
		super(array);
	}

	@Override
	protected void sort() throws InterruptedException {
		int size = a.getSize();
		int q = 1, r = 0, p = 1, b = 1, c = 1;

		while (q != size) {
			if (p % 8 == 3) {
				sift(r, b, c);
				p = (p + 1) / 4;
				
				int temp = b + c + 1;
				c = b;
				b = temp;
				
				temp = b + c + 1;
				c = b;
				b = temp;
			}
			else if (p % 4 == 1) {
				if (q + c < size) {
					sift(r, b, c);
				}
				else {
					trinkle(r, p, b, c);
				}
				do {
					int temp = b - c - 1;
					b = c;
					c = temp;
					
					p *= 2;
				}
				while (b != 1);
				p++;
			}
			q++;
			r++;
		}
		trinkle(r, p, b, c);

		while (q != 1) {
			q--;
			if (b == 1) {
				r--;
				p--;
				while (p % 2 == 0) {
					p /= 2;
					
					int temp = b + c + 1;
					c = b;
					b = temp;
				}
			}
			else if (b >= 3) {
				p--;
				r += c - b;
				if (p != 0) semitrinkle(r, p, b, c);
				
				int temp = b - c - 1;
				b = c;
				c = temp;
				
				p = 2 * p + 1;
				r += c;
				semitrinkle(r, p, b, c);
				
				temp = b - c - 1;
				b = c;
				c = temp;
				
				p = 2 * p + 1;
			}
		}
	}

	private void sift(int r, int b, int c) throws InterruptedException {
		while (b >= 3) {
			int r2 = r - b + c;

			if (a.get(r2) < a.get(r - 1)) {
				r2 = r - 1;

				int temp = b - c - 1;
				b = c;
				c = temp;
			}
			sleep();

			if (a.get(r) >= a.get(r2)) {
				b = 1;
			}
			else {
				a.swap(r, r2);
				r = r2;

				int temp = b - c - 1;
				b = c;
				c = temp;
			}
			sleep();
		}
	}

	private void trinkle(int r, int p, int b, int c) throws InterruptedException {
		while (p > 0) {
			int r3;

			while (p % 2 == 0) {
				p /= 2;

				int temp = b + c + 1;
				c = b;
				b = temp;
			}
			r3 = r - b;

			if (p == 1 || a.get(r3) <= a.get(r)) {
				if (p != 1) sleep();
				p = 0;
			}
			else {
				p--;
				if (b == 1) {
					a.swap(r, r3);
					sleep();
					r = r3;
				}
				else if (b >= 3) {
					int r2 = r - b + c;
					if (a.get(r2) < a.get(r - 1)) {
						r2 = r - 1;

						int temp = b - c - 1;
						b = c;
						c = temp;

						p *= 2;
					}
					sleep();

					if (a.get(r3) >= a.get(r2)) {
						a.swap(r, r3);
						r = r3;
					}
					else {
						a.swap(r, r2);
						r = r2;

						int temp = b - c - 1;
						b = c;
						c = temp;

						p = 0;
					}
					sleep();
				}
			}
		}
		sift(r, b, c);
	}

	private void semitrinkle(int r, int p, int b, int c) throws InterruptedException {
		int r1 = r - c;
		if (a.get(r1) > a.get(r)) {
			a.swap(r, r1);
			sleep();
			trinkle(r1, p, b, c);
		}
	}

}
