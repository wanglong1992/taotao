package com.taotao.common.controller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

@ControllerAdvice
public class TaoTaoJsonpResponseBodyAdvice extends AbstractJsonpResponseBodyAdvice {
    public TaoTaoJsonpResponseBodyAdvice() {
        super("callback");
    }
}
