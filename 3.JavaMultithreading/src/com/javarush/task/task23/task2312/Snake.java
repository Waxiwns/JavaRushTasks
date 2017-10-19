package com.javarush.task.task23.task2312;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 07.03.2017.
 */
/*Кусочки змеи» мы описали, теперь займемся самой змеей.

У змеи должен быть список кусочков(SnakeSection) и голова.
Пусть головой будет просто самый первый кусочек (с номером 0).

У змеи также есть направление движения — сторона, куда она движется по умолчанию.
А еще есть состояние — жива змея или мертва.
Игра заканчивается тогда, когда змея мертва.

Надо:
а) Добавить в класс Snake поле sections типа List<SnakeSection>.
б) Добавить поле isAlive типа boolean.
в) Добавить поле direction типа SnakeDirection.
г) Для всех полей добавить getter’ы, а для direction еще и setter.

Примечание:
Для полей логического типа геттер принято писать не как getValue(), а как isValue() — это улучшает читабельность кода.
Только не пиши isIsAlive(). Одного is будет вполне достаточно.

Примечание 2:
Все поля создаваемые в этом задании должны быть приватными, а методы — публичными.

*/
// Змея которая ползает
public class Snake {
    private List<SnakeSection> sections = new ArrayList<>();
    private boolean isAlive;
    private SnakeDirection direction;

    public List<SnakeSection> getSections() {
        return sections;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public SnakeDirection getDirection() {
        return direction;
    }

    public void setDirection(SnakeDirection direction) {
        this.direction = direction;
    }
    public Snake(int x, int y){
        SnakeSection head = new SnakeSection(x, y);
        sections.add(head);
        isAlive = true;
        SnakeSection section = null;
    }

    public int getX(){
        return sections.get(0).getX();
    }
    public int getY(){
        return sections.get(0).getY();
    }
    public void move(){
        if (!isAlive) return;
            if (direction == SnakeDirection.UP)
                move(0, -1);
            else if (direction == SnakeDirection.RIGHT)
                move(1, 0);
            else if (direction == SnakeDirection.DOWN)
                move(0, 1);
            else if (direction == SnakeDirection.LEFT)
                move(-1, 0);

    }
    public void move(int x, int y){
    }
    public void checkBorders(SnakeSection head){
        if (head.getX() < 0 || head.getX() >= Room.game.getWidth() || head.getY() < 0 || head.getY() >= Room.game.getHeight())
            isAlive = false;
    }
    public void checkBody(SnakeSection head){
        if (sections.contains(head))
            isAlive = false;
    }
}
