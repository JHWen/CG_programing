package test;

import clipAlgorithm.MidPointLineCliping;

public class MidPointTest {

	public static void main(String[] args) {
		float x1 = 3; // ´ý²Ã¼ôµÄÏß¶Î
		float y1 = 1;
		float x2 = 13;
		float y2 = 8;
		// ²âÊÔÊý¾Ý
//		 x1 = 10.5f; y1 = 0.5f; x2 = 12; y2 = 2.5f;
//		 x1 = 6; y1 = 4; x2 = 10; y2 = 6;
		int[] x = { 4, 11, 11, 4, 4 }; // ²Ã¼ô´°¿Ú
		int[] y = { 2, 2, 7, 7, 2 };
		MidPointLineCliping MidPoint=new MidPointLineCliping();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MidPoint.MP_LineCliping(x1, y1, x2, y2, x, y);
		
		
	}

}
