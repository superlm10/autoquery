package com.lm.mybatisplus.autoquery.enums;

import lombok.Getter;

/**
 * @author LM
 * @description:
 * @date 2019/3/15 11:57
 */
@Getter
public enum AutoExceptionEnum {

    /**
     * 无AUTO-QUERY注解
     */
    AUTO_QUERY_NOT(400, "实体上无AUTO-QUERY注解, 请检查"),
    ;

    private int code;

    private String message;

    AutoExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
