package ploygon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;

public class PolygonScanLine extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int width = 800;
	public static int height = 600;
	public static int wait_time = 300;
	public CoordinatePanel panel;

	public PolygonScanLine() {
		this.setSize(width, height);
		this.setVisible(true);
		panel = new CoordinatePanel();
		this.setLocationRelativeTo(null);
		this.add(panel);
	}

	public void Polyfill(int []x,int []y,Color color) {
		DrawPolygon(x, y);
		int y0;
		double a, b;
		int nScan = 1; //扫描线数
		int nVertex = x.length - 1;
		/* 创建新边表NET */
//		System.out.println("NET start");
		int ymin = Min(y);
		int ymax = Max(y);
//		System.out.println("min" + ymin);
//		System.out.println("max" + ymax);
		HashMap<Integer, List<Node>> NET = new HashMap<Integer, List<Node>>();
		List<Node> list;
		Node node;
		for (int i = ymin; i < ymax; i++) {// 扫描线
			int flag = 0;
			list = new ArrayList<Node>();
			for (int j = 0; j < nVertex; j++) {// 遍历顶点
				y0 = Math.min(y[j], y[j + 1]);
				if (y0 == i) {
					node = new Node();
					a = y[j] - y[j + 1];
					b = x[j + 1] - x[j];
					if (a == 0)
						continue;
					else {
						flag++;
						if (y0 == y[j])
							node.setX(x[j]);
						else
							node.setX(x[j + 1]);
						node.setDeltaX(-b / a);
						node.setYmax(Math.max(y[j], y[j + 1]));
						list.add(node);
					}
				}
			}
			if (flag != 0) {
				Collections.sort(list);
				NET.put(nScan, list);
			}
			nScan++;
		}
//		System.out.println(NET);

		// 创建活性边表AET
		List<Node> AET = new ArrayList<Node>();
		List<Node> temp = new ArrayList<Node>();
		nScan = 1;
//		System.out.println("AET start");
		for (int i = ymin; i <= ymax; i++) {
//			System.out.println(nScan);
			List<Node> tAET = new ArrayList<Node>(AET);
			Iterator<Node> it = tAET.iterator();
			while (it.hasNext()) {
				Node n = it.next();
				if (n.getYmax() == i && n.getYmax() != ymax) { //去除Ymax=i的边
					AET.remove(n);
//					System.out.println(AET);
				}
			}

			if (NET.get(nScan) != null) {  //把NET[i]中边结点插入AET
				if (AET.isEmpty() == true) {
					AET = NET.get(nScan);
//					System.out.println(AET);
				} else {
					if (NET.get(nScan) != null) {
						temp = NET.get(nScan);
						for (int j = 0; j < temp.size(); j++) {
							AET.add(temp.get(j));
						}
						Collections.sort(AET);    //按x坐标递增排序
//						System.out.println(AET);
					}
				}
			}

			// 交点配对,改写像素颜色值
			int x1, x2;
			for (int j = 0; j < AET.size(); j += 2) {
//				System.out.println(AET);
				x1 = (int) (AET.get(j).getX()+0.5);
				x2 = (int)( AET.get(j + 1).getX()+0.5);
				for (int k = x1; k <= x2; k++) {
					drawPixel(k, i, color);
					try {
						Thread.sleep(wait_time);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			// 更新活性边表
			for (int j = 0; j < AET.size(); j++) {
				double t = AET.get(j).getX() + AET.get(j).getDeltaX();
				AET.get(j).setX(t);
			}
			Collections.sort(AET);
			nScan++;
		}
	}

	public int Max(int[] x) {
		int max = x[0];
		for (int i = 1; i < x.length; i++) {
			max = Math.max(max, x[i]);
		}
		return max;
	}

	public int Min(int[] x) {
		int min = x[0];
		for (int i = 1; i < x.length; i++) {
			min = Math.min(min, x[i]);
		}
		return min;
	}

	// 画像素点
	public void drawPixel(int x, int y, Color color) {
		Graphics g = panel.getGraphics();
		Point t = panel.transform(x, y);
		g.setColor(color);
		g.fillOval(t.x - 5, t.y - 5, 10, 10);
	}

	// 画多边形
	public void DrawPolygon(int[] x, int[] y) {
		Point p;
		int[] x1 = new int[x.length];
		int[] y1 = new int[y.length];

		for (int i = 0; i < y.length; i++) {
			p = panel.transform(x[i], y[i]);
			x1[i] = p.x;
			y1[i] = p.y;
		}
		Graphics g = this.panel.getGraphics();
		g.drawPolygon(x1, y1, x1.length);
	}
}
