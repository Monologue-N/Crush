package com.monologue.crush.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monologue.crush.entity.Game;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.monologue.crush.external.TwitchClient;
import com.monologue.crush.external.TwitchException;
import org.json.JSONObject;
import org.apache.commons.io.IOUtils;

@WebServlet(name = "GameServlet", urlPatterns = {"/game"})
public class GameServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Read game information from request body
//        JSONObject jsonRequest = new JSONObject(IOUtils.toString(request.getReader()));
//        String name = jsonRequest.getString("name");
//        String developer = jsonRequest.getString("developer");
//        String releaseTime = jsonRequest.getString("release_time");
//        String website = jsonRequest.getString("website");
//        float price = jsonRequest.getFloat("price");
//
//        // Print game information to IDE console
//        System.out.println("Name is: " + name);
//        System.out.println("Developer is: " + developer);
//        System.out.println("Release Time is: " + releaseTime);
//        System.out.println("Website is: " + website);
//        System.out.println("Price is: " + price);
//
//        // Return status = ok as response body to the client
//        response.setContentType("application/json");
//        JSONObject jsonResponse = new JSONObject();
//        jsonResponse.put("status", "ok");
//        response.getWriter().print(jsonResponse);
//    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////        response.getWriter().print("Hello World : )");
////        String gameName = request.getParameter("gamename");
////        response.getWriter().println("com.monologue.crush.entity.Game is: " + gameName);
//        response.setContentType("application/json");
////        JSONObject game = new JSONObject();
////        game.put("name", "Sonder");
////        game.put("developer", "Monologue_N");
////        game.put("release_time", "Dec 25, 2020");
////        game.put("website", "https://monologue-n.github.io/");
////        game.put("price", 19.99);
//        ObjectMapper mapper = new ObjectMapper();
//
//        Game.Builder builder = new Game.Builder();
//        builder.setName("Sonder");
//        builder.setDeveloper("Monologue_N");
//        builder.setReleaseTime("Dec 25, 2020");
//        builder.setWebsite("https://monologue-n.github.io/");
//        builder.setPrice(19.99);
//
//        Game game = builder.build();
//
//        // Write game information to response body
////        response.getWriter().print(game);
//        response.getWriter().print(mapper.writeValueAsString(game));
        // Get gameName from request URL.
        String gameName = request.getParameter("game_name");
        TwitchClient client = new TwitchClient();

        // Let the client know the returned data is in JSON format.
        response.setContentType("application/json;charset=UTF-8");
        try {
            // Return the dedicated game information if gameName is provided in the request URL, otherwise return the top x games.
            if (gameName != null) {
                response.getWriter().print(new ObjectMapper().writeValueAsString(client.searchGame(gameName)));
            } else {
                response.getWriter().print(new ObjectMapper().writeValueAsString(client.topGames(0)));
            }
        } catch (TwitchException e) {
            throw new ServletException(e);
        }
    }
}

