package mx.uv.c80640;

import static spark.Spark.*;
import com.google.gson.*;

import java.util.List;
import java.util.UUID;

/**
 * Hello world!
 *
 */
public class App 
{
    public static Gson gson = new Gson();
    public static void main( String[] args )
    {
        port(getHerokuAssignedPort());
        System.out.println( "Hello World!" );
        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            System.out.println(accessControlRequestHeaders);
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            System.out.println(accessControlRequestMethod);
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });
        before((req, res)-> res.header("Access-Control-Allow-Origin", "*"));
        post("/", (req, res) -> {
            String datosMonumento = req.body();
            // String id = UUID.randomUUID().toString();
            Monumento u = gson.fromJson(datosMonumento, Monumento.class);
            // u.setId(id);

            // devolver una respuesta JSON
            JsonObject objetoJson = new JsonObject();
            // objetoJson.addProperty("status", DAO.crearUsuario(u));
            // objetoJson.addProperty("id", id);
            return objetoJson;
        });

    }
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; // return default port if heroku-port isn't set (i.e. on localhost)
    }
    
}
