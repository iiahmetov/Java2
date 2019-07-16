package Lesson_1.Obstacles;

import Lesson_1.Competitors.Competitor;

public class WaterPool extends Obstacle {

    int length;

    public WaterPool(int length) {
        this.length = length;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.swim(length);
    }
}
