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
                String scores = null;
                int gameEnd = ticTacToe.hasWon();
                // If there is a win, send the update score to the web
                if (gameEnd != 0) {
                    if (gameEnd == 1)
                        scores = " " + Integer.toString(ticTacToe.getPlayerScore());
                    else if (gameEnd == 2)
                        scores = " 0 " + Integer.toString(ticTacToe.getComputerScore());
                    else if (gameEnd == 3)
                        scores = " 0 0 " + Integer.toString(ticTacToe.getTieScore());
                }

                return playerMove + " " + computerMove + scores;
            }
        });
        
        post(new Route("/selectdifficulty"){
            @Override
            public Object handle(Request request, Response response){
                String difficulty = request.queryParams("option");

                // Create a new game with the desired difficulty
                if (difficulty == "easy")
                    ticTacToe = new TicTacToe(true, false);
                else
                    ticTacToe = new TicTacToe(true, false);

                // Create the grid
                ticTacToe.makeGrid();

                return " ";
            }
        });
    }
}
