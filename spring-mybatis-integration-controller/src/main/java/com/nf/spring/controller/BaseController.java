package com.nf.spring.controller;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.annotation.Inherited;
import java.util.List;

/**
 * @author Sam
 */
public class BaseController {

    /**
     * 用于填充字段的错误信息
     * @param bindingResult 错误信息
     * @param redirectAttributes
     */
    public void fillFieldError(BindingResult bindingResult, RedirectAttributes redirectAttributes){
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            redirectAttributes.addFlashAttribute(error.getField(),error.getDefaultMessage());
        }
    }
}
