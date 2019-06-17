package Lesson_1;

import Lesson_1.Competitors.*;
import Lesson_1.Obstacles.*;

public class MainClass1 {
    public static void main(String[] args) {

        Team team = new Team ("Простоквашино", new Human("Uncle Фёдор"), new Cat("Матроскин"), new Dog("Шарик"), new Human("Mailman Печкин"));
        Course course = new Course(new RunningTrack(300), new WaterPool(5), new Wall(2));

        team.info();
        course.doIt(team);
        team.showResults();
    }
}
