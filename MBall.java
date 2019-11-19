
//import javafx.scene.Node;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MBall extends Circle{
	static double spaceH = 600;
	static double spaceW = 600;
	private boolean lSide ;
	private boolean rSide;
	private boolean justChanged;
	double x = 0; // position of the ball
	double y = 0;
	double vx = 100; // pix per second 
	double vy = -100;
	int hw = 10;
	double speedFtr = Math.random() + .7;
	boolean gateOpened = false;
	double gTop = 0;
	int lCount = 5;
	//Random r = new Random();
	//private Circle ball;
	
	
	public MBall(int xLoc, int yLoc, boolean right, boolean left)
    {
    	//super( 10, 10 );
		super(xLoc, yLoc, 5, Color.TOMATO);
    	setCenterX(xLoc);
    	setCenterY(yLoc);
    	x = xLoc;
		y = yLoc;
		rSide = right;
		lSide = left;
		justChanged = false;
    }
	
	
	public boolean isRight() {
		if(rSide) {
			lSide = false;
			return rSide;
		}
		rSide = false;
		return rSide;
	}
	
	
	public void changeSide() {
		//add an if statement to make this start that says it can only change if it hasn't just changed
		//this is determine by whether or not i just bounced a wall. so when i bounce on a wall, justchanged goes back to no
		if(!justChanged){
			if(lSide) {
				lSide = false;
				rSide = true;
				lCount--;
				//System.out.println(lCount);
			}
			else if(rSide) {
				lSide = true;
				rSide = false;
				lCount++;
				//System.out.println(lCount);
			}
			justChanged = true;
		}
	}
	
	public void move(double deltat)
    {
    	
		if(lSide) {
    		bounceLeft();
    	}
    	else if(rSide) {
    		bounceRight();
    	}
		if(gateOpened && !justChanged) {
			if(x>=293 && x<= 313) {
				if(y>=gTop+6 && y<=gTop+44) {
					changeSide();
				}
			}
		}
		x = getCenterX();
    	x += speedFtr * vx * deltat;
    	setCenterX(x);
    	y = getCenterY();
    	y += speedFtr *vy * deltat;
    	setCenterY(y);
    	
    }
    
    public void bounceLeft()
    {
    	if (y>595){ 
    		vy = - Math.abs(vy); 
    	}
    	
    	if(y<5) {
    		vy = Math.abs(vy);
    	}
    	
    	if ( x<5 ) { 
    		vx = Math.abs(vx); 
    		justChanged = false;
    	}
    	if ( x>294 ){ 
    		vx = - Math.abs(vx); 
   		}
    	
    	//justChanged = false;
    }
    
    public void bounceRight()
    {
    	if (y>595)
    	{ vy = - Math.abs(vy); }
    	
    	if(y<5) {
    		vy = Math.abs(vy);
    	}
    	
    	if ( x<312 ) { vx = Math.abs(vx); }
    	
    	if ( x>595 ) { 
    		vx = - Math.abs(vx); 
    		justChanged = false;
    	}
    }
    
    public void openGate(double top) {
    	gateOpened = true;
    	gTop = top;
    }
    
    public void closeGate() {
    	gateOpened = false;
    }
    
    public void gateCoordinates(double top) {
    	gTop = top;
    }
    
    public int getLCt() {
    	return lCount;
    }
	
}
