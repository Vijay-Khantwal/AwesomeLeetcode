/**
 * SudukoSolver
 */
import java.util.Arrays;

public class SudukoSolver {

    public static void main(String[] args) {
        char[][] board = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        if(solve(board)){
            System.out.println("Suduko Solved! ");
            System.out.println("Output: ");
            for(char[] row : board){
                System.out.println(Arrays.toString(row));
            }
        }
        else{
            System.out.println("Cannot Solve Suduko.");
        }

    }

    public static boolean solve(char[][] board){
        int row  = -1 ; int col = -1;
        int empty_found = 0;

        for(int i = 0; i<9 ;i++){
            for(int j = 0; j<9 ; j++){
                if(board[i][j] == '.'){ 
                    row = i; col = j;
                    empty_found = 1;
                    break;
                }
            }
            if(empty_found == 1){
                break;
            }
        }

        if(empty_found == 0){
            return true;
        }

        for(int i = 1 ; i<=9 ; i++){
            if(isSafe(board,row,col,i)){
                board[row][col] = (char)(i+'0');
                if(solve(board)){
                    return true;
                }
                else{
                    board[row][col] = '.';
                }
            }
        }

        return false;

    }

    public static boolean isSafe(char[][] board,int row,int col,int num){
        char ch = (char)(num + '0');

        //checks column
        for(char[] line : board){
            if(line[col] == ch){
                return false;
            }
        }

        //checks row
        for(int i = 0; i<9 ; i++){
            if(board[row][i] == ch){
                return false;
            }
        }

        //checks Sub-blocks of 3*3
        int subRowStart = row - row%3 ;  
        int subColStart = col - col%3 ;  
        for(int i = subRowStart; i< subRowStart+3;i++){
            for(int j = subColStart; j< subColStart+3 ; j++){
                if(board[i][j] == ch){
                    return false;
                }
            }
        }

        return true;
    }
}