package iproblems;
import java.util.*;
public class Triangle_prob {
	
	//to calculate area
	public float area(float x1, float y1, float x2, float y2, float x3, float y3)
	{
	   return (float) Math.abs((x1*(y2-y3) + x2*(y3-y1)+ x3*(y1-y2))/2.0);
	}
	
	
	public int check_in_triangle(float x1, float y1, float x2, float y2, float x3, float y3,float c1,float c2)
	{
		float A1,A2,A3,A;
		A = area(x1, y1, x2, y2, x3, y3);
		A1 =area(x1, y1, x2, y2, c1, c2);
		A2= area(x1, y1, c1, c2, x3, y3);
		A3 = area(c1, c2, x2, y2, x3, y3);
		if(A==(A1+A2+A3))
		{
			return 1;
		}
		return 0;
	}
	public static void main(String[] args) {
		//for user-input
		Scanner s1= new Scanner(System.in);
		
		float x1,x2,x3,y1,y2,y3,c1,c2;
		System.out.println("enter the first coordinate");
		x1= s1.nextFloat();
		y1 = s1.nextFloat();
		System.out.println("enter the second coordinate");
		x2= s1.nextFloat();
		y2 = s1.nextFloat();
		System.out.println("enter the third coordinate");
		x3= s1.nextFloat();
		y3 = s1.nextFloat();
		
		System.out.println("enter the test pofloat");
		c1= s1.nextFloat();
		c2 = s1.nextFloat();
		s1.close();
		
		Triangle_prob t1= new Triangle_prob();
		int result=t1.check_in_triangle(x1, y1, x2, y2, x3, y3, c1, c2);
		if(result==1)
		{
			System.out.println("Point lies inside triangle");
		}
		else 
			System.out.println("Point lies outside triangle");
			
		
		
	}

}
