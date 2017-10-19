package com.javarush.task.task33.task3304;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.AnnotatedParameterizedType;

/* 
Конвертация из одного класса в другой используя JSON
Два класса имеют одинаковые поля, но не имеют общий суперкласс. Пример, классы First и Second.
Реализовать логику метода convertOneToAnother, который должен возвращать объект класса resultClassObject,
значения полей которого равны значениям полей в объекте one.
Используй объект типа ObjectMapper.
Известно, что у классов есть JSON аннотация, у которой значение property равно имени класса в нижнем регистре.
На примере класса First, это className=»first»
Классы First и Second не участвуют в тестировании, они предоставлены в качестве тестовых данных.


Требования:
1. Метод convertOneToAnother должен возвращать объект класса resultClassObject значения полей которого равны значениям полей объекта one.
2. В методе convertOneToAnother должен быть создан объект типа ObjectMapper.
3. Метод convertOneToAnother должен быть статическим.
4. Метод convertOneToAnother должен быть публичным.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Second s = (Second) convertOneToAnother(new First(), Second.class);
        First f = (First) convertOneToAnother(new Second(), First.class);
        System.out.println(s.getClass());
        System.out.println(f.getClass());
    }

    public static Object convertOneToAnother(Object one, Class resultClassObject) throws IOException {
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(writer, one);

        String oneClass = one.getClass().getSimpleName().toLowerCase();
        String resClass = resultClassObject.getSimpleName().toLowerCase();
        String writerStr = writer.toString().replaceFirst(oneClass, resClass);

        StringReader reader = new StringReader(writerStr);
        ObjectMapper mapper1 = new ObjectMapper();

        return mapper1.readValue(reader, resultClassObject);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME,  property="className")
    @JsonSubTypes(@JsonSubTypes.Type(value=First.class,  name="first"))
    public static class First {
        public int i;
        public String name;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME,  property="className")
    @JsonSubTypes(@JsonSubTypes.Type(value=Second.class, name="second"))
    public static class Second {
        public int i;
        public String name;
    }
}
