package Java.Labs.SecondLab;

public class Circus extends Artist implements Gastroler {
    void actAtArena() {
        System.out.println("Circus:actAtArena()");
    }

    @Override
    void workForAudience() {
        System.out.println("Circus:workForAudience()");
    }

    @Override
    public void performAtConcert() {
        System.out.println("Circus:Performing:performAtConcert()");
    }

    @Override
    public void tour() {
        System.out.println("Circus:Gastroler:tour()");
    }
}
