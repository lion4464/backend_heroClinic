package com.example.demo.generic;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public interface GenericEntity<T> extends Serializable {
    T getId();

    boolean isDeleted();

    default List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));
        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }
        return fields;
    }

    @SuppressWarnings("unchecked")
    default <S> S mergeObjects(S input)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> clazz = input.getClass();
        LinkedList<Field> fieldLinkedList = new LinkedList<>();
        List<Field> fields = getAllFields(fieldLinkedList, clazz);
        Object returnValue = clazz.getDeclaredConstructor().newInstance();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value1 = field.get(input);
            Object value2 = field.get(this);
            Object value = (value1 != null) ? value1 : value2;
            if (!Modifier.isFinal(field.getModifiers()))
                field.set(returnValue, value);
        }
        return (S) returnValue;
    }
}