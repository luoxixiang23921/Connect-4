import java.util.*;

public class ConnectFourGame implements ConnectFourRules
{
  //play the game here
  Scanner in = new Scanner(System.in);
  Board boardObj = new Board();
  String player1 = "";
  String player2 = "";
  String currentPlayer = "";
  int Tiecount = 0;
  boolean game_over = false;
  boolean again = true;

  public void play(){
    intro();
    while(again){
      while(!game_over && !Tie()){
      move();
      checkWinner();
      }
      if(Tie()) System.out.println("It's a Tie!\n");
      else congratulation();
      if(againOrNot() == false) again = false;
    }
  }

  public boolean againOrNot(){
    String response = "";
    System.out.println("Would you like to play again? (enter 'yes' or press any key for 'no')");
    response = in.next();
    if(response.equals("yes")){
      reset();
      return true;
    }
    else{
      System.out.println("\nHave a nice day （～￣▽￣～）");
      return false;
    } 
  }

  public void reset(){
    boardObj = new Board();
    Tiecount = 0;
    game_over = false;
    clearScreen();
    boardObj.display_board();
  }
  
  public void intro(){
    System.out.println("Welcome to Connect 4!\n");
    System.out.println("player 1 will be X, player 2 will be O. Player 1 will start first, but Player 2 will go first the next round and so on.\n");
    System.out.print("Please enter the name for player 1: ");
    player1 = in.nextLine();
    System.out.print("Please enter the name for player 2: ");
    player2 = in.nextLine();
    currentPlayer = player1; // player 1 starts
  }

  public void move(){ 
    clearScreen();
    boardObj.display_board();
    System.out.println("It's " + currentPlayer + "'s turn");
    System.out.println("Choose the column by entering number from (1-7)");
    int user_input = getValidInput() - 1; 
    drop(user_input,currentPlayer);
    boardObj.display_board();  
    if(currentPlayer.equals(player1)) currentPlayer = player2; // changes turns
    else currentPlayer = player1;
    Tiecount++;
  }
    

  public void drop(int user_col, String player){ // with the verified column number, drop 
    char temp = ' ';
    if(player.equals(player1)){
      temp = 'X';
    }else{
      temp = 'O';
    }
    for(int i = boardObj.getRow() - 1; i >= 0; i--){
      if(boardObj.getBoard()[i][user_col] == '-'){
        boardObj.getBoard()[i][user_col] = temp;
        break;
      }
    } 
  }

  public int getValidInput(){ // return a valid user input
    System.out.print("Please Enter the column number: ");
    int user_col = in.nextInt(); 
    boolean valid = user_col > 0 && user_col < 8 && boardObj.getBoard()[0][user_col-1] == '-'; // index starts from 0
    while(!valid){
      System.out.print("Invalid Input, Please enter a column number(1-7): ");
      int temp = in.nextInt();
      if(temp > 0 && temp < 8 && boardObj.getBoard()[0][temp-1] == '-'){
        return temp;
      } 
    }
    return user_col;
  } 

  public void checkWinner(){
    char[][] temp = boardObj.getBoard();
    if(checkDIAGdown(temp) || checkDIAGup(temp) || checkHorizontal(temp) || checkVertical(temp)){
      game_over = true;
      clearScreen();
      boardObj.display_board();
    }
  }

  public boolean Tie(){
    if(Tiecount == boardObj.getRow()*boardObj.getCol()){
      return true;
    }
    return false;
  }


  public boolean checkVertical(char[][] b){ // return true if there is four in a row in a column
    for(int col = 0; col < b[0].length; col++){
      int count = 1;
      char last = ' ';
      for(int row = 0; row < b.length; row++) {
        if(b[row][col] != '-' && b[row][col] == last) {
        count++;
        }
        else {
        count = 1;
        }
        last = b[row][col];
        if(count == 4) {
        return true;
        }
      }
		}
		return false;
  }

  public boolean checkHorizontal(char[][] b){ // return true if there is four in a row in a row
    for(int row =  b.length-1; row >= 0; row--) {
    int count = 1;
    char last = ' ';
      for(int col = 0; col < b[0].length; col++) {
        if(b[row][col] != '-' && b[row][col] == last) {
          count++;
        }
        else {
          count = 1;
        }
        last = b[row][col];
        if(count == 4) {
          return true;
        }
      }
    }
		return false;
  }

  public boolean checkDIAGdown(char[][]b) {
		int rowNum = b.length;
		int colNum = b[0].length;
		
		for(int k = rowNum-1; k >= 0; k--) {
			int row = k;
			int col = 0;
			int count = 1;
			char last = ' ';
			while(row <= rowNum-1) {
				if(b[row][col] != '-' && b[row][col] == last) {
					count++;
				}
				else {
					count = 1;
				}
				last = b[row][col];
				if(count == 4) {
					return true;
				}
				
				row = row + 1;
				col = col + 1;
			}
		}
		
		for(int k = colNum-1; k >= 1; k--) { // these is a redundant diagonal
			int row = 0;
			int col = k;
			int count = 1;
			char last = ' ';
			while(col <= colNum-1) {
				if(b[row][col] != '-' && b[row][col] == last) {
					count++;
				}
				else {
					count = 1;
				}
				last = b[row][col];
				if(count == 4) {
					return true;
				}
				
				row = row + 1;
				col = col + 1;
			}
		}
		return false;
	}

	public boolean checkDIAGup(char[][]b) {
		int rowNum = b.length;
		int colNum = b[0].length;
		
		for(int k = 0; k < rowNum; k++) {
			int row = k;
			int col = 0;
			int count = 1;
			char last = ' ';
			while(row >= 0) {
				if(b[row][col] != '-' && b[row][col] == last) {
					count++;
				}
				else {
					count = 1;
				}
				last = b[row][col];
				if(count == 4) {
					return true;
				}
				
				row = row - 1;
				col = col + 1;
			}
		}
		
		for(int k = 1; k < rowNum; k++) { // these is a redundant diagonal, so k = 1 instead of 0
			int row = rowNum-1;
			int col = k;
			int count = 1;
			char last = ' ';
			while(col <= colNum-1) {
				if(b[row][col] != '-' && b[row][col] == last) {
					count++;
				}
				else {
					count = 1;
				}
				last = b[row][col];
				if(count == 4) {
					return true;
				}
				
				row = row - 1;
				col = col + 1;
			}
		}
		return false;
	}

  public void congratulation(){
    if(currentPlayer.equals(player1)){
      System.out.println("The winner is " + player2 + "!"); // I swap the current player at the end of every move, so the last player makes the winning move will cause the currentPlayer to become the loser. 
    }
    else{
      System.out.println("\nThe winner is " + player1 + "!");
    }
  }
  public static void clearScreen() {  
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
  }  


  
}


