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
        
        get(new Route("/gethi"){
            @Override
            public Object handle(Request request, Response response){
                String hello = "Hello world";
                return hello;
            }
	    });
    }
}
