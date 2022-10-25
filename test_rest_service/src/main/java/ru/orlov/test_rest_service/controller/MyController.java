package ru.orlov.test_rest_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.orlov.test_rest_service.model.Request;
import ru.orlov.test_rest_service.model.Response;
import ru.orlov.test_rest_service.service.MyLogger;
import ru.orlov.test_rest_service.service.MyService;
import ru.orlov.test_rest_service.service.ServiceForward;

@RestController
public class MyController {


    private final MyService myService;


    private final ServiceForward serviceForward;

    private final Logger logger = LoggerFactory.getLogger(MyController.class);

    private final MyLogger mylogger;


    @Autowired
    public MyController(@Qualifier("ModifyErrorMessage") MyService myService, ServiceForward serviceForward, MyLogger mylogger) {
        this.myService = myService;

        this.serviceForward = serviceForward;

        this.mylogger = mylogger;
    }

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
        Response forward = serviceForward.send(response);
        Response change_uid =myService.exececute(response);
        mylogger.createLog(logger);


        return new ResponseEntity<>(change_uid, HttpStatus.OK);

    }
}
