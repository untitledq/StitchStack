package com.infrastructure.http;

import com.domain.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.application.*;
import com.domain.ports.*;
import com.infrastructure.persistence.*;

public class AppFactory {
    public Router buildRouter(ObjectMapper mapper) {
        // Repo
        UserRepository userRepo = new JdbcUserRepository();

        // Services
        UserService userService = new UserService();

        // Handler
        UserHandler userHandler = new UserHandler();

        // Router + Routes
        Router router = new Router(mapper, "/api");

        Routes.register(router,
                userHandler
        );

        return router;
    }


}
