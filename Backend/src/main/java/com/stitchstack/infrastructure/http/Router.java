package com.stitchstack.infrastructure.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Router {
    private HttpResponses response;
    private String path;
    private List<Route> routes = new ArrayList<>();
    private ObjectMapper mapper = new ObjectMapper();

    public Router(ObjectMapper mapper, String path) {
        this.path = path;
        this.response = new HttpResponses(mapper);
    }

    public void add(String method, String regex, RouteHandler handler) {
        Route r = new Route();
        r.method = method.toUpperCase();
        r.pattern = Pattern.compile(regex);
        r.handler = handler;
        routes.add(r);
    }

    public void handle(HttpExchange exchange) {
        try {
            String method = exchange.getRequestMethod().toUpperCase();
            String path = exchange.getRequestURI().getPath();
            String rel = path.startsWith(path) ? path.substring(path.length()) : path;

            for (Route r : routes) {
                if (!r.method.equals(method)) continue;
                Matcher m = r.pattern.matcher(rel);
                if (m.matches()) {
                    r.handler.handle(exchange, m);
                    return;
                }
            }

            response.error(exchange, 404, "not found");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.error(exchange, 500, "internal server error");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } finally {
            exchange.close();
        }
    }

    private class Route {
        String method;
        Pattern pattern;
        RouteHandler handler;
    }
}
