package com.javamastery.spring.core.aop;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * S01 · BeanPostProcessor — механизм, на котором построены @Transactional, @Async, @Cacheable.
 *
 * <p>Для каждого бина, класс которого помечен {@link Timed}, вернуть вместо него JDK dynamic proxy
 * ({@code java.lang.reflect.Proxy}), реализующий все интерфейсы бина. Прокси перед вызовом делегата
 * записывает в {@link CallRegistry} строку "SimpleName.methodName" и затем вызывает оригинальный метод.
 * Бины без @Timed возвращаются как есть.
 *
 * <p>Подсказки: CallRegistry внедри через {@code ObjectProvider} (BPP создаются очень рано);
 * исключения из делегата разворачивай из InvocationTargetException.
 */
// TODO: аннотация
public class TimedBeanPostProcessor implements BeanPostProcessor {

    // TODO: поля и конструктор

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean; // TODO: оберни бины с @Timed в JDK-прокси
    }
}
