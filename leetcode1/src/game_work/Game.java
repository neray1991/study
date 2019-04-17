package game_work;

import java.util.*;


public class Game
{
  private Grid grid;
  private int userRow;
  private int msElapsed;
  private int timesGet;
  private int timesAvoid;
  
  public Game()
  {
    grid = new Grid(5, 10);
    userRow = 0;
    msElapsed = 0;
    timesGet = 0;
    timesAvoid = 0;
    updateTitle();
    grid.setImage(new Location(userRow, 0), "user.gif");

  }
  
  public void play()
  {
    while (!isGameOver())
    {
      grid.pause(100);
      handleKeyPress();
      if (msElapsed % 300 == 0)
      {
        scrollLeft();
        populateRightEdge();
      }
      updateTitle();
      msElapsed += 100;
    }
  }
  
  public void handleKeyPress()
  {
	  int key = grid.checkLastKeyPressed();
	  int pre = userRow;
      if (key == 38) {
    	  System.out.println(key);
    	  userRow--;
    	  if (userRow < 0) userRow = grid.getNumRows() - 1;
      } else if (key == 40) {
    	  userRow++;
    	  if (userRow >= grid.getNumRows()) userRow = 0;
      }
	  handleCollision(new Location(userRow,0));
	  grid.setImage(new Location(userRow,0), "user.gif");
      if (pre != userRow) {
    	  grid.setImage(new Location(pre,0), "null");
      }
  }
  
  public void populateRightEdge()
  {
	  int gainNum = (int) (3 * Math.random());
	  int[] pos = new int[3];
	  int rows = grid.getNumRows();
	  int cols = grid.getNumCols();
	  for (int i = 0; i < 3; i++)
		  pos[i] = (int) (rows * Math.random());
	  if (gainNum == 0) {
		  grid.setImage(new Location(pos[0],cols-1), "avoid.gif");
	  } else {
		  for (int i = 0; i < gainNum; i++) {
			  grid.setImage(new Location(pos[i],cols-1), "get.gif");
		  }
	  }
  }
  
  public void scrollLeft()
  {
	  int rows = grid.getNumRows();
	  int cols = grid.getNumCols();
	  for (int i = 0; i < rows; i++) {
		  for (int j = 0; j < cols-1; j++) {
			  String pic = grid.getImage(new Location(i, j+1));
			  if (pic != "null" && pic != null) {
				  grid.setImage(new Location(i,j), pic);
				  grid.setImage(new Location(i,j+1), "null");
			  }
			  if (j == 0 && pic == "avoid.gif") timesAvoid++;
		  }
	  }
  }
  
  public void handleCollision(Location loc)
  {
	  if (grid.getImage(loc) == "get.gif")
		  timesGet++;
	  else if (grid.getImage(loc) == "avoid.gif")
		  timesAvoid--;
  }
  
  public int getScore()
  {
    return timesGet + timesAvoid;
  }
  
  public void updateTitle()
  {
    grid.setTitle("Game:  " + getScore());
  }
  
  public boolean isGameOver()
  {
    return this.getScore() > 10;
  }
  
  public static void test()
  {
    Game game = new Game();
    game.play();
  }
  
  public static void main(String[] args)
  {
    test();
  }
}