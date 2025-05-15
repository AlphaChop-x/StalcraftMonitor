package manakin.ru.stalcraftmonitor.controller;

import manakin.ru.stalcraftmonitor.entity.exceptions.ServiceException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice()
public class DefaultAdvice {

//    @ExceptionHandler(java.lang.Exception.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ServiceException exception(java.lang.Exception e) {
//        return ServiceException.create(e);
//    }
//
//    @ExceptionHandler(ResourceNotFoundException.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ServiceException exception(ResourceNotFoundException e) {
//        return ServiceException.create(e);
//    }

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ServiceException> handleException(DuplicateKeyException e) {
        ServiceException response = new ServiceException(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ServiceException> onIllegalArgumentException(IllegalArgumentException e) {
        ServiceException response = new ServiceException(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
