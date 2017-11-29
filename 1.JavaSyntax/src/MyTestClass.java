import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Max on 28.03.2017.
 */
public class MyTestClass {

    public static void main(String[] args) throws IOException {
        class User{
            int id;
            String name;

            public User(int id, String name) {
                this.id = id;
                this.name = name;
            }

            public String getName() {
                return name;
            }

            @Override
            public String toString() {
                return "User{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        '}';
            }
        }
        Map<Integer, User> list = new HashMap<>();
//        for (int i = 0; i < 5; i++) {
            list.put(1, new User(1, "name "+1));
            list.put(3, new User(3, "name "+2));
            list.put(5, new User(5, "name "+3));
            list.put(4, new User(4, "name "+4));
            list.put(2, new User(2, "name "+5));
//        }


        List<User> users = list.values().stream().
                sorted((o1, o2) -> o1.getName().compareTo(o2.getName())).
                collect(Collectors.toList());
        System.out.println(users);




//        List<Integer> list = new ArrayList<Integer>();
//        for (int i = 0; i < 10; i++) {
//            list.add(i);
//        }
//
//        List<Integer> stream = list.stream()
//                .filter(i -> i % 2 == 0)
//                .map(i -> i+1)
////                .forEach(System.out::print);
//                .collect(Collectors.toList());
//        stream.forEach(System.out::println);


//        for (int i = 0; i < stream.size(); i++) {
//            System.out.println(stream.get(i));
//        }
    }
}
