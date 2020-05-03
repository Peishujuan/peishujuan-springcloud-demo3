package com.peishujuan.springcloud.jpa.tests.common;

/**
 * 软删除的枚举类
 */
public enum DeleteEnum {
    YES(1), No(0);

    private Integer value;

    DeleteEnum(Integer value) {
        this.value = value;
    }
}
