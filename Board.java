public class Board
{//construct and print the board 6 by 7
  int row = 6;
  int col = 7;
  char[][] board = new char[row][col];

  public Board(){
    for(int i=0; i < board.length; i++){
      for(int j = 0; j < board[0].length; j++){
        board[i][j] = '-';
      }
    }
    display_board();
  }

  public void display_board(){
    for(char[] row : board){
      for(char col : row){
        System.out.print("  " + col + "  ");
      }
      System.out.println("\n");
    }

    System.out.println("  1    2    3    4    5    6    7\n");
  }

  public int getRow(){
    return row;
  }

  public int getCol(){
    return col;
  }

  public char[][] getBoard(){
    return board;
  }

}