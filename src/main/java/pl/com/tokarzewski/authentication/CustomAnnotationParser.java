package pl.com.tokarzewski.authentication;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class CustomAnnotationParser {
    private Logger logger = Logger.getLogger(CustomAnnotationParser.class);
    @Autowired
    private ListableBeanFactory factory;

    @PostConstruct
    void parse() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        String[] beanNamesForAnnotation = factory.getBeanNamesForAnnotation(Controller.class);
        for (String bean :
                beanNamesForAnnotation) {
            logger.info(bean);
            Method[] methods =
                    factory.getType(bean).getMethods();
            for (Method method :
                    methods) {


                for (Parameter parameter :
                        method.getParameters()) {
                    if (parameter.isAnnotationPresent(CurrentUser.class)) {
                        logger.info(method.getName());
                        logger.info(parameter.getName());
                    }
                }
            }

        }
    }
}
