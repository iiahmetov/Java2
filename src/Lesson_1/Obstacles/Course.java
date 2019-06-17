package Lesson_1.Obstacles;

import Lesson_1.Competitors.Competitor;
import Lesson_1.Competitors.Team;

public class Course {

    Obstacle[] obstacles = new Obstacle[3];

    public Course(Obstacle obstacle1, Obstacle obstacle2, Obstacle obstacle3) {
        Obstacle[] temp = {obstacle1, obstacle2, obstacle3};
        this.obstacles = temp;
    }

    public void doIt(Team team){
        for (Competitor c: team.competitors) {
            for (Obstacle o:obstacles) {
                o.doIt(c);
                if (!c.isOnDistance()) {
                    break;
                }
            }
        }

    }
}
