package clipAlgorithm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;

import coordinateSystem.CoordinatePanel;

/**
 * @author asus
 *
 */
public class CohenSutherlandLineCliping extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int width = 800;
	public static int height = 600;
	private CoordinatePanel panel;
	public static final int LEFT = 1; // ����ֵ����
	public static final int RIGHT = 2;
	public static final int BOTTOM = 4;
	public static final int TOP = 8;
	public int xmin; // �ü��߿�߽�ֵ
	public int xmax;
	public int ymin;
	public int ymax;

	public CohenSutherlandLineCliping() {
		panel = new CoordinatePanel();
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.add(panel);
	}

	/**
	 * �����ı���(�����ʽ :CtCbCrCl)
	 * 
	 * @return
	 */
	public int encode(float x, float y) {
		int code = 0;
		if (x < xmin) {
			code |= LEFT;
		}
		if (x > xmax) {
			code |= RIGHT;
		}
		if (y < ymin) {
			code |= BOTTOM;
		}
		if (y > ymax) {
			code |= TOP;
		}
		return code;
	}

	/**
	 * ֱ�߲ü��㷨
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param x
	 * @param y
	 */
	public void CS_LineCliping(float x1, float y1, float x2, float y2, int x[],
			int[] y) {
		DrawLine(x1, y1, x2, y2,Color.RED);
		DrawPolygon(x, y, Color.GREEN);
		float r_x = 0,r_y = 0; //ֱ����߽罻��
		xmin = getMin(x); // ����ü��߿�߽�
		xmax = getMax(x);
		ymin = getMin(y);
		ymax = getMax(y);
//		System.out.println(xmin + " " + xmax + " " + ymin + " " + ymax);
		int code1 = encode(x1, y1); // �������
		int code2 = encode(x2, y2);
		int code;
		while (code1 != 0 | code2 != 0) { // �ü�
			if ((code1 & code2) != 0) {
				System.out.println("-------ֱ�߲��ڲü�����--------");
				return;
			}
			if(code1!=0){   
				code=code1;
			}else{
				code=code2;
			}
			if((LEFT&code)!=0){  //�ж���߽罻��
				r_x=xmin;
				r_y=y1+(xmin-x1)*(y2-y1)/(x2-x1);
				System.out.println("L");
			}else if((RIGHT&code)!=0){
				r_x=xmax;
				r_y=y1+(xmax-x1)*(y2-y1)/(x2-x1);
				System.out.println("R");
			}else if((BOTTOM&code)!=0){
				r_y=ymin;
				r_x=x1+(ymin-y1)*(x2-x1)/(y2-y1);
				System.out.println("B");
			}else if((TOP&code)!=0){
				r_y=ymax;
				r_x=x1+(ymax-y1)*(x2-x1)/(y2-y1);
				System.out.println("T");
			}
			if(code==code1){
				x1=r_x;
				y1=r_y;
				code1=encode(r_x, r_y);
			}else{
				x2=r_x;
				y2=r_y;
				code2=encode(r_x,r_y);
			}

		}
		DrawLine(x1, y1, x2, y2, Color.BLUE);
	}

	/**
	 * �����߽�
	 * 
	 * @param x
	 * @return
	 */
	public int getMax(int[] x) {
		int max = x[0];
		for (int i = 1; i < x.length; i++) {
			if (max < x[i]) {
				max = x[i];
			}
		}
		return max;
	}

	/**
	 * ����С�߽�
	 * 
	 * @param x
	 * @return
	 */
	public int getMin(int[] x) {
		int min = x[0];
		for (int i = 0; i < x.length; i++) {
			if (min > x[i]) {
				min = x[i];
			}
		}
		return min;
	}

	/**
	 * �������
	 * 
	 * @param x
	 * @param y
	 * @param color
	 */
	public void DrawPolygon(int[] x, int[] y, Color color) {
		Point p;
		int[] x1 = new int[x.length];
		int[] y1 = new int[y.length];
		for (int i = 0; i < y.length; i++) {
			p = panel.transform(x[i], y[i]);
			x1[i] = p.x;
			y1[i] = p.y;
		}
		Graphics g = this.panel.getGraphics();
		
		g.setColor(color);
		g.drawPolygon(x1, y1, x1.length);
	}
	/**
	 * ��ֱ��
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param color
	 */
	public void DrawLine(float x1, float y1, float x2, float y2,Color color) {
		Point p1 = this.panel.transform(x1, y1);
		Point p2 = this.panel.transform(x2, y2);
		Graphics g = panel.getGraphics();
		g.setColor(color);
		g.drawLine(p1.x, p1.y, p2.x, p2.y);
	}
}
