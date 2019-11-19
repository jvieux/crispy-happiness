
import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
//import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
//import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


//put driver in here and that'll start the game.
public class MGame {
	Line barrier = new Line(299, 0, 299, 600);
	Rectangle gate = new Rectangle(296, 10, 8, 50);
	Rectangle btm = new Rectangle(0, 600, 600, 50);
	Random r = new Random();
	boolean openGate = false;
	double gateY = 0;
	Label go = new Label("Game Over!");
	int gamePlay = 6;
	boolean done = false;
	
	
	ArrayList<MBall> balls = new ArrayList<MBall>();
	//ArrayList<MBall> ballsRight = new ArrayList<MBall>();
	//use count it'll be easier
	
	
	public void setUp(Group root, Scene scene) {
		barrier.setStrokeWidth(2);
		
		btm.setFill(Color.DARKSLATEBLUE);
		
		root.getChildren().add(barrier);
		root.getChildren().add(gate);
		root.getChildren().add(btm);
		
	 	
		//leftSide Balls
		for(int i = 0; i<3; i++) {
			int x = r.nextInt(260)+10;
			int y = r.nextInt(580)+10;
			
			MBall ball = new MBall(x, y, false, true);
			balls.add(ball);
		}
		//root.getChildren().addAll(balls);
		
		//rightSide Balls
		for(int i = 0; i<3; i++) {
			int x = r.nextInt(260)+310;
			int y = r.nextInt(580)+10;
					
			MBall ball = new MBall(x,y, true, false);
			//Circle thisBall = ball.makeBall(x, y, false, true);
			balls.add(ball);
		}
		root.getChildren().addAll(balls);
		
	 	scene.setOnMouseMoved((MouseEvent m)->
	 	{
	 		gateY = m.getY();
	 		if(gateY<=550) {
	 			gate.setY(gateY);
	 		}
	 		int rCnt = 0;
	 		for(MBall b : balls) {
	 			if(b.isRight()) {
	 				rCnt++;
	 			}
	 		}
	 		if(rCnt == 0 || rCnt == balls.size()) {
	 			gameOver(root);
	 		}
	 	});
	 	scene.setOnMousePressed((MouseEvent m)->
	 	{
	 		gate.setFill(Color.WHEAT);
	 		openGate = true;
	 		int rCnt = 0;
	 		for(MBall b : balls) {
	 			b.openGate(gateY);
				b.gateCoordinates(gateY);
				if(b.isRight()) {
		 			rCnt++;
		 		}
			}
	 		if(rCnt == 0 || rCnt == balls.size()) {
	 			gameOver(root);
	 		}
	 	});
	 	scene.setOnMouseReleased((MouseEvent m)->
	 	{
	 		gate.setFill(Color.BLACK);
	 		openGate = false;
	 		int rCnt = 0;
	 		for(MBall b : balls) {
				b.closeGate();
				b.gateCoordinates(gateY);
				if(b.isRight()) {
		 			rCnt++;
		 		}
			}
	 		if(rCnt == 0 || rCnt == balls.size()) {
	 			gameOver(root);
	 		}
	 	});
	 	scene.setOnMouseDragged((MouseEvent m)->
	 	{
	 		openGate = true;
	 		gateY = m.getY();
	 		if(gateY<=560) {
	 			gate.setY(gateY);
	 		}
	 		
	 		for(MBall b : balls) {
				//b.closeGate();
				b.gateCoordinates(gateY);
			}
	 	});
	 	
	 	addButton(root, scene);
	 	
	 	Driver d = new Driver();
	 	d.start();
		
	}
	
	public void addButton(Group root, Scene scene) {
		Button addB = new Button("Add Ball");
		addB.setOnAction((ActionEvent e)->
		{
			if(root.getChildren().contains(go)) {
				go.setText("Restart Game to play new ball");
			}
			double side = Math.random();
			if(side<=0.5) {
				int x = r.nextInt(260)+10;
				int y = r.nextInt(580)+10;
				
				MBall ball = new MBall(x, y, false, true);
				balls.add(ball);
				root.getChildren().add(ball);
			}
			else {
				int x = r.nextInt(260)+310;
				int y = r.nextInt(580)+10;
						
				MBall ball = new MBall(x,y, true, false);
				//Circle thisBall = ball.makeBall(x, y, false, true);
				balls.add(ball);
				root.getChildren().add(ball);
			}
			
		});
		
		addB.setLayoutY(610);
		addB.setLayoutX(250);
		addB.setPrefSize(100, 30);
		
		root.getChildren().add(addB);
	}
	
	public void gameOver(Group root) {
		go.setPrefSize(250, 150);
		go.setLayoutX(175);
		go.setLayoutY(225);
		go.setTextFill(Color.WHITE);
		go.setWrapText(true);
		go.setBackground(new Background(new BackgroundFill(Color.FORESTGREEN, null, null)));
		go.setAlignment(Pos.CENTER);
		go.setFont(Font.font(null, FontWeight.BOLD, 25));
		if(!done) {
			root.getChildren().add(go);
			done = true;
		}
	}
	
    public class Driver extends AnimationTimer{
   	 long lasttime;
   	 boolean firsttime = true;
   	 
    	//@Override
    	public void handle( long now )
    	{
   		//System.out.println("timer="+now);
   		if ( firsttime ) { lasttime = now; firsttime = false; }
   		else{
   		    double deltat = (now-lasttime ) * 1.0e-9;
   		    lasttime = now;
   		    
   				for(MBall b : balls) {
   					b.move(deltat);
   				}
   			}
    	}
    }
}