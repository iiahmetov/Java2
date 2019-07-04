package Lesson_1.Competitors;

import Lesson_1.Competitors.Competitor;

public class Human implements Competitor {

    String name;
    int maxRunDistance;
    int maxSwimDistance;
    int maxJumpHeight;
    boolean active;

    public Human(String name, int maxRunDistance, int maxSwimDistance, int maxJumpHeight) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxSwimDistance = maxSwimDistance;
        this.maxJumpHeight = maxJumpHeight;
        this.active = true;
    }

    public Human(String name) {
        this(name, 400,25,1);
    }

    @Override
    public void run(int dist) {
        if (dist <= maxRunDistance) {
            System.out.println("Участник " + name + " успешно справился с забегом на " + dist + " метров!");
        } else {
            System.out.println(name +", участник забега на " + dist + " метров, словил МакКонахи и сошёл с дистанции.");
            active = false;
        }
    }

    @Override
    public void swim(int dist) {
        if (dist <= maxSwimDistance) {
            System.out.println(name + ", участник заплыва на " + dist + " метров, успешно преодолел дистанцию и вышел сухим из воды!");
        } else {
            System.out.println(name +", участник заплыва на " + dist + " метров, нырнул, но где он? Зовите спасателей, он не справился!");
            active = false;
        }
    }

    @Override
    public void jump(int height) {
        if (height <= maxJumpHeight) {
            System.out.println("Участник " + name + ", подошёл к " + height + " метровому препятствию и с лёгкостью его преодолел!");
        } else {
            System.out.println("Участник " + name + ", подошёл к " + height + " метровому препятствию...умный гору обойдёт. Препятствие не взято!");
            active = false;
        }
    }

    @Override
    public boolean isOnDistance() {
        return active;
    }

    @Override
    public void info() {
        if (active == true) {
            System.out.println("Участник " + name + " на дистанции!");
        } else System.out.println("Участник " + name + " выбыл!");
    }

    @Override
    public void teamInfo() {
        System.out.println(name);
    }
}
