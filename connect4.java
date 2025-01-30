import java.util.Scanner;
public class connect4 
{
    String[][] board;
    boolean winner;
    boolean draw;
    int win;
    int playerturn;

    public connect4() 
    {
        win=0;
        draw=false;
        playerturn=1;
        board=new String[6][7];
        newBoard();
    }

    public void newBoard() 
    {
        for(int i=0;i<6;i++) 
        {
            for(int j=0;j<7;j++) 
            {
                board[i][j] = " - ";
            }
        }
    }

    private void display() 
    {
        System.out.println("CONNECT 4 GAME");
        for(int i=0;i<6;i++) 
        {
            for(int j=0;j<7;j++) 
            {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public boolean dropDisc(int col,String disc) 
    {
        for (int i=5;i>=0;i--) 
        {  // Start from bottom row
            if (board[i][col].equals(" - ")) 
            {
                board[i][col] = disc;
                return true;
            }
        }
        return false;
    }

    public boolean checkWin(String disc) 
    {
        // Check horizontal, vertical, and diagonal wins
        for (int i=0;i<6;i++) 
        {  // Check rows
            for (int j=0;j<4;j++)
            {
                if (board[i][j].equals(disc) && board[i][j + 1].equals(disc) && board[i][j + 2].equals(disc) && board[i][j + 3].equals(disc)) 
                {
                    return true;
                }
            }
        }

        for (int i = 0; i < 3; i++) 
        {  // Check columns
            for (int j = 0; j < 7; j++) {
                if (board[i][j].equals(disc) && board[i + 1][j].equals(disc) && board[i + 2][j].equals(disc) && board[i + 3][j].equals(disc)) {
                    return true;
                }
            }
        }

        for(int i=0;i<3;i++) 
        {  // Check diagonal (\)
            for(int j=0;j<4;j++) 
            {
                if (board[i][j].equals(disc) && board[i + 1][j + 1].equals(disc) && board[i + 2][j + 2].equals(disc) && board[i + 3][j + 3].equals(disc)) 
                {
                    return true;
                }
            }
        }

        for(int i=3;i<6;i++) 
        {  // Check diagonal (/)
            for (int j=0;j<4;j++) 
            {
                if (board[i][j].equals(disc) && board[i - 1][j + 1].equals(disc) && board[i - 2][j + 2].equals(disc) && board[i - 3][j + 3].equals(disc)) 
                {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkDraw() 
    {
        for (int i = 0; i < 6; i++) 
        {
            for (int j = 0; j < 7; j++) 
            {
                if (board[i][j].equals(" - ")) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        connect4 obj = new connect4();
        Scanner sc = new Scanner(System.in);
        String disc;
        boolean gameOn = true;

        while (gameOn) 
        {
            obj.display();
            disc=obj.playerturn == 1 ? " X " : " O ";
            System.out.println("Player " + obj.playerturn + "'s turn. Enter column (0-6): ");
            int col=sc.nextInt();

            if (col<0 || col>6 || !obj.dropDisc(col,disc)) 
            {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            if(obj.checkWin(disc)) 
            {
                obj.display();
                System.out.println("Player " + obj.playerturn + " wins!");
                gameOn=false;
            }
            else if(obj.checkDraw()) 
            {
                obj.display();
                System.out.println("It's a draw!");
                gameOn=false;
            } 
            else 
            {
                obj.playerturn=obj.playerturn == 1 ? 2 : 1;  // Switch player
            }
        }
    }
}
