
import java.util.ArrayList;
import java.util.LinkedList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MaxwellJessicaVieux extends Application{
	
	MGame game = new MGame();
	/*ArrayList<MBall> ballsLeft;
	ArrayList<MBall> ballsRight;*/
	
	
	public static void main( String[] args)
		{launch(args);}
	
	
	@Override
	public void start(Stage stage)
    {    
    	Group root = new Group();
    	
    	//yo.begin(root);
       
        Scene scene = new Scene(root, 600, 650);
        
        scene.setFill(Color.WHEAT);
        
        //Driver d = new Driver();
     	//d.start();
        game.setUp(root, scene);
        //game.playGame(root, scene);

        stage.setTitle("Maxwell's Demon");
	    stage.setScene(scene);
	     
	    stage.show();
	        
   }
    
    

}
