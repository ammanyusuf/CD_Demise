import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.Point;
import java.lang.Math;

public class Projectile extends Obstacle{
    
    //Instance variables
    private boolean isDeadlyToAvatar = false;
    private boolean isDeadlyToEnemy = false;
    private boolean isShot = false;
    
    private final int mapHeight = 1000;
    private final int mapWidth = 1000;
    
    //Constructors
    public Projectile(){}
    
    public Projectile(String name, boolean deadlyToAvatar, boolean deadlyToEnemy){
        super.setName(name);
        this.isDeadlyToAvatar = deadlyToAvatar;
        this.isDeadlyToEnemy = deadlyToEnemy;
        this.isShot = false;
    }
    
    public Projectile(Projectile projectileToCopy){
        super(projectileToCopy);
        this.isDeadlyToAvatar = projectileToCopy.getDeadlyToAvatar();
        this.isDeadlyToEnemy = projectileToCopy.getDeadlyToEnenmy();
        this.isShot = projectileToCopy.getIsShot();
    }
    
    //Getter methods
    /**
    This method gets the isDeadlyToAvatar instance variable
    @return boolean
    */
    public boolean getDeadlyToAvatar(){
        return this.isDeadlyToAvatar;
    }
    
    /**
    This method gets the isDeadlyToEnemy instance variable
    @return boolean
    */
    public boolean getDeadlyToEnenmy(){
        return this.isDeadlyToEnemy;
    }
    
    /**
    This method returns the isShot instance variable
    */
    public boolean getIsShot(){
        return this.isShot;
    }
    
    //Setter methods
    /**
    This sets wether or not the projectile is deadly to the avatar
    @param deadlyToAvatar : it is either deadly to the avatar (true) or not deadly (false)
    */
    public void setIsDeadlyToAvatar(boolean deadlyToAvatar){
        this.isDeadlyToAvatar = deadlyToAvatar;
    }
    
    /**
    This sets wether or not the porjectile is deadly to the enemy
    @param deadlyToEnemy : it is either deadly to the enemy (true) or not deadly (false)
    */
    public void setIsDeadlyToEnemy(boolean deadlyToEnemy){
        this.isDeadlyToEnemy = deadlyToEnemy;
    }
    
    /**
    This sets wether or not the projectile is shot 
    @param shot : it is either shot (true) or not shot (flase)
    */
    public void setIsShot(boolean shot){
        this.isShot = shot;
    }
    
    /**
    This method is the move method for the projectile.  It takes in a string input for the direction that the avatar/enemy is facing
    @param direction : this is the direction that we wish to shoot the projectile
    */
    /**
	Move the object in a defined direction
	
	@param direction is the direction to move the object in (up, down, left, right, otherwise the object won't move)
	*/
	public void move(String direction)
	{
		direction = direction.toLowerCase();
		System.out.println(direction);
		int xCoord = (int)(this.getLocation().getX());
		int yCoord = (int)(this.getLocation().getY());
		if(direction.equals("up")){
			if (this.getLocation().getY() > 0){
                //if the obstacle is within the edge, then move up
				super.setLocation(xCoord, yCoord - 1);	
			}else if(this.getLocation().getY() == 0 ){
                //If the obstacle is at the edge, have the obstacle bounce away from it
                super.setLocation(xCoord, yCoord + 1);
            }
		} else if(direction.equals("down")){
			if (this.getLocation().getY() < mapHeight){
                //if the obstcle is within the edge, then move down
				super.setLocation(xCoord, yCoord + 1);
			}else if(this.getLocation().getY() == mapHeight){
                //if the obstacle is at the edge, then move up
                super.setLocation(xCoord, yCoord - 1);
            }
		} else if(direction.equals("left")){
			if (this.getLocation().getX() > 0){
                //if the obstacle is within the edge, then move left
				super.setLocation(xCoord - 1, yCoord);
			}else if(this.getLocation().getX() == 0){
                //if the obstacle is at the edge, then move right
                super.setLocation(xCoord + 1, yCoord);
            }
		}else if(direction.equals("right")){
			if (this.getLocation().getX() < mapWidth){
				//if the obsatcle is within the egde, then move right
                super.setLocation(xCoord + 1, yCoord);
			}else if(this.getLocation().getX() == mapWidth){
                //if the obstacle is at the edge, then move left
                super.setLocation(xCoord - 1, yCoord);
            }
		}
	}
    
    /**
    This is the toString method for the projectile clasee, it displays the name, position, and wether
    it is deadly to the avatar or deadly to the enemy
    */
    public String toString(){
        if (this.isShot == true){
            if(this.isDeadlyToAvatar == true){
                return super.toString() + " Is deadly to Avatar" + " and is Shot"; 
            }else if (this.isDeadlyToEnemy == true){
                return super.toString() + " Is deadly to Enemy" + " and is Shot";
            }else {
                return super.toString() + " is Shot";
            }
        }else if (this.isShot == false){
            if(this.isDeadlyToAvatar == true){
                return super.toString() + " Is deadly to Avatar"; 
            }else if (this.isDeadlyToEnemy == true){
                return super.toString() + " Is deadly to Enemy";
            }else {
                return super.toString();
            }
        }else {
            return super.toString();
        }
    }
    
    public static void main(String[] args){
        Projectile p = new Projectile();
        Enemy e = new Enemy();
        Avatar a = new Avatar();
        
        a.setName("Avatar");
        
        e.setName("Enemy");
        
        e.setLocation(0,0);
        
        p.setName("Projectile1");
        
        p.setIsDeadlyToEnemy(false);
        p.setIsDeadlyToAvatar(true);
        
        System.out.println(p);
        System.out.println(p.overlapsWith(a));
        System.out.println(p.overlapsWithObstacle(e));
        
        a.setHealth(3);
        System.out.println(a);
        
        if (p.overlapsWith(a) && p.getDeadlyToAvatar()){
            //Avatar take damage
            a.takeDamage(1);
        }
        System.out.println(a);
        
        e.setHealth(3);
        System.out.println(e);
        if (p.overlapsWithObstacle(e) && p.getDeadlyToEnenmy()){
            //Enemy take damage
            e.takeDamage(1);
        }
        System.out.println(e);
        
        p.move("down");
        System.out.println(p);
        
        ArrayList<Obstacle> obstacleArray = new ArrayList<Obstacle>();
        obstacleArray.add(p);
        System.out.println(p);
        
    }
    
}