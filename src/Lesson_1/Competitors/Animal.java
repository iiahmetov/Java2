package Lesson_1.Competitors;

public class Animal implements Competitor {

    String type;
    String name;
    int maxRunDistance;
    int maxSwimDistance;
    int maxJumpHeight;
    boolean onDistance;

    public Animal(String type, String name, int maxRunDistance, int maxSwimDistance, int maxJumpHeight) {
        this.type = type;
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxSwimDistance = maxSwimDistance;
        this.maxJumpHeight = maxJumpHeight;
        this.onDistance = true;
    }

    @Override
    public void run(int dist) {
        if (dist <= maxRunDistance) {
            System.out.println("Участник " + type + " " + name + " успешно справился с забегом на " + dist + " метров!");
        } else {
            System.out.println(type + " " + name +", участник забега на " + dist + " метров, словил МакКонахи и сошёл с дистанции.");
            onDistance = false;
        }
    }

    @Override
    public void swim(int dist) {
        if (dist <= maxSwimDistance) {
            System.out.println(type + " " + name + ", участник заплыва на " + dist + " метров, успешно преодолел дистанцию и вышел сухим из воды!");
        } else {
            System.out.println(type + " " + name +", участник заплыва на " + dist + " метров, нырнул, но где он? Зовите спасателей, он не справился!");
            onDistance = false;
        }
    }

    @Override
    public void jump(int height) {
        if (height <= maxJumpHeight) {
            System.out.println("Участник " + type + " " + name + ", подошёл к " + height + " метровому препятствию и с лёгкостью его преодолел!");
        } else {
            System.out.println("Участник " + type + " " + name + ", подошёл к " + height + " метровому препятствию...умный гору обойдёт. Препятствие не взято!");
            onDistance = false;
        }
    }

    @Override
    public boolean isOnDistance() {
        return onDistance;
    }

    @Override
    public void info() {
        if (onDistance == true) {
            System.out.println("Участник " + type + " " + name + " на дистанции!");
        } else System.out.println("Участник " + type + " " + name + " выбыл!");
    }

    @Override
    public void teamInfo() {
        System.out.println(type + " " + name);
    }
}
