### 编译环境：JDK 1.8   eclipse-luna

### 实现功能：
实现了三种直线扫描算法（**数值微分法、中点画线法、Bresenham算法**）

### 说明：
-	PaintPanel  

	继承JPanel,Paint方法实现画x-y坐标系,transform方法实现逻辑坐标和设备坐标转换
-	PaintLine

  继承JFrame,实现三种直线扫描转换方法
- Main  测试类
