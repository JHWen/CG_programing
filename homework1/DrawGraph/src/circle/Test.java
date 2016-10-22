package circle;

import java.awt.Color;

public class Test {

	public static void main(String[] args) {
		PaintCircle circle=new PaintCircle();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		circle.DrawCircle(0, 0, 4);
		circle.MidPointCircle(0, 0, 4, Color.GREEN);
	}

}
