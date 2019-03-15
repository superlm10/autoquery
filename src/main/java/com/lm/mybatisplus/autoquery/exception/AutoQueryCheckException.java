package com.lm.mybatisplus.autoquery.exception;

import com.lm.mybatisplus.autoquery.enums.AutoExceptionEnum;
import lombok.Getter;

/**
 * @author LM
 * @description:
 * @date 2019/3/15 11:53
 */
@Getter
public class AutoQueryCheckException extends RuntimeException {

    private int code;

    private String message;

    public AutoQueryCheckException(AutoExceptionEnum autoExceptionEnum) {
        super(autoExceptionEnum.getMessage());
        this.code = autoExceptionEnum.getCode();
        this.message = autoExceptionEnum.getMessage();
    }


}
