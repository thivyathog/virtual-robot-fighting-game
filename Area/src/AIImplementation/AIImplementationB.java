package AIImplementation;

import RobotPackage.RobotAI;
import RobotPackage.RobotControl;
import RobotPackage.RobotInfo;

public class AIImplementationB implements RobotAI, Runnable {
    int n = 0;
    Thread object;
    RobotControl current;

    @Override

    public void runAI(RobotControl rc) {
        object = new Thread(new AIImplementationA());
        object.start();
        current = rc;

        rc.setRobotInfos(new RobotInfo(rc.getRf().getName(), rc.getX(), rc.getY(), rc.getRf().getHealth()));
    }

    public RobotControl getCurrent() {
        return current;
    }

    public void sleepThread() throws InterruptedException {
        object.sleep(1000);
    }

    @Override
    public void run() {
        try {
            // Displaying the thread that is running
            System.out.println("Thread " +
                    Thread.currentThread().getId() +
                    " is running");


        } catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }
}
