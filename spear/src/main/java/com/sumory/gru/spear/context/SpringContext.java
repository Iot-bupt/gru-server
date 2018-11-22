package com.sumory.gru.spear.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SpringContext {
    private static ApplicationContext applicationContext;

    public static void initSpringContext() {
        String[] configs = { "classpath*:spring/applicationContext-service.xml" };
        applicationContext = new FileSystemXmlApplicationContext(configs);
    }

    /**
     * 通过名字获取bean
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {

        try{
            return applicationContext.getBean(beanName);
        }catch(BeansException e){
            return null;
        }

    }
}
