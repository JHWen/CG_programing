package ploygon;

public class Node implements Comparable<Node> {
	double x;
	double deltaX;
	double ymax;
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getDeltaX() {
		return deltaX;
	}
	public void setDeltaX(double deltaX) {
		this.deltaX = deltaX;
	}
	public double getYmax() {
		return ymax;
	}
	public void setYmax(double ymax) {
		this.ymax = ymax;
	}
	@Override
	public String toString() {
		return "X:"+getX()+" deltaX:"+getDeltaX()+" Ymax:"+getYmax();
	}
	@Override
	public int compareTo(Node o) {
		int i=(int) (this.x-o.getX());
		return i;
	}
}
