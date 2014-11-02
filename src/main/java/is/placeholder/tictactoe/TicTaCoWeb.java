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

    public void init(){
        
        post(new Route("/playtic"){
            @Override
            public Object handle(Request request, Response response){
                String tile = request.queryParams("tile");

                int tileX = Integer.parseInt(tile.substring(4, 5));
                int tileY = Integer.parseInt(tile.substring(5, 6));

                String playerMove = Integer.toString(tileX) + Integer.toString(tileY) + " x";
                String computerMove = "33 o";

                return playerMove + " " + computerMove;
            }
        });
        
        post(new Route("/selectdifficulty"){
            @Override
            public Object handle(Request request, Response response){
                return "13 empty";
            }
        });
    }
}
