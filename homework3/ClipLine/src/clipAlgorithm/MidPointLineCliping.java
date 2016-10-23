package clipAlgorithm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;

import coordinateSystem.CoordinatePanel;
import coordinateSystem.FloatPoint;

public class MidPointLineCliping extends JFrame {
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
	public static float deviation = 0.02f; // �������
	public int xmin; // �ü��߿�߽�ֵ
	public int xmax;
	public int ymin;
	public int ymax;

	public MidPointLineCliping() {
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
	public void MP_LineCliping(float x1, float y1, float x2, float y2, int x[],
			int[] y) {
		DrawLine(x1, y1, x2, y2, Color.RED);
		DrawPolygon(x, y, Color.GREEN);
		xmin = getMin(x); // ����ü��߿�߽�
		xmax = getMax(x);
		ymin = getMin(y);
		ymax = getMax(y);
		FloatPoint fp1=PioneerVisiblePoint(x1, y1, x2, y2);
		System.out.println(fp1);
		if(fp1.VisiableFlag!=false){
			FloatPoint fp2=PioneerVisiblePoint(x2, y2, x1, y1);
			System.out.println(fp2);
			if(fp2.VisiableFlag!=false){
				DrawLine(fp1.x,fp1.y,fp2.x,fp2.y,Color.blue);
			}
		}

	}

	public FloatPoint PioneerVisiblePoint(float x1, float y1, float x2, float y2) {
		FloatPoint pv = new FloatPoint();
		pv.VisiableFlag = true;
		FloatPoint mid = new FloatPoint();
		int code1 = encode(x1, y1);
		int code2;
		int code;
		if (code1 == 0) {
			pv.x = x1;
			pv.y = y1;
		} else {
			code2 = encode(x2, y2);
			if ((code1 & code2) == 0) {
				while (true) {
					mid.x = (x1 + x2) / 2;
					mid.y = (y1 + y2) / 2;
					if (Math.abs(mid.x - x1) > deviation
							|| Math.abs(mid.y - y1) > deviation) {
						code = encode(mid.x, mid.y);
						if ((code1 & code) != 0) {
							x1 = mid.x;
							y1 = mid.y;
							code1 = code;
							if ((code1 & code2) != 0) {
								pv.setVisiableFlag(false);
								break;
							}
						} else {
							x2 = mid.x;
							y2 = mid.y;
							code2=code;
						}

					} else {
						pv.x = mid.x;
						pv.y = mid.y;
						break;
					}
				}
			} else {
				pv.VisiableFlag = false;
			}
		}
		return pv;

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
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param color
	 */
	public void DrawLine(float x1, float y1, float x2, float y2, Color color) {
		Point p1 = this.panel.transform(x1, y1);
		Point p2 = this.panel.transform(x2, y2);
		Graphics g = panel.getGraphics();
		g.setColor(color);
		g.drawLine(p1.x, p1.y, p2.x, p2.y);
	}

}
