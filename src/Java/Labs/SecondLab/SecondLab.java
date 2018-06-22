/*
1) Создать приложение с 3 классами-Артист , Циркач, Танцор и  интерфейсами Выступающий , Гастролер.
2) Класс Артист является родительским для классов Циркач и Танцор , Интерфейс Выступающий  является родительским для интерфейса Гастролер.
   Класс Циркач реализует интерфейс Гастролер.  Класс Танцор реализует интерфейс Выступающий .
3) Интерфейс Выступающий  содержит метод "Выступать на концерте", класс Артист содержит метод "Работать для публики",
   Интерфейс Гастролер содержит метод Гастролировать. Класс Циркач содержит метод "Выступать на арене".
   Класс Танцор  содержит метод Танцевать.
   Все методы выводят строку с именем своего класса или интерфейса и именем метода.
   Создать минимальное число объектов для выполнения всех указанных 5 методов.
   и выполнить все эти методы.
   Привести эти объекты к общему типу и опять выполнить все методы
*/
package Java.Labs.SecondLab;

public class SecondLab {
    public static void main(String[] args) {
        Artist artist = new Artist();
        Circus circus = new Circus();
        Dancer dancer = new Dancer();


        artist.workForAudience();
        System.out.println();

        circus.performAtConcert();
        circus.actAtArena();
        circus.tour();
        circus.workForAudience();
        System.out.println();

        dancer.dance();
        dancer.workForAudience();
        dancer.performAtConcert();
        System.out.println();


        Artist castedDanser = (Artist) dancer;
        ((Dancer)(castedDanser)).dance();
        ((Dancer)(castedDanser)).performAtConcert();
        ((Dancer)(castedDanser)).workForAudience();
        System.out.println();

        Artist castedCircus = (Artist) circus;
        ((Circus)(castedCircus)).actAtArena();
        ((Circus)(castedCircus)).performAtConcert();
        ((Circus)(castedCircus)).tour();
        ((Circus)(castedCircus)).workForAudience();
    }
}

