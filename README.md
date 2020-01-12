# virtual-robot-fighting-game
It is a virtual robot fighting game, where the robots are controlled by code, and move around an on-screen grid (“arena”). Each robot is run on a thread.


## This is what happens from start to end:
(1) The applicationt initialise the AIs and the arena. the user is asked for the
relevant details,
Specifically, you (or the user) will need to decide on the arena size (number of rows
and columns), number of competing robots, AI type for each robot, starting locations,
and robot names. Each robot should be identified by a unique name. 

(2) The application displays the robots at their starting positions within the arena.

(3) The user decides when to start the game (e.g., by pressing a button).

(4) The game must then play out, according to the other requirements below. As it does
so, the application displays the robots and also shows a running log of events.

(5) The user can choose to stop the game at some point. The AIs must discontinue all
actions at this point but the application must remain running, showing the robots’
last positions. The game must also stop when there is only one robot left “alive”.


## Display/Visualisation Requirements
The app shows the following things on-screen (in a GUI window):

• All robots in their correct locations within the arena. it includes a .png/.jpg
file in your app to represent the robots. (You can use multiple images if you like.)

• Underneath each robot, its name and health (as a percentage).

• All shots fired. Show a line from the shooter to the target grid cell, for a period of
250ms. That is, the line should appear on the screen as the shot is fired, and disappear
250ms later.

• In a separate area of the screen, a sequence of text messages indicating when (1) a
robot shoots and hits another robot, (2) a robot dies, and (3) the game is over.
This must all happen in “real-time”, as the game unfolds.


## Rules of the Game
Robots can move around the grid/arena, one square at a time, and fire a laser at other
robots.

Here are the precise rules that the application must also enforce:

• Robots cannot move “outside” the arena.

• Multiple robots cannot occupy the same grid square.

• Robots cannot move within 1 second of having previously moved.

• To fire its laser, a robot must specify a target grid cell that is no more than two grid
cells away from its current location, horizontally and/or vertically. For instance, if
the robot is at (2, 5), it can target (0, 3), but not (5, 5).

• A laser will not actually fire until 500ms after a robot commits to firing it at a particular
location. The robot must wait, motionless, during this period of time.

• Each robot starts with 100.0 units of “health”, and loses 35.0 units whenever it gets
hit, down to a minimum of 0.0.

• A robot is fully functional until its health reaches 0.0, at which point it dies. The last
robot standing wins.

• A dead robot cannot move or fire!

You may optionally make these constraints configurable, if you like.
The move...() and fire() methods in RobotControl must return true if the action was legal,
and hence actually carried out, and false if it would have broken one of the above rules.
(Note: the return value does not indicate whether the robot’s laser actually hit anything.)

