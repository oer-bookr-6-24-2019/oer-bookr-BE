package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.User;
import com.lambdaschool.starthere.models.UserRoles;
import com.lambdaschool.starthere.services.RoleService;
import com.lambdaschool.starthere.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.ArrayList;

@RestController
public class OpenController
{
    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PostMapping(value = "/createnewuser", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewUser(HttpServletRequest request, @Valid
    @RequestBody
            User newuser) throws URISyntaxException
    {

        logger.trace(request.getRequestURI() + " accessed");

        ArrayList<UserRoles> newRoles = new ArrayList<>();
        newRoles.add(new UserRoles(newuser, roleService.findByName("user")));
        newuser.setUserRoles(newRoles);

        String temp = newuser.getPasswordRaw();
        newuser =  userService.save(newuser); //passwordRaw is nullified


        String loginUrl
                = "https://sgs-lambda-bookr.herokuapp.com//oauth/token";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        headers.add("Authorization", "Basic bGFtYmRhLWNsaWVudDpsYW1iZGEtc2VjcmV0");
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("grant_type", "password");
        map.add("username", newuser.getUsername());
        map.add("password", temp);
        HttpEntity<MultiValueMap<String, String>> request2 = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(
                loginUrl, request2 , String.class);




/*        // set the location header for the newly created resource - to another controller!
        HttpHeaders responseHeaders = new HttpHeaders();
        URI createUserURI = ServletUriComponentsBuilder
                .fromUriString(request.getServerName() + ":" + request.getLocalPort() + "/users/user/{userId}")
                .buildAndExpand(newuser.getUserid()).toUri();
        responseHeaders.setLocation(createUserURI);*/


        return response;
        //return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

}
