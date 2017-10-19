package com.javarush.task.task33.task3309;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringWriter;

/*
Комментарий внутри xml
Реализовать метод toXmlWithComment, который должен возвращать строку — xml представление объекта obj.
В строке перед каждым тегом tagName должен быть вставлен комментарий comment.
Сериализация obj в xml может содержать CDATA с искомым тегом. Перед ним вставлять комментарий не нужно.
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) throws IOException {
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, obj);
        String xmlString = "";
        if (writer.toString().contains("<" + tagName + ">")) {
            xmlString = writer.toString();
            xmlString = xmlString.replaceAll(("<" + tagName + ">"), ("<!--" + comment + "-->" + "\n" + "<" + tagName + ">"));
        }
        return xmlString;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(Solution.toXmlWithComment(new Solution.First(), "second", "it's a comment"));

    }

    @XmlRootElement(name = "first")
    public static class First {
        @XmlElement(name = "second")
        public String item1 = "some string";
        @XmlElement(name = "second")
        public String item2 = "need CDATA because of <second>";
        @XmlElement(name = "second")
        public String item3 = "";
        @XmlElement(name = "third")
        public String item4;
        @XmlElement(name = "forth")
        public Second[] third = new Second[]{new Second()};
        @XmlElement(name = "fifth")
        public String item5 = "need CDATA because of \"";
    }

    public static class Second {
        @XmlElement(name = "second")
        public String item1 = "some string";
        @XmlElement(name = "second")
        public String item2 = "need CDATA because of <second>";
    }
}
