package com.example.apidemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.net.URI;
import javax.ws.rs.QueryParam;

@RestController
@Slf4j
public class DemoController {

    /* TODO: implement a demo service the controller can use
    @Autowired
    private DemoService demoService;
    */
    
    @Hidden
    @GetMapping
    ResponseEntity<Void> redirect() {
        return ResponseEntity.status(HttpStatus.FOUND)
            .location(URI.create("/swagger-ui.html"))
            .build();
    }

    @ApiOperation(value = "Return demo API response")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success, Results found"),
        @ApiResponse(responseCode = "204", description = "Success, no content"),
        @ApiResponse(responseCode = "404", description = "Service not found"),
        @ApiResponse(responseCode = "400", description = "Invalid Request"),
        @ApiResponse(responseCode = "500", description = "An unexpected error occurred")})
    @RequestMapping(value = "/getDemoApiResponse", method = RequestMethod.GET)
    public ResponseEntity getDemoApiResponse(@RequestParam(required = true) @QueryParam("value") String value) {
        
        if (value.isBlank()) {
            return new ResponseEntity<>("Not a valid request value. Cannot be empty.", HttpStatus.BAD_REQUEST);
        } else {
            //demo response object
            String response = "Hello World!";

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
