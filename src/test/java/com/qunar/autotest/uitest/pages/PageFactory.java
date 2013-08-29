package com.qunar.autotest.uitest.pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static java.util.Arrays.asList;

public class PageFactory {

    @Autowired
    private ApplicationContext context;

    public <T extends BasePage> T gotoPage(Class<T> pageClass, Object... args) {
        T page = getPage(pageClass, args);
        page.go();
        return page;
    }

    @SuppressWarnings("unchecked")
	public <T> T getPage(Class<T> pageClass, Object... args) {
        Constructor<?> constructor = pageClass.getConstructors()[0];
        Object[] parameters = getParameters(constructor, args);
        try {
            T page = (T) constructor.newInstance(parameters);
            return page;
        } catch (InstantiationException e) {

        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        }
        return null;
    }
    public void sleep(){
    	try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {

		}
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private Object[] getParameters(Constructor<?> constructor, Object... args) {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        ArrayList result = new ArrayList(parameterTypes.length);
        for (Class<?> parameterType : parameterTypes) {
            if (parameterType.equals(String.class)) continue;
            result.add(context.getBean(parameterType));
        }
        result.addAll(asList(args));
        return result.toArray();
    }

}
