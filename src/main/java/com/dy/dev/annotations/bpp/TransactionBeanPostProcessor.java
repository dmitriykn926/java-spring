package com.dy.dev.annotations.bpp;

import com.dy.dev.annotations.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

@Slf4j
@Component
public class TransactionBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Transaction.class)) {
            return Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(),
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
