package Java.Labs.SecondLab;

public class Dancer extends Artist implements Performing {
    void dance() {
        System.out.println("Dancer:dance()");
    }

    void workForAudience() {
        System.out.println("Dancer:workForAudience()");
    }

    @Override
    public void performAtConcert() {
        System.out.println("Dancer:Performing:performAtConcert()");
    }
}
