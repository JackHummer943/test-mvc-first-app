package ru.orlov.test_rest_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.orlov.test_rest_service.model.Request;
import ru.orlov.test_rest_service.model.Response;
import ru.orlov.test_rest_service.service.Executor;


@RestController
public class MyController {

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@RequestBody Request request) {
        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(request.getSystemTime())
                .code("success")
                .errorCode("")
                .errorMessage("")
                .build();
        if(response!=null){
            System.out.println(request.getUid());
            System.out.println(response.getCode());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
