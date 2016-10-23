package clipAlgorithm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;

import coordinateSystem.CoordinatePanel;

public class LiangBarskeyLineCliping extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int width = 800;
	public static int height = 600;
	private CoordinatePanel panel;
	public int xmin; // 裁剪边框边界值
	public int xmax;
	public int ymin;
	public int ymax;
	public float u1; //参数方程  p=p1+u(p2-p1) (0<=u<=1)
	public float u2;
	public LiangBarskeyLineCliping() {
		panel = new CoordinatePanel();
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.add(panel);
	}
	/**
	 * 梁友栋-Barskey裁剪算法
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param x
	 * @param y
	 */
	public void LB_LineCliping(float x1, float y1, float x2, float y2, int x[],
			int[] y){
		float dx,dy;
		DrawLine(x1, y1, x2, y2,Color.RED);
		DrawPolygon(x, y, Color.GREEN);
		xmin = getMin(x); // 计算裁剪边框边界
		xmax = getMax(x);
		ymin = getMin(y);
		ymax = getMax(y);
		u1=0; //初始化
		u2=1;
		dx=x2-x1;
		dy=y2-y1;
		if(ClipT(-dx,x1-xmin))
			if(ClipT(dx, xmax-x1))
				if(ClipT(-dy, y1-ymin))
					if(ClipT(dy, ymax-y1))
						DrawLine(x1+u1*dx, y1+u1*dy, x1+u2*dx, y1+u2*dy, Color.BLUE);
		
	}
	public Boolean ClipT(float p,float q){
		float r;
		if(p<0){ //线段从外到内
			r=q/p;
			if(r>u1){
				u1=r;
			}
			if(u1>u2){   //u1>u2不在裁剪窗口内
				return false;
			}
		}else if(p>0){  //线段从内到外
			r=q/p;
			if(r<u2){
				u2=r;
			}
			if(u1>u2){ //u1>u2不在裁剪窗口内
				return false;
			}
		}else{
			return q>=0;
		}
		
		return true;
	}
	/**
	 * 找最大边界
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
	 * 找最小边界
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
	 * 画多边形
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
	 * 画直线
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
