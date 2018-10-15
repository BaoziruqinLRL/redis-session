package com.baozi.handler;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

/**
 * @Description: redis的session操作类，主要提供session验证和session保存功能
 * @Author: lirl
 * @Create: 2018-09-19 12:37
 */
public class RedisSessionHandler {

    public static Object getSessionObject(HttpServletRequest request,String key){
        return request.getSession().getAttribute(key);
    }

    public static void setSessionObject(HttpServletRequest request,String key,Object value){
        request.getSession().setAttribute(key,value);
    }

    public static String getSessionId(HttpServletRequest request){
        return request.getSession().getId();
    }

    public static boolean verifyObjectId(HttpServletRequest request, String key, Object value, String propertyName){
        if (value == null){
            return false;
        }
        boolean result = false;
        Field field;
        Field sessionField;
        try {
            field = value.getClass().getDeclaredField(propertyName);
            var sessionValue =  request.getSession().getAttribute(key);
            if (sessionValue != null){
                sessionField = sessionValue.getClass().getDeclaredField(propertyName);
                try {
                    if (sessionField != null && field != null) {
                        field.setAccessible(true);
                        sessionField.setAccessible(true);
                        if (field.get(value).equals(sessionField.get(sessionValue))) {
                            result = true;
                        }
                    }
                }catch (IllegalAccessException ex){
                    ex.printStackTrace();
                }
            }
        }catch (NoSuchFieldException ex){
            ex.printStackTrace();
        }
        return result;
    }
}
