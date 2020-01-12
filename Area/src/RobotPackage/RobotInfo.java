package RobotPackage;

public class RobotInfo {

   private String name;
private int  x;
private int y;
private double health;

   public RobotInfo(String name, int x, int y, double health) {
      this.name = name;
      this.x = x;
      this.y = y;
      this.health = health;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getX() {
      return x;
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

   public double getHealth() {
      return health;
   }

   public void setHealth(double health) {
      this.health = health;
   }
}
