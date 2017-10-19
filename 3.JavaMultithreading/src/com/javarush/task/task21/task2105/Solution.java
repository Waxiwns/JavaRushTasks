package com.javarush.task.task21.task2105;

import java.util.HashSet;
import java.util.Set;

/* 
Исправить ошибку. Сравнение объектов
Сравнение объектов Solution не работает должным образом. Найти ошибку и исправить.
Метод main не участвует в тестировании.
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    @Override
    public int hashCode() {
        int res1 = first != null ? first.hashCode() : 0;
        int res2 = last != null ? last.hashCode() : 0;
        return 31 * res1 + res2;
    }

    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (this.hashCode() != o.hashCode()) return false;
        if (!(o instanceof Solution))
            return false;
        Solution n = (Solution) o;
        if (first != null ? !first.equals(n.first) : n.first != null ) return false;
        return last != null ? last.equals(n.last) : n.last == null; //) return false;
//        if (first != null ? !first.equals(n.first) : n.first != null) return false;
//        return last != null ? last.equals(n.last) : n.last == null;
//        return n.first.equals(first) && n.last.equals(last);
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Mickey", "Mouse"));
        System.out.println(s.contains(new Solution("Mickey", "Mouse")));

        Solution solution1 = new Solution("1", "11");
        Solution solution2 = new Solution("1", "11");

        System.out.println(solution1.equals(solution2));
        System.out.println(solution1.hashCode());
        System.out.println(solution2.hashCode());
    }
}
