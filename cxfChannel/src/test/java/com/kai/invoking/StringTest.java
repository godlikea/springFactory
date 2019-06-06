package com.kai.invoking;

import org.junit.Test;

/**
 * @author ggk
 * @date 2019/6/1 0001 上午 11:11
 */
public class StringTest {

    @Test
    public void strTest(){
        String a="0009";
        System.out.println(Integer.valueOf(a));
    }
    @Test
    public void strSubTest(){
        String maxAutoCode="HT-20190601001";
       String numberStr = maxAutoCode.substring(maxAutoCode.indexOf("-")+9,
                maxAutoCode.length());
        System.out.println(numberStr);
    }
}
