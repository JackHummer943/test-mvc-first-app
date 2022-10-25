package ru.orlov.test_rest_service.service;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class CrLog implements MyLogger{


    @Override
    public void createLog(Logger logger) {
        logger.info("Это лог инфы");
        logger.error("Это лог ошибки");


    }
}
