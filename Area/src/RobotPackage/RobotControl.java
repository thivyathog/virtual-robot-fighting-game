package RobotPackage;

import java.util.ArrayList;
import java.util.List;

public class RobotControl {
    private boolean movingNorth = false;
    private boolean movingEast = false;
    private boolean movingSouth=false;
    private boolean movingWest=false;
RobotInfo rf;
private int n;
List rc=new ArrayList();
    public void setRobotInfos(RobotInfo robotInfos) {
        this.robotInfos[n] = robotInfos;
    }

    private static RobotInfo [] robotInfos=new RobotInfo[2];
    int x;

    public RobotInfo getRf() {
        return rf;
    }

    int y;

    public int getX() {
        return x;
    }

    public int getN() {
        return n;
    }
public List getLi() {
        return rc;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public RobotControl(String name, int  x, int y, double health,int n) {
        rf=new RobotInfo(name,x,y,health);
      rc.add( rf);
this.setX(x);
this.setY(y);
this.n=n;

    }

public static RobotInfo[] getAllRobots(){
return robotInfos;
}
public boolean moveNorth(){

return movingNorth;
}
    public boolean moveEast(){
return movingEast;
    }
    public boolean moveSouth(){
return movingSouth;
    }
    public boolean  moveWest(){
return movingWest;
    }
    public boolean  fire(int x, int y){

        for(int i=0;i<robotInfos.length;i++){
            if(robotInfos[i].getX()==x &&robotInfos[i].getY()==y){
                double health=robotInfos[i].getHealth()-35.0;
                if(health<0) health=0;
                robotInfos[i].setHealth(health);
            }
        }
return true;
    }
}
