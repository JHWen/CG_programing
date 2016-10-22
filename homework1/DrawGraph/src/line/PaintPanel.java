package line;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

import javax.swing.JPanel;

public class PaintPanel extends JPanel {
	public Point p;
	public static int space=50;
	public static int triangle=10;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public PaintPanel() { 

	}
	@Override
	public void paint(Graphics g) { //绘制坐标系
		super.paint(g);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 18));
		p=new Point();
		p.x=this.getWidth()/2;
		p.y=this.getHeight()/2;
		g.drawLine(space, p.y, 2*p.x-space, p.y);  //x轴
		g.drawLine(p.x, space, p.x, 2*p.y-space);  //y轴
		//画箭头
		//x
		g.drawLine(2*p.x-space, p.y, 2*p.x-space-triangle, p.y-triangle);
		g.drawLine(2*p.x-space, p.y, 2*p.x-space-triangle, p.y+triangle);
		g.drawString("X",2*p.x-space-20 ,p.y-20 );
		//y
		g.drawLine(p.x, space, p.x+triangle, space+triangle);
		g.drawLine(p.x, space, p.x-triangle, space+triangle);
		g.drawString("Y",p.x+20,space);
        //画网格		
		Graphics2D gr = (Graphics2D)g;
		Stroke dash = new BasicStroke(0.5f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND,1.5f,new float[]{5,10},0f);
		gr.setStroke(dash);
		gr.setColor(Color.black);
	    for(int i=p.x+space;i<2*p.x-space;i+=space){
	    	gr.drawLine(i,space, i,2*p.y-space);
	    }
	    for(int i=p.x-space;i>space;i-=space){
	    	gr.drawLine(i,space, i,2*p.y-space);
	    }
		for(int j=p.y+space;j<2*p.y-space;j+=space){
			gr.drawLine(space, j, 2*p.x-space, j);
		}
		for(int j=p.y-space;j>space;j-=space){
			gr.drawLine(space, j, 2*p.x-space, j);
		}
	}
	public Point transform(int x,int y){
		x=x*space;
		y=y*space;
		Point t=new Point(x,y);
		t.x=t.x+p.x;
		t.y=p.y-t.y;
		return t;
	}

}
