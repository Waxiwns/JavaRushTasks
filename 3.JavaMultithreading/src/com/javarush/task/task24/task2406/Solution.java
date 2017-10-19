package com.javarush.task.task24.task2406;

import java.math.BigDecimal;

/* 
Наследование от внутреннего класса
Внутри класса Solution создай 2 внутренних public класса Apt3Bedroom, BigHall.
Унаследуй их от Apartments и Hall.
*/
public class Solution {
    public class Building {
        public class Hall {
            private BigDecimal square;

            public Hall(BigDecimal square) {
                this.square = square;
            }
        }

        public class Apartments {
        }
    }
    public class Apt3Bedroom extends Building.Apartments{
        Apt3Bedroom(Building building){
            building.super();
        }
    }
    public class BigHall extends Building.Hall{
        BigHall(Building building, BigDecimal square){
            building.super(square);
        }
//        public BigHall(BigDecimal square) {
//            super(square);
//        }
    }

    public static void main(String[] args) {

    }
}
