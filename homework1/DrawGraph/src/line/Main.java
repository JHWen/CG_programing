package line;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PaintLine line=new PaintLine();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		line.MidLine(0, 0, 5, 2);
//		line.MidLine(0, 0, 2, 4);
//		line.DDALine(0, 0, 5, 2);
		line.DDALine(1, 1, 2, 4);
//		line.BresenhamLine(0, 0, 5, 2);
//		line.BresenhamLine(0, 0, 2, 4);
	}

}
