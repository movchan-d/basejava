package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume();
        Class<? extends Resume> resumeClass = r.getClass();
        Field field = resumeClass.getDeclaredFields()[0];
        System.out.println(field.getName());

        field.setAccessible(true);
        System.out.println(field.get(r));
        field.set(r, "new uuid");

        // Invoke r.toString via reflection (вызвать toString через отражение - не r.toString )
        Method method = resumeClass.getMethod("toString");
        Object result = method.invoke(r);
        System.out.println(result);
    }
}
