import com.google.gson.Gson;

import static spark.Spark.*;

public class Server {
    public static void main(String[] args) {
        Gson gson = new Gson();
        get("/hello", "application/json", (req, res) -> {
            return "Hello world";
        }, gson::toJson);
    }
}
