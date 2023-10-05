package edu.eci.arep;



import static spark.Spark.*;


/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args ) throws Exception {

        port(getPort());
        secure("keystores/ecikeypair.p12", "abc123", null, null);

        get("/hello", (req, res) -> {
            return "Hello Service!";
        });

    }

    public static int getPort() {
        return System.getenv("PORT") != null ? Integer.valueOf(System.getenv("PORT")) : 5000;
    }

}
