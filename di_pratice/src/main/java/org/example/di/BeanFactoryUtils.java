package org.example.di;

import org.example.annotation.Inject;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.util.Set;

public class BeanFactoryUtils {
    public static Constructor<?> getInjectedConstructor(Class<?> clazz) {
        // Inject annotation만 붙은 생성자만 가지고 온다는 의미
        Set<Constructor> injectedConsturctors = ReflectionUtils.getAllConstructors(clazz, ReflectionUtils.withAnnotation(Inject.class));
        if (injectedConsturctors.isEmpty()) {
            return null;
        }
        return injectedConsturctors.iterator().next();
    }
}
