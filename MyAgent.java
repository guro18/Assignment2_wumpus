package wumpusworld;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains starting code for creating your own Wumpus World agent.
 * Currently the agent only make a random decision each turn.
 * 
 * @author Johan Hagelbäck
 */
public class MyAgent implements Agent
{
    private World w;
    int rnd;
    
    /**
     * Creates a new instance of your solver agent.
     * 
     * @param world Current world state 
     */
    public MyAgent(World world)
    {
        w = world; 
        
    }
    
   
            
    /**
     * Asks your solver agent to execute an action.
     */

    public void doAction()
    {
        //Location of the player
        int cX = w.getPlayerX();
        int cY = w.getPlayerY();
        //boolean knownSquare = w.isUnknown(cX + 1, cY);
        //boolean squareVisited = w.isVisited(cX, cY);
        //boolean rightSquareVisited = w.isVisited(cX + 1, cY);
        //System.out.println(cX);
        //System.out.println(cY);
        //System.out.println(rightSquareVisited);
        //System.out.println(knownSquare);	
        List myList = new ArrayList();
        
        
        
        
        
        //Basic action:
        //Grab Gold if we can.
        if (w.hasGlitter(cX, cY))
        {
            w.doAction(World.A_GRAB);
            return;
        }
        
        //Basic action:
        //We are in a pit. Climb up.
        if (w.isInPit())
        {
            w.doAction(World.A_CLIMB);
            return;
        }
        
        //Test the environment
        if (w.hasBreeze(cX, cY))
        {
            System.out.println("I am in a Breeze");
        }
        if (w.hasStench(cX, cY))
        {
            System.out.println("I am in a Stench");
        }
        if (w.hasPit(cX, cY))
        {
            System.out.println("I am in a Pit");
        }
        if (w.getDirection() == World.DIR_RIGHT)
        {
            System.out.println("I am facing Right");
        }
        if (w.getDirection() == World.DIR_LEFT)
        {
            System.out.println("I am facing Left");
        }
        if (w.getDirection() == World.DIR_UP)
        {
            System.out.println("I am facing Up");
        }
        if (w.getDirection() == World.DIR_DOWN)
        {
            System.out.println("I am facing Down");
        }
        
        //decide next move
        //rnd = decideRandomMove();
        /*System.out.println(rnd);
        if (rnd==0)
        {
            w.doAction(World.A_TURN_LEFT);
            w.doAction(World.A_MOVE);
        }
        
        if (rnd==1)
        {
            w.doAction(World.A_MOVE);
        }
                
        if (rnd==2)
        {
            w.doAction(World.A_TURN_LEFT);
            w.doAction(World.A_TURN_LEFT);
            w.doAction(World.A_MOVE);
        }
                        
        if (rnd==3)
        {
            w.doAction(World.A_TURN_RIGHT);
            w.doAction(World.A_MOVE);
        }  */
        
        //moveLeft();
        //moveRight();
        //moveDown();
        //moveUp();
        if ((cX == 1) & (cY == 1))//square 1,1
        {	if ((w.hasStench(w.getPlayerX(), w.getPlayerY())) == true) //has stench
        	{
        		System.out.println("current square has stench");
        		if (w.hasArrow() == true)// if arrow is available
        		{
    				w.doAction(World.A_SHOOT);//shot arrow to the current direction since it costs least points
    				System.out.println("Arrow was used");
    				if ((w.hasStench(w.getPlayerX(), w.getPlayerY())) == true)//checks if wumpus is still alive
    				{
    					System.out.println("Wumpus still alive");
    					moveRight();//move right sinve this cost least points	
    				}
    				else
    				{
    					System.out.println("Wumpus was killed");
    					moveRight(); //move to the right
    				}
        		}
        		else
        		{
        			moveRight();
        		}	
        	}
        	else 
        	{
        		moveRight();
        	}
        }
        else
        {
        	if ((w.hasStench(cX, cY) == false) & (w.hasBreeze(cX, cY) == false)) // square with no sensors
        	{
        		rnd = decideRandomMove();//decide random move
        		System.out.println(rnd);
        		
        		//checks if square to the right, left, up, down is visited, and valid, and makes move
        		if ((rnd == 0) & (w.isValidPosition(cX+1, cY) == true) & (w.isVisited(cX + 1, cY) == false))
        		{
        			moveRight();
        		}
        		else if ((rnd == 1) & (w.isValidPosition(cX-1, cY) == true) & (w.isVisited(cX -1, cY) == false))
        		{
        			moveLeft();
        		}
        		else if ((rnd == 2) & (w.isValidPosition(cX, cY+1) == true) & (w.isVisited(cX, cY+ 1) == false))
        		{
        			moveUp();
        		}
        		else if ((rnd == 3) & (w.isValidPosition(cX, cY-1) == true) & (w.isVisited(cX, cY-1) == false))
        		{
        			moveDown();
        		}
        		
        		else //moves in oposite direction if the first priority is visited/invalid
        		{
        			if ((rnd == 0) & (w.isVisited(cX -1, cY) == false))
        			{
        				moveLeft();
        			}
        			else if ((rnd == 1) & (w.isVisited(cX + 1, cY) == false))
        			{
        				moveRight();
        			}
        			else if ((rnd == 2) & (w.isVisited(cX, cY-1) == false))
        			{
        				moveDown();
        			}
        			else if ((rnd == 3) & (w.isVisited(cX, cY+ 1) == false))
        			{
        				moveUp();
        			}
        		}
        	}
        	
        }
        
        
        
        
        	
        	
        

    }    
    
