package com.nf.spring.advice;

import com.nf.spring.vo.ResponseVo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;

@RestControllerAdvice
public class SystemControllerAdvice {
    @ExceptionHandler(ValidationException.class)
    public ResponseVo handlerV(ValidationException va){
        ResponseVo responseVO = new ResponseVo();
        responseVO.setMsg(va.getMessage());
        responseVO.setCode("500");
        responseVO.setData(null);
        return responseVO;
    }
}
