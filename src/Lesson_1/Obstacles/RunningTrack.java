package Lesson_1.Obstacles;

import Lesson_1.Competitors.Competitor;

public class RunningTrack extends Obstacle {

    int length;

    public RunningTrack (int length) {
        this.length = length;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.run(length);
    }

}
