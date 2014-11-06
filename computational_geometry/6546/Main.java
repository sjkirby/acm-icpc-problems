import java.util.ArrayList;
import java.util.Scanner;

class Point{
	double x;
	double y;
	Point(double _x, double _y){
		x = _x;
		y = _y;
	}
	
	double dist(double x2, double y2){
		double dx = x2 - x;
		double dy = y2 - y;
		return Math.sqrt(dx*dx + dy*dy);
	}
}

class Line{
	double x1,y1,x2,y2;
	Double dot = null;
	Double dr = null;
	double dx,dy;
	Line(double _x1, double _y1, double _x2, double _y2){
		x1 = _x1;
		y1 = _y1;
		x2 = _x2;
		y2 = _y2;
		init();
	}
	
	void init(){
		dot();
		dr();
	}
	
	void dot(){
		if(dot == null){
			dot = x1*y2-x2*y1;
		}
	}
	
	//distance between points defining line
	void dr(){
		if(dr == null){
			dx = x2 - x1;
			dy = y2 - y1;
			dr = Math.sqrt(dx*dx + dy*dy); 
		}
	}
	
	Point intersection(Line l2){
		
		
		double xD1 = dx;
		double yD1 = dy;
		double xD2 = l2.dx;
		double yD2 = l2.dy;
		
		double xD3 = x1 - l2.x1;
		double yD3 = y1 - l2.y1;
		
		//length of 2 lines - if they were segments
		double len1,len2;
		len1 = dr;
		len2 = l2.dr;
		
		double linesdot = xD1*xD2 + yD1*yD2;
		double degree = dot / (len1*len2);
		
		//lines are parallel
		if(Math.abs(degree) == 1) return null;
		
		double div = yD2*xD1 - xD2*yD1;
		double ua = (xD2*yD3 - yD2*xD3)/div;
		double ub = (xD1*yD3 - yD1*xD3)/div;
		Point out = new Point(x1+ua*xD1,y1+ua*yD1);	
		
		return out;
	}
	
}

class Circle{
	static final double ERROR = 0.000001;
	
	double x,y,r;
	Circle(double _x, double _y, double _r){
		x = _x;
		y = _y;
		r = _r;
	}
	
	//MORE EFFICIENT WITH CIRCLE AT 0,0
	double sgn(double dy){
		if(dy<0) return -1;
		return 1;
	}
	void intersections(Line l){
		if(x != 0||y!=0){
			l = new Line(l.x1-x,l.y1-y,l.x2-x,l.y2-y);
		}
		double xPlusMinus = sgn(l.dy)*l.dx*Math.sqrt(r*r*l.dr*l.dr - l.dot*l.dot);
		double ix1 = l.dot * l.dy + xPlusMinus;
		double ix2 = l.dot * l.dy - xPlusMinus;
		ix1 /= l.dr*l.dr;
		ix2 /= l.dr*l.dr;
		
		double yPlusMinus = Math.abs(l.dy)*Math.sqrt(r*r*l.dr*l.dr - l.dot*l.dot);
		double iy1 = l.dot * -1 * l.dx + yPlusMinus;
		double iy2 = l.dot * -1 * l.dx - yPlusMinus;
		iy1 /= l.dr*l.dr;
		iy2 /= l.dr*l.dr;
		
		if(x != 0||y!=0){
			ix1 += x;
			ix2 += x;
			iy1 += y;
			iy2 += y;
		}
		
		
		System.out.println("("+ix1+","+iy1+") ("+ix2+","+iy2+")");
		
	}
	
	//returns 0 for no intersection, 1 for tangent, and 2 for intersection 
	int intersects(Line l){
		if(x != 0||y!=0){
			l = new Line(l.x1-x,l.y1-y,l.x2-x,l.y2-y);
		}
		double temp = r*r*l.dr*l.dr-l.dot*l.dot;
		if(temp != 0){
			if(temp < 0){
				return 0;
			}
			return 2;
		}
		return 1;
	}
	
	//true if p is within circle
	boolean contains(Point p){
		return p.dist(x,y) < r;
	}
	
	//true if p is on circle within ERROR value
	boolean on(Point p){
		return Math.abs(p.dist(x,y) - r) < ERROR;
	}
	
	
}

public class Main {

	static void p(String str) {
		System.out.println(str);
	}

	static void pr(String str) {
		System.out.print(str);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
//		Line l1 = new Line(0,0,0,1);
//		Line l21 = new Line(0,2,1,1.5);
//		Point p = new Point(0,0.999999);
//		Circle c = new Circle(0,0,1);
//		p(""+c.intersects(l));
//		Point intersect = l1.intersection(l21);
//		p("("+intersect.x+","+intersect.y+")");
//		p("("+c.within(p)+")");
		
		int r = in.nextInt();
		int x = in.nextInt();
		int y = in.nextInt();
		int n = in.nextInt();
		while(r!=0||x!=0||y!=0||n!=0){
			Circle circ = new Circle(0,0,r);
			ArrayList<Line> lines = new ArrayList<Line>();
			while(n-- != 0){
				int x1 = in.nextInt(),y1 = in.nextInt();
				int x2 = in.nextInt(),y2 = in.nextInt();
				x1 -= x;
				x2 -= x;
				y1 -= y;
				y2 -= y;
				
				Line temp = new Line(x1,y1,x2,y2);
				lines.add(temp);
			}
			int out = 1;
			for(int i = 0; i < lines.size();i++){
				Line l = lines.get(i);
				if(circ.intersects(l) == 2) out++;
				for(int j = i + 1; j < lines.size();j++){
					Line l2 = lines.get(j);
					Point intersection = l.intersection(l2);
					if(intersection != null && circ.contains(intersection)){
						out++;
					}
				}
			}
			p(""+out);
			
			r = in.nextInt();
			x = in.nextInt();
			y = in.nextInt();
			n = in.nextInt();
		}
		
		
		in.close();	
	}
}
/*
01000101101000
1001000101001000
0
*/