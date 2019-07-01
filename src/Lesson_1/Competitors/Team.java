package Lesson_1.Competitors;

public class Team {
    String name;
    public Competitor[] competitors = new Competitor[4];

    public Team(String name, Competitor competitor1, Competitor competitor2, Competitor competitor3, Competitor competitor4) {
        this.name = name;

        Competitor[] temp = {competitor1, competitor2, competitor3, competitor4};
        this.competitors = temp;
    }

    public void info(){
        System.out.println("Команда " + name + " в составе:");
        for (Competitor c:competitors
             ) {
            c.teamInfo();
        }
    }

    public void showResults(){
        for (Competitor c:competitors) {
            c.info();
        }
    }


}
