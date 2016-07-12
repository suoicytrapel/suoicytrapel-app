package lepartycious.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import lepartycious.models.User;
import lepartycious.services.implementations.UserService;

/**
 *
 *  REST service for users.
 *
 */

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public String getUserInfo(Principal principal) {

        User user = userService.findUserByUsername(principal.getName());
        return user.getUsername();
    }

   /* @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT)
    public void updateUserMaxCaloriesPerDay(Principal principal, @RequestBody Long newMaxCalories) {
        userService.updateUserMaxCaloriesPerDay(principal.getName(), newMaxCalories);
    }*/


    /*@ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST)
    public void createUser(@RequestBody NewUserDTO user) {
        userService.createUser(user.getUsername(), user.getEmail(), user.getPlainTextPassword());
    }*/


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> errorHandler(Exception exc) {
        LOGGER.error(exc.getMessage(), exc);
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
