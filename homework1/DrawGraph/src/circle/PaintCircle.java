package circle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;

import line.PaintPanel;

public class PaintCircle extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int width = 800;
	public static int height = 600;
	PaintPanel panel; // 坐标系面板
	public static int wait_time = 300; // 画点间隔时间
	public static int space = 50;

	public PaintCircle() {
		this.setSize(width, height);
		this.setVisible(true);
		panel = new PaintPanel();
		this.setLocationRelativeTo(null);
		this.add(panel);
	}

	/**
	 * 中点画圆法
	 * 
	 * @param x
	 * @param y
	 * @param r
	 * @param color
	 */
	public void MidPointCircle(int x0, int y0, int r, Color color) {
		int x, y;
		float d;
		x = x0;
		y = y0 + r;
		d = (float) (1.25 - r);
		CirclePoint(x, y, color);
		while (x < y) {
			if (d < 0) {
				d += 2 * x + 3;
			} else {
				d += 2 * (x - y) + 5;
				y--;
			}
			x++;
			delay_time(500);
			CirclePoint(x, y, color);
			System.out.println(x + " " + y);
		}
	}

	void CirclePoint(int x, int y, Color color) {
		drawPixel(x, y, color);
		drawPixel(x, -y, color);
		drawPixel(-x, y, color);
		drawPixel(-x, -y, color);
		drawPixel(y, x, color);
		drawPixel(y, -x, color);
		drawPixel(-y, x, color);
		drawPixel(-y, -x, color);
	}

	void delay_time(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 画像素点
	 * 
	 * @param x
	 * @param y
	 * @param color
	 */
	public void drawPixel(int x, int y, Color color) {
		Graphics g = panel.getGraphics();
		Point p = panel.transform(x, y);
		g.setColor(color);
		g.fillOval(p.x - 5, p.y - 5, 10, 10);
	}

	public void DrawCircle(int x, int y, int r) {
		Graphics g = panel.getGraphics();
		Point p = panel.transform(x, y);
		g.setColor(Color.black);
		g.drawOval(p.x - r * space, p.y - r * space, 2 * r * space, 2 * r
				* space);
	}
}
