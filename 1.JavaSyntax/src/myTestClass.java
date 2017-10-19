import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by Max on 28.03.2017.
 */
public class myTestClass {
//    @JsonAutoDetect
//    static class Cat {
//        public String name;
//        public int age;
//        public int weight;
//        Cat(){ }

    @JsonAutoDetect
        static class Cat {
        @JsonProperty("alias") public String name;
        public int age;
        @JsonIgnore
        public int weight;

        Cat() {
        }
    }


    public static void main(String[] args) throws IOException {
        //создание объекта для сериализации в JSON
        Cat cat = new Cat();
        cat.name = "Murka";
        cat.age = 5;
        cat.weight = 4;

        //писать результат сериализации будем во Writer(StringWriter)
        StringWriter writer = new StringWriter();

        //это объект Jackson, который выполняет сериализацию
        ObjectMapper mapper = new ObjectMapper();

        // сама сериализация: 1-куда, 2-что
        mapper.writeValue(writer, cat);

        //преобразовываем все записанное во StringWriter в строку
        String result = writer.toString();
        System.out.println(result);



        String jsonString = result;
        StringReader reader = new StringReader(jsonString);

        ObjectMapper mapper1 = new ObjectMapper();

        Cat cat1 = mapper.readValue(reader, Cat.class);
        System.out.println("name: " + cat1.name + " age: " + cat1.age + " weight: " + cat1.weight);
    }
}