     /**
     * Genertes a random instruction for the Agent.
     */
    public int decideRandomMove()
    {
      return (int)(Math.random() * 4);
    }
    
    public void moveRight()//makes the agent move right to the closest square
    {	
    	if (w.getDirection() == World.DIR_DOWN)
    	{
	    	w.doAction(World.A_TURN_LEFT);
	    	w.doAction(World.A_MOVE);
    	}
    
    	else if (w.getDirection() == World.DIR_UP)
    	{
		    w.doAction(World.A_TURN_RIGHT);
			w.doAction(World.A_MOVE);
    	}
    
    	else if (w.getDirection() == World.DIR_RIGHT)
    	{
			w.doAction(World.A_MOVE);
    	}
    
    	else if (w.getDirection() == World.DIR_LEFT)
    	{
		    w.doAction(World.A_TURN_LEFT);
		    w.doAction(World.A_TURN_LEFT);
			w.doAction(World.A_MOVE);
    	}
    }
    
    
    public void moveLeft()//makes the agent move left to the closest square
    {	
    	if (w.getDirection() == World.DIR_DOWN)
    	{
	    	w.doAction(World.A_TURN_RIGHT);
	    	w.doAction(World.A_MOVE);
    	}
    
    	else if (w.getDirection() == World.DIR_UP)
    	{
		    w.doAction(World.A_TURN_LEFT);
			w.doAction(World.A_MOVE);
    	}
    
    	else if (w.getDirection() == World.DIR_LEFT)
    	{
			w.doAction(World.A_MOVE);
    	}
    
    	else if (w.getDirection() == World.DIR_RIGHT)
    	{
		    w.doAction(World.A_TURN_LEFT);
		    w.doAction(World.A_TURN_LEFT);
			w.doAction(World.A_MOVE);
    	}  		
    }
    
    
    public void moveDown()//makes the agent move downwards to the square closest 
    {	
    	if (w.getDirection() == World.DIR_RIGHT)
    	{
	    	w.doAction(World.A_TURN_RIGHT);
	    	w.doAction(World.A_MOVE);
    	}
    
    	else if (w.getDirection() == World.DIR_LEFT)
    	{
		    w.doAction(World.A_TURN_LEFT);
			w.doAction(World.A_MOVE);
    	}
    
    	else if (w.getDirection() == World.DIR_DOWN)
    	{
			w.doAction(World.A_MOVE);
    	}
    
    	else if (w.getDirection() == World.DIR_UP)
    	{
		    w.doAction(World.A_TURN_LEFT);
		    w.doAction(World.A_TURN_LEFT);
			w.doAction(World.A_MOVE);
    	}  		
    }
    
    
    public void moveUp()//makes the agent move upwards to the square closest 
    {	
    	if (w.getDirection() == World.DIR_LEFT)
    	{
	    	w.doAction(World.A_TURN_RIGHT);
	    	w.doAction(World.A_MOVE);
    	}
    
    	else if (w.getDirection() == World.DIR_RIGHT)
    	{
		    w.doAction(World.A_TURN_LEFT);
			w.doAction(World.A_MOVE);
    	}
    
    	else if (w.getDirection() == World.DIR_UP)
    	{
			w.doAction(World.A_MOVE);
    	}
    
    	else if (w.getDirection() == World.DIR_DOWN)
    	{
		    w.doAction(World.A_TURN_LEFT);
		    w.doAction(World.A_TURN_LEFT);
			w.doAction(World.A_MOVE);
    	}  		
    }
    
}

