import java.util.ArrayList;
import java.util.List;

public class NQueens51 {
     public static void main(String[] args) {
        int n = 4;
        List<List<String>> ans = solveNQueens(n);

        for(List<String> arr : ans){
            System.out.println(arr);
        }

     }

     public static List<List<String>> solveNQueens(int n) {
        boolean[][] board = new boolean[n][n];
        List<List<String>> solution = new ArrayList<>();
        solve(board,0,solution);
        return solution;
    }
    public static void solve(boolean[][] board, int row, List<List<String>> solution){
        if(row == board.length){
            List<String> list = getAnswerList(board);

            solution.add(list);

        }
        for(int col =0; col<board.length; col++){
            if(isSafe(board,row,col)){
                board[row][col] = true;
                solve(board,row+1,solution);
                board[row][col] = false;
            }
        }
    }

    private static List<String> getAnswerList(boolean[][] board) {
        List<String> list = new ArrayList<>();
        for(boolean[] row : board){
            StringBuilder str = new StringBuilder();
            for(boolean col : row){
                if(col){
                    str = str.append("Q");
                }
                else{
                    str = str.append(".");
                }
            }
            list.add(str.toString());
        }

        return list;
    }

    private static boolean isSafe(boolean[][] board, int row, int col) {
        int LeftMax = Math.min(row,col);
        int RightMax = Math.min(row,board.length-col-1);
        for (int i = row-1; i >= 0; i--) {
            if(board[i][col]){
                return false;
            }
        }
        for(int i = 1 ; i<= LeftMax ; i++){
            if(board[row-i][col-i]){
                return false;
            }
        }
        for(int i = 1 ; i<= RightMax ; i++){
            if(board[row-i][col+i]){
                return false;
            }
        }

        return true;
    }
}