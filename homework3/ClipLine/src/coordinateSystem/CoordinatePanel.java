package coordinateSystem;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

import javax.swing.JPanel;

public class CoordinatePanel extends JPanel {
	/**
	 * 
	 */
	public Point p;
	public static int space=50;
	public static int triangle=10;
	private static final long serialVersionUID = 1L;
	public CoordinatePanel() { 

	}
	@Override
	public void paint(Graphics g) { //生成坐标系
		super.paint(g);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 18));
		p=new Point();
		p.x=space;
		p.y=this.getHeight()-space;
		g.drawString("0", p.x-10, p.y+10);
		g.drawLine(p.x, p.y, this.getWidth()-space, p.y);  //x轴
		g.drawLine(p.x, p.y, p.x,space);  //y轴
		//画箭头
		//x
		g.drawLine(this.getWidth()-space, p.y, this.getWidth()-space-triangle, p.y-triangle);
		g.drawLine(this.getWidth()-space, p.y, this.getWidth()-space-triangle, p.y+triangle);
		g.drawString("X",this.getWidth()-space ,p.y-20 );
		//y
		g.drawLine(p.x, space, p.x+triangle, space+triangle);
		g.drawLine(p.x, space, p.x-triangle, space+triangle);
		g.drawString("Y",p.x+20,space);
        //画网格		
		Graphics2D gr = (Graphics2D)g;
		Stroke dash = new BasicStroke(0.1f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND,5f,new float[]{5,10},2f);
		gr.setStroke(dash);
		gr.setColor(Color.black);
		int index=1;
		g.setFont(new Font("Arial", Font.PLAIN, 12));
	    for(int i=p.x+space;i<this.getWidth()-space;i+=space){
	    	gr.drawLine(i,p.y, i,space);
	    	g.drawString(String.valueOf(index), i, p.y+20);
	    	index++;
	    } 
	    index=1;
		for(int j=p.y-space;j>space;j-=space){
			gr.drawLine(p.x, j, this.getWidth()-space, j);
			g.drawString(String.valueOf(index), p.x-20, j);
			index++;
		}
	}
	public Point transform(int x,int y){ //逻辑坐标与设备坐标转换
		x=x*space;
		y=y*space;
		Point t=new Point(x,y);
		t.x=t.x+p.x;
		t.y=p.y-t.y;
		return t;
	}
	public Point transform(float x,float y){
		Point t=new Point();
		int sum_x=(int) (x*space);
		int sum_y=(int) (y*space);
		t.x=(int) (sum_x+p.x);
		t.y=(int) (p.y-sum_y);
		return t;
	}
	

}
