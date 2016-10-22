package line;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;

public class PaintLine extends JFrame {
	public static int width = 800;
	public static int height = 600;
	PaintPanel panel; // 坐标系面板
	public static int wait_time = 300; // 画点间隔时间
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PaintLine() {
		this.setSize(width, height);
		this.setVisible(true);
		panel = new PaintPanel();
		this.setLocationRelativeTo(null);
		this.add(panel);

	}

	// 中点画线法
	public void MidLine(int x0, int y0, int x1, int y1) {
		DrawLine(x0, y0, x1, y1);
		int a, b, d, d1, d2, x, y;
		a = y0 - y1;
		b = x1 - x0;
		x = x0;
		y = y0;
		drawPixel(x, y, Color.green);
		if (Math.abs(b) > Math.abs(a)) {
			d = 2 * a + b;
			d1 = 2 * a;
			d2 = 2 * (a + b);
			while (x < x1) {
				if (d < 0) {
					d = d + d2;
					x++;
					y++;
				} else {
					d = d + d1;
					x++;
				}
				drawPixel(x, y, Color.green);
				try {
					Thread.sleep(wait_time);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else {
			d = a + 2 * b;
			d1 = 2 * b;
			d2 = 2 * (a + b);
			while (y < y1) {
				if (d < 0) {
					d = d + d1;
					y++;
				} else {
					d = d + d2;
					x++;
					y++;
				}
				drawPixel(x, y, Color.green);
				try {
					Thread.sleep(wait_time);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// Bresenham算法
	public void BresenhamLine(int x0, int y0, int x1, int y1) {
		DrawLine(x0, y0, x1, y1);
		int x, y;
		float dx, dy;
		float k, e;
		dx = x1 - x0;
		dy = y1 - y0;
		e = -0.5f;
		x = x0;
		y = y0;
		if (dx > dy) {
			k = dy / dx;
			while (x <= x1) {
				drawPixel(x, y, Color.GREEN);
				x++;
				e = e + k;
				if (e >= 0) {
					y++;
					e = e - 1;
				}
				try {
					Thread.sleep(wait_time);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		} else {
			k = dx / dy;
			while (y <= y1) {
				drawPixel(x, y, Color.GREEN);
				y++;
				e = e + k;
				if (e > 0) {
					x++;
					e = e - 1;
				}
				try {
					Thread.sleep(wait_time);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	// 数值微分法
	public void DDALine(int x0, int y0, int x1, int y1) {
		DrawLine(x0, y0, x1, y1);
		float x, y, dy, dx, length;
		Point t;
		dy = y1 - y0;
		dx = x1 - x0;
		if (Math.abs(dx) > Math.abs(dy)) {
			length = Math.abs(dx);
		} else {
			length = Math.abs(dy);
		}
		dx = dx / length;
		dy = dy / length;
		x = (float) (x0 + 0.5);
		y = (float) (y0 + 0.5);
		int i = 0;
		while (i <= (int) length) {
			t = new Point((int) x, (int) y);
			drawPixel(t.x, t.y, Color.green);
			x = x + dx;
			y = y + dy;
			try {
				Thread.sleep(wait_time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
		}

	}

	// 画像素点
	public void drawPixel(int x, int y, Color color) {
		Graphics g = panel.getGraphics();
		Point p = panel.transform(x, y);
		g.setColor(color);
		g.fillOval(p.x - 5, p.y - 5, 10, 10);
	}

	// 画直线
	public void DrawLine(int x0, int y0, int x1, int y1) {
		Graphics g = panel.getGraphics();
		Point p1, p2;
		p1 = panel.transform(x0, y0);
		p2 = panel.transform(x1, y1);
		g.setColor(Color.BLACK);
		g.drawLine(p1.x, p1.y, p2.x, p2.y);
	}
}
