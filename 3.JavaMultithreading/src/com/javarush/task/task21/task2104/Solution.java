package com.javarush.task.task21.task2104;

import java.util.HashSet;
import java.util.Set;

/* 
Equals and HashCode
В классе Solution исправить пару методов equals/hashCode в соответствии с правилами реализации этих методов
(детали уточни у своего любимого поисковика).
Обе строки first и last должны принимать участие в сравнении с помощью метода equals и вычислении hashcode.
Метод main не участвует в тестировании.
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Solution solution = (Solution) o;
//
//        if (!first.equals(solution.first)) return false;
//        return last.equals(solution.last);
//
//    }

    public boolean equals(Object n) {
        if (n == null) return false;
        if (n == this) return true;
        if (this.hashCode() != n.hashCode()) return false;
        if (!(n instanceof Solution)) return false;
            Solution solution = (Solution) n;
//            if(n.first.equals(this.first) && n.last.equals(this.last) && n.first!=null && n.last!=null && last!=null && first!=null)
//                return true;
//            else return false;
//            if (first == null || n.first == null || last == null || n.last == null) return false;
//            if (!first.equals(n.first) || !last.equals(n.last)) return false;
//        return true;
//        if (first.equals(last) && n.first.equals(solution.last) && first.equals(solution.first)) return true;
        if (first != null ? !first.equals(solution.first) : solution.first != null) return false;
        return last != null ? last.equals(solution.last) : solution.last == null;
//            return true;
//        }
//            else return false;
        }


    public int hashCode() {
        int res1 = first != null ? first.hashCode() : 0;
        int res2 = last!= null ? last.hashCode() : 0;

        return 31 * res1+ res2;
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Donald", "Duck"));
        System.out.println(s.contains(new Solution("Donald", "Duck")));
    }
}
