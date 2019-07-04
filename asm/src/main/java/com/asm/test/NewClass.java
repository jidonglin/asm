package com.asm.test;

import com.asm.test.call.CallMethod;

/**
 * @Author: jidonglin
 * @Date: 2019/7/3 16:51
 */
public class NewClass {
    public String newField;

    public NewClass() {
    }

    public static void newMethod() throws Exception {
        System.out.println("Hello world!");
        CallMethod.methodName("arg1");
    }
}
