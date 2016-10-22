package ploygon;

import java.awt.Color;

public class Test {
	public static void main(String[] args) {
		// int[] x = { 3, 5, 5, 8, 10, 7, 5, 5, 1, 1, 3 };
		// int[] y = { 1, 1, 4, 4, 6, 8, 8, 7, 7, 3, 1 };

		int[] x = { 7, 10, 10, 7, 1, 4, 7 };
		int[] y = { 1, 1, 4, 7, 4, 4, 1 };

		// int[] x = { 2, 5, 11, 11, 5, 2, 2 };
		// int[] y = { 2, 1, 3, 8, 5, 7, 2 };

		// int[] x = { 1, 8, 8, 5, 1, 1 };
		// int[] y = { 1, 1, 6, 3, 7, 1 };

		// int[] x = { 1, 3, 8, 8, 5, 1, 1 };
		// int[] y = { 3, 3, 1, 6, 3, 7, 3 };
		PolygonScanLine p = new PolygonScanLine();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		p.Polyfill(x, y, Color.GREEN);

	}

}
