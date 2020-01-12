package javafx;

import RobotPackage.RobotControl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ExampleJFXApp extends Application {

    private int i = 1;
    Button btnN = new Button("North");
    Button btnS = new Button("South");
    Button btnW = new Button("West");
    Button btnE = new Button("East");
    Button stop = new Button("STOP");
    Button btnRoN = new Button("North");
    Button btnRoS = new Button("South");
    Button btnRoW = new Button("West");
    Button btnRoE = new Button("East");
    Button btn1 = new Button("SHOOT");
    Button btn2 = new Button("SHOOT");

    public static void main(String[] args) {

        launch();


    }

    public void start(Stage stage) {
        stage.setTitle("Robot AI Test (JavaFX)");
        JFXArena arena = new JFXArena();
        ToolBar toolbar = new ToolBar();

        Button start = new Button("START");
        TextArea logger = new TextArea();

        Label label = new Label();
        label.setText("ROBOT 1:");

        Label label2 = new Label();
        label2.setText("ROBOT 2:");

        ToolBar toolbar2 = new ToolBar();
        toolbar.getItems().addAll(label, btn1, btnN, btnS, btnW, btnE);
        toolbar2.getItems().addAll(label2, btn2, btnRoN, btnRoS, btnRoW, btnRoE);
        disableButtons();
        start.setOnAction((event) -> {

            btnRoN.setDisable(false);
            btnRoS.setDisable(false);
            btnRoE.setDisable(false);
            btnRoW.setDisable(false);
            btn2.setDisable(false);
            btnN.setDisable(false);
            btnS.setDisable(false);
            btnE.setDisable(false);
            btnW.setDisable(false);
            btn1.setDisable(false);


        });

        stop.setOnAction((event) -> {
            disableButtons();
            start.setDisable(true);
        });
        btn1.setOnAction((event) ->
        {

            arena.shoot(1);


            logger.appendText("Robot 1:SHOOTS\n");


            if (arena.getMsg() != null) {

                logger.appendText(arena.getMsg() + "\n");

                if (arena.getMsg().equalsIgnoreCase("Robot 2 dead")) {
                    disableButtons();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("GAME OVER ! \n WINNER is ROBOT 1 !!");
                    alert.show();


                    start.setDisable(true);

                }

            }


        });
        btn2.setOnAction((event) ->
        {

            arena.shoot(2);


            logger.appendText("Robot 2:SHOOTS\n");


//           RobotControl.getAllRobots()[0].setY(1);
//            RobotControl.getAllRobots()[0].setX(1);


            if (arena.getMsg() != null) {

                logger.appendText(arena.getMsg() + "\n");
                if (arena.getMsg().equalsIgnoreCase("ROBOT 1 DEAD")) {

                    disableButtons();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("GAME OVER ! \n WINNER is ROBOT 2 !!");

                    alert.show();


                }

            }
        });


        btnN.setOnAction((event) ->
        {

            int c = RobotControl.getAllRobots()[0].getY() - i;
            if (c > 0) {
                System.out.println(c);
                if (!(RobotControl.getAllRobots()[0].getX() == RobotControl.getAllRobots()[1].getX() && c == RobotControl.getAllRobots()[1].getY()))
                    RobotControl.getAllRobots()[0].setY(c);

            }
            arena.setRobotPosition();

        });
        btnS.setOnAction((event) ->
        {
            int c = RobotControl.getAllRobots()[0].getY() + i;


            if (c <= 4) {

                if (!(RobotControl.getAllRobots()[0].getX() == RobotControl.getAllRobots()[1].getX() && c == RobotControl.getAllRobots()[1].getY()))
                    RobotControl.getAllRobots()[0].setY(c);

                // RobotControl.getAllRobots()[0].setX(1);
                arena.setRobotPosition();


            }

        });


        btnW.setOnAction((event) ->
        {
            int c = RobotControl.getAllRobots()[0].getX() - i;

            if (c > 0) {
                if (!(c == RobotControl.getAllRobots()[1].getX() && RobotControl.getAllRobots()[0].getY() == RobotControl.getAllRobots()[1].getY()))
                    RobotControl.getAllRobots()[0].setX(c);
                arena.setRobotPosition();

            }
        });

        btnE.setOnAction((event) ->
        {

            int c = RobotControl.getAllRobots()[0].getX() + i;


            if (c <= 4) {
                if (!(c == RobotControl.getAllRobots()[1].getX() && RobotControl.getAllRobots()[0].getY() == RobotControl.getAllRobots()[1].getY()))
                    RobotControl.getAllRobots()[0].setX(c);
                // RobotControl.getAllRobots()[0].setX(1);
                arena.setRobotPosition();

            }
        });


        btnRoN.setOnAction((event) ->
        {

            int c = RobotControl.getAllRobots()[1].getY() - i;
            if (c > 0) {
                if (!(RobotControl.getAllRobots()[1].getX() == RobotControl.getAllRobots()[0].getX() && c == RobotControl.getAllRobots()[0].getY()))
                    RobotControl.getAllRobots()[1].setY(c);

            }
            arena.setRobotPosition();


        });
        btnRoS.setOnAction((event) ->
        {
            int c = RobotControl.getAllRobots()[1].getY() + i;


            if (c <= 4) {
                if (!(RobotControl.getAllRobots()[1].getX() == RobotControl.getAllRobots()[0].getX() && c == RobotControl.getAllRobots()[0].getY()))
                    RobotControl.getAllRobots()[1].setY(c);

                // RobotControl.getAllRobots()[0].setX(1);
                arena.setRobotPosition();


            }

        });


        btnRoW.setOnAction((event) ->
        {
            int c = RobotControl.getAllRobots()[1].getX() - i;

            if (c > 0) {
                if (!(c == RobotControl.getAllRobots()[0].getX() && RobotControl.getAllRobots()[1].getY() == RobotControl.getAllRobots()[0].getY()))
                    RobotControl.getAllRobots()[1].setX(c);
                arena.setRobotPosition();
            }
        });

        btnRoE.setOnAction((event) ->
        {

            int c = RobotControl.getAllRobots()[1].getX() + i;


            if (c <= 4) {
                if (!(c == RobotControl.getAllRobots()[0].getX() && RobotControl.getAllRobots()[1].getY() == RobotControl.getAllRobots()[0].getY()))
                    RobotControl.getAllRobots()[1].setX(c);
                // RobotControl.getAllRobots()[0].setX(1);
                arena.setRobotPosition();

            }
        });


        ToolBar toolbar3 = new ToolBar();
        toolbar3.getItems().addAll(start, stop);
        BorderPane contentPane = new BorderPane();
        contentPane.setBottom(toolbar);
        contentPane.setCenter(arena);
        contentPane.setTop(toolbar2);
        contentPane.setLeft(toolbar3);

        BorderPane loggerPl = new BorderPane();
        loggerPl.setTop(logger);
        loggerPl.setBottom(toolbar3);


        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(contentPane, loggerPl);
        arena.setMinWidth(300.0);

        BorderPane border = new BorderPane();
        border.setCenter(splitPane);
//border.setBottom(toolbar3);

        Scene scene = new Scene(border, 800, 400);
        stage.setScene(scene);
        stage.show();


    }

    public void disableButtons() {
        btnRoN.setDisable(true);
        btnRoS.setDisable(true);
        btnRoE.setDisable(true);
        btnRoW.setDisable(true);
        btn2.setDisable(true);

        btnN.setDisable(true);
        btnS.setDisable(true);
        btnE.setDisable(true);
        btnW.setDisable(true);
        btn1.setDisable(true);
    }

}
