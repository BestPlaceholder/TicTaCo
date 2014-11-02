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
                //String hello = "You pressed: ";
                String tile = request.queryParams("tile");
                //return hello + title;
                return tile.substring(4) + " empty";
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
