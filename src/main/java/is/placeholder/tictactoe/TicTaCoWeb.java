package is.placeholder.tictactoe;

import spark.*;
import static spark.Spark.*;
import spark.servlet.SparkApplication;

public class TicTaCoWeb implements SparkApplication {
    public static void main(String[] args){
        staticFileLocation("/public");
        SparkApplication tictacoweb = new TicTaCoWeb();
        String port = System.getenv("PORT");
        if (port != null) {
            setPort(Integer.valueOf(port));
        }
        tictacoweb.init(); 
    }

    private TicTacToe ticTacToe;

    /**
    *   A method that does EVERYTHING
    */
    public void init(){
        
        post(new Route("/playtic"){
            @Override
            public Object handle(Request request, Response response){
                // Get the coordinates of the tile that was clicked
                String tile = request.queryParams("tile");
                int tileX = Integer.parseInt(tile.substring(4, 5));
                int tileY = Integer.parseInt(tile.substring(5, 6));

                // Account for diffrence between tiles and arrays
                tileX--;tileY--;

                // Check for win
                if (ticTacToe.hasGameBeenWon()) {
                    ticTacToe.makeGrid();
                    return "clear";
                }

                // Make the player move, if invalid, return
                String playerMove;
                if (ticTacToe.humanPlayerMove(tileX, tileY) != null)
                    playerMove = Integer.toString(++tileX) + Integer.toString(++tileY) + " x";
                else
                    return " ";
                
                // Make the computer move
                String computerMove = null;
                int[] computerMoveCoordinates = ticTacToe.computerMove(ticTacToe.grid);
                if (computerMoveCoordinates != null)
                    computerMove = Integer.toString(++computerMoveCoordinates[0]) + 
                        Integer.toString(++computerMoveCoordinates[1]) + " o";
                else
                    computerMove = playerMove; // Say the same move if computer does nothing
                
                // Check for win
                String scores = "";
                int gameEnd = ticTacToe.hasWon();

                // If there is a win, send the update score to the web
                if (gameEnd != 0) {
                    ticTacToe.calculateScores(gameEnd);
                    if (gameEnd == 2)
                        scores = " " + Integer.toString(ticTacToe.getPlayerScore());
                    else if (gameEnd == 3)
                        scores = " 0 " + Integer.toString(ticTacToe.getComputerScore());
                    else if (gameEnd == 1)
                        scores = " 0 0 " + Integer.toString(ticTacToe.getTieScore());
                }

                //return playerMove + " " + computerMove + scores;
                return playerMove + " " + computerMove + scores;
            }
        });
        
        post(new Route("/selectdifficulty"){
            @Override
            public Object handle(Request request, Response response){
                String difficulty = request.queryParams("option");

                // Create a new game with the desired difficulty
                if (difficulty.equals("easy"))
                    ticTacToe = new TicTacToe(true, false);
                else
                    ticTacToe = new TicTacToe(true, true);

                // Create the grid
                ticTacToe.makeGrid();

                return difficulty;
            }
        });
    }
}
