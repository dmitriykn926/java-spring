package com.dy.dev.annotations.bpp;

import com.dy.dev.annotations.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class TransactionBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware {

    private final Map<String, Class<?>> beanClassMap = new ConcurrentHashMap<>();

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

//        Arrays.stream(bean.getClass().getDeclaredFields())
//                .filter(f -> f.isAnnotationPresent(Transaction.class))
//                .forEach(f -> {
//                    Object beanToInject = applicationContext.getBean(f.getName());
//                    ReflectionUtils.makeAccessible(f);
//                    ReflectionUtils.setField(f, bean, beanToInject);
//                });

        if (bean.getClass().isAnnotationPresent(Transaction.class)) {
            beanClassMap.put(beanName, bean.getClass());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = beanClassMap.get(beanName);
        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(),
                    (proxy, method, args) -> {
                        try {
                            log.info("Transaction is opened");
                            return method.invoke(bean, args);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        } finally {
                            log.info("Transaction is closed");
                        }
                    });
        }
        return bean;
    }
}
