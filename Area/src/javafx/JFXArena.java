package javafx;

import AIImplementation.AIImplementationA;
import AIImplementation.AIImplementationB;
import RobotPackage.RobotControl;
import RobotPackage.RobotInfo;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A JavaFX GUI element that displays a grid on which you can draw images, text and lines.
 */
public class JFXArena extends Pane {
    // Represents the image to draw. You can modify this to introduce multiple images.
    private static final String IMAGE_FILE = "1554047213.png";
    private Image robot1;
    RobotInfo[] currentRobot;
    // The following values are arbitrary, and you may need to modify them according to the 
    // requirements of your application.
    private int gridWidth = 4;
    private int gridHeight = 4;
    private double robotX = 1;
    private double robotY = 1;
    private String msg;
    private double gridSquareSize; // Auto-calculated
    private Canvas canvas; // Used to provide a 'drawing surface'.
    boolean fire = false;
    private AIImplementationA a;
    private AIImplementationB b;


    public JFXArena() {
        a = new AIImplementationA();
        a.runAI(new RobotControl("robot1", 2, 2, 100, 0));

        b = new AIImplementationB();
        b.runAI(new RobotControl("robot2", 1, 1, 100, 1));
        System.out.println(RobotControl.getAllRobots()[0].getName());
        System.out.println(RobotControl.getAllRobots()[1].getName());


        robot1 = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(IMAGE_FILE)));

        // You will get an exception here if the specified image file cannot be found.

        canvas = new Canvas();
        canvas.widthProperty().bind(widthProperty());
        canvas.heightProperty().bind(heightProperty());
        getChildren().add(canvas);
    }

    public String getMsg() {
        return msg;
    }

    /**
     * Moves a robot image to a new grid position.
     * <p>
     * You will probably need to significantly modify this method. Currently it just serves as a
     * demonstration.
     */
    public void setRobotPosition() {
//
//        robotX = x;
//        robotY = y;

        robotX = RobotControl.getAllRobots()[0].getX();
        robotY = RobotControl.getAllRobots()[0].getY();

        requestLayout();
    }


    /**
     * This method is called in order to redraw the screen, either because the user is manipulating
     * the window, OR because you've called 'requestLayout()'.
     * <p>
     * You will need to modify the last part of this method; specifically the sequence of calls to
     * the other 'draw...()' methods. You shouldn't need to modify anything else about it.
     */


    @Override
    public void layoutChildren() {
        super.layoutChildren();
        GraphicsContext gfx = canvas.getGraphicsContext2D();
        gfx.clearRect(0.0, 0.0, canvas.getWidth(), canvas.getHeight());

        // First, calculate how big each grid cell should be, in pixels. (We do need to do this
        // every time we repaint the arena, because the size can change.)
        gridSquareSize = Math.min(
                getWidth() / (double) gridWidth,
                getHeight() / (double) gridHeight);

        double arenaPixelWidth = gridWidth * gridSquareSize;
        double arenaPixelHeight = gridHeight * gridSquareSize;


        // Draw the arena grid lines. This may help for debugging purposes, and just generally
        // to see what's going on.
        gfx.setStroke(Color.DARKGREY);
        gfx.strokeRect(0.0, 0.0, arenaPixelWidth - 1.0, arenaPixelHeight - 1.0); // Outer edge

        for (int gridX = 1; gridX < gridWidth; gridX++) // Internal vertical grid lines
        {
            double x = (double) gridX * gridSquareSize;
            gfx.strokeLine(x, 0.0, x, arenaPixelHeight);
        }

        for (int gridY = 1; gridY < gridHeight; gridY++) // Internal horizontal grid lines
        {
            double y = (double) gridY * gridSquareSize;
            gfx.strokeLine(0.0, y, arenaPixelWidth, y);
        }

        // Invoke helper methods to draw things at the current location.
        // ** You will need to adapt this to the requirements of your application. **

//
//        drawImage(gfx, robot1, robotX,  robotY);
//        drawLabel(gfx, "Robot Name (100%)", robotX , robotY);


        drawImage(gfx, robot1, RobotControl.getAllRobots()[0].getX() - 1, RobotControl.getAllRobots()[0].getY() - 1);
        drawLabel(gfx, RobotControl.getAllRobots()[0].getName() + "(" + RobotControl.getAllRobots()[0].getHealth() + "%)", RobotControl.getAllRobots()[0].getX() - 1, RobotControl.getAllRobots()[0].getY() - 1);


        drawImage(gfx, robot1, RobotControl.getAllRobots()[1].getX() - 1, RobotControl.getAllRobots()[1].getY() - 1);
        drawLabel(gfx, RobotControl.getAllRobots()[1].getName() + "(" + RobotControl.getAllRobots()[1].getHealth() + "%)", RobotControl.getAllRobots()[1].getX() - 1, RobotControl.getAllRobots()[1].getY() - 1);

        if (fire) {
            drawLine(gfx, RobotControl.getAllRobots()[0].getX() - 1, RobotControl.getAllRobots()[0].getY() - 1, RobotControl.getAllRobots()[1].getX() - 1, RobotControl.getAllRobots()[1].getY() - 1);

        }


    }


    /**
     * Draw an image in a specific grid location. *Only* call this from within layoutChildren().
     * <p>
     * Note that the grid location can be fractional, so that (for instance), you can draw an image
     * at location (3.5,4), and it will appear on the boundary between grid cells (3,4) and (4,4).
     * <p>
     * You shouldn't need to modify this method.
     */
    private void drawImage(GraphicsContext gfx, Image image, double gridX, double gridY) {
        // Get the pixel coordinates representing the centre of where the image is to be drawn. 
        double x = (gridX + 0.5) * gridSquareSize;
        double y = (gridY + 0.5) * gridSquareSize;

        // We also need to know how "big" to make the image. The image file has a natural width 
        // and height, but that's not necessarily the size we want to draw it on the screen. We 
        // do, however, want to preserve its aspect ratio.
        double fullSizePixelWidth = robot1.getWidth();
        double fullSizePixelHeight = robot1.getHeight();

        double displayedPixelWidth, displayedPixelHeight;
        if (fullSizePixelWidth > fullSizePixelHeight) {
            // Here, the image is wider than it is high, so we'll display it such that it's as 
            // wide as a full grid cell, and the height will be set to preserve the aspect 
            // ratio.
            displayedPixelWidth = gridSquareSize;
            displayedPixelHeight = gridSquareSize * fullSizePixelHeight / fullSizePixelWidth;
        } else {
            // Otherwise, it's the other way around -- full height, and width is set to 
            // preserve the aspect ratio.
            displayedPixelHeight = gridSquareSize;
            displayedPixelWidth = gridSquareSize * fullSizePixelWidth / fullSizePixelHeight;
        }

        // Actually put the image on the screen.
        gfx.drawImage(image,
                x - displayedPixelWidth / 2.0,  // Top-left pixel coordinates.
                y - displayedPixelHeight / 2.0,
                displayedPixelWidth,              // Size of displayed image.
                displayedPixelHeight);
    }


    /**
     * Displays a string of text underneath a specific grid location. *Only* call this from within
     * layoutChildren().
     * <p>
     * You shouldn't need to modify this method.
     */
    private void drawLabel(GraphicsContext gfx, String label, double gridX, double gridY) {
        gfx.setTextAlign(TextAlignment.CENTER);
        gfx.setTextBaseline(VPos.TOP);
        gfx.setStroke(Color.BLUE);
        gfx.strokeText(label, (gridX + 0.5) * gridSquareSize, (gridY + 1.0) * gridSquareSize);
    }

    /**
     * Draws a (slightly clipped) line between two grid coordinates.
     * <p>
     * You shouldn't need to modify this method.
     */
    private void drawLine(GraphicsContext gfx, double gridX1, double gridY1,
                          double gridX2, double gridY2) {
        gfx.setStroke(Color.RED);

        // Recalculate the starting coordinate to be one unit closer to the destination, so that it
        // doesn't overlap with any image appearing in the starting grid cell.
        final double radius = 0.5;
        double angle = Math.atan2(gridY2 - gridY1, gridX2 - gridX1);
        double clippedGridX1 = gridX1 + Math.cos(angle) * radius;
        double clippedGridY1 = gridY1 + Math.sin(angle) * radius;

        gfx.strokeLine((clippedGridX1 + 0.5) * gridSquareSize,
                (clippedGridY1 + 0.5) * gridSquareSize,
                (gridX2 + 0.5) * gridSquareSize,
                (gridY2 + 0.5) * gridSquareSize);


    }


    public void gameScenario(RobotControl rc) {


        if (rc.getRf().getName().equalsIgnoreCase("robot1")) {
            if ((Math.abs(RobotControl.getAllRobots()[0].getX() - RobotControl.getAllRobots()[1].getX()) <= 2) && (Math.abs(RobotControl.getAllRobots()[0].getY() - RobotControl.getAllRobots()[1].getY()) <= 2)) {
                ///  rc.fire(2, 3);
                fire = true;
                rc.fire(RobotControl.getAllRobots()[1].getY(), RobotControl.getAllRobots()[1].getY());
                setRobotPosition();

            }
            else{
                msg="INVALID SHOT!";
            }
            System.out.println("HELLL" + RobotControl.getAllRobots()[1].getHealth());
            if (RobotControl.getAllRobots()[1].getHealth() == 0.0) {
                msg = "ROBOT 2 DEAD";
                System.out.println("DEAD");
            }
        } else {
            if ((Math.abs(RobotControl.getAllRobots()[1].getX() - RobotControl.getAllRobots()[0].getX()) <= 2) && (Math.abs(RobotControl.getAllRobots()[1].getY() - RobotControl.getAllRobots()[0].getY()) <= 2)) {
                ///  rc.fire(2, 3);

                fire = true;
                rc.fire(RobotControl.getAllRobots()[0].getY(), RobotControl.getAllRobots()[0].getY());
                setRobotPosition();

            }
            else{
                msg="INVALID SHOT!";
            }
            if (RobotControl.getAllRobots()[0].getHealth() == 0.0)
                msg = "ROBOT 1 DEAD";
        }
    }

    public void shoot(int name) {
        System.out.println(name + "NAME");
        if (name == 1) {
            System.out.println("R");
            gameScenario(a.getCurrent());


        } else {
            gameScenario(b.getCurrent());
            System.out.println("L");
        }
        Timer timer = new Timer();
        System.out.println("Enter");

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                fire = false;
                requestLayout();
            }
        }, 250);

    }


}

