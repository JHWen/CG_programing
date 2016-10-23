package coordinateSystem;

public class FloatPoint {
	public float x;
	public float y;
	public boolean VisiableFlag; //是否显然可见标志
	public FloatPoint() {
		
	}
	public FloatPoint(float x,float y){
		this.x=x;
		this.y=y;
	}
	public boolean isVisiableFlag() {
		return VisiableFlag;
	}
	public void setVisiableFlag(boolean visiableFlag) {
		VisiableFlag = visiableFlag;
	}
	
	@Override
	public String toString() {
		return "x:"+x+" "+"y:"+y;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	
}
