/*4. Создать многопотоковое приложение с 2 параметрами. Количество потоков "Рабочий" задано параметром.
    Первый рабочий вытачивает деталь и кладет ее на конвейер. Каждый следующий рабочий берет ее с конвейера,
   модифицирует и снова отправляет на конвейер следующему работнику.
        Количество деталей тоже задается параметром. Каждый рабочий не берет следующую деталь, пока не закончил с предыдущей.
        Использовать ограничения из задания 3.
        Выводить на дисплей название детали и имя рабочего.Вывод может быть например такой:
        деталь1- рабочий1
        деталь2 -рабочий1
        деталь3 -рабочий1
        деталь1- рабочий2
        деталь1 -рабочий3
        деталь2 -рабочий2
        деталь1 -рабочий4
        деталь2 -рабочий3
        ................*/

package Java.Labs.FifthLab.Functional.FourthLab;

import Java.Labs.FifthLab.Functional.ThirdLab.ThirdLab;

import java.util.ArrayList;

public class FourthLab {

    private static ArrayList<Worker> workers;
    private static int detailsCount = 3;
    private static int workersCount = 3;

    static class FirstWorker implements Runnable {
        private int currentDetail = 1;

        @Override
        public void run() {
            Thread lastThread = null;
            if (workersCount != 1) {
                workers = new ArrayList<Worker>(workersCount);
                for (int i = 2; i < workersCount + 1; i++) {
                    workers.add(new Worker(i));
                }
                for (int i = 0; i < workersCount - 1; i++) {
                    lastThread = new Thread(workers.get(i));
                    lastThread.start();
                }
                while (currentDetail <= detailsCount) {
                    System.out.println(Thread.currentThread().getName() + " 1 worker - detail " + currentDetail);
                    new Thread(()->{
                        workers.get(0).incDetailsNumver();
                    }).run();
                    currentDetail++;
                }
            } else {
                while (currentDetail <= detailsCount) {
                    System.out.println("1 worker - detail " + currentDetail);
                    currentDetail++;
                }
            }
            if (lastThread != null){
                try {
                    lastThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Worker implements Runnable {
        private int order;
        private volatile int details = 0;
        private int currentDetail = 1;

        Worker(int order) {
            this.order = order;
        }

        @Override
        public void run() {
            while (currentDetail <= detailsCount) {
                synchronized (this) {
                    if (details < currentDetail) {
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + " " + order + " worker - detail " + currentDetail);
                    currentDetail++;
                    if (order != workersCount) {
                        new Thread(()->{
                            workers.get(order - 1).incDetailsNumver();
                        }).run();
                    }
                }
            }
        }

        public void incDetailsNumver() {
            synchronized (this) {
                ++details;
                this.notify();
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Wrong Arguments number!!");
            return;
           // System.exit(1);
        }
        try {
            workersCount = Integer.parseInt(args[0]);
            if (workersCount <= 0) {
                System.err.println("Wrong Argument!" + args[0]);
                return;
             //   System.exit(2);
            }
            detailsCount = Integer.parseInt(args[1]);
            if (detailsCount <= 0) {
                System.err.println("Wrong Argument!" + args[1]);
              //  System.exit(2);
                return;
            }
        } catch (NumberFormatException exception) {
            System.err.println("Wrong Arguments");
            System.err.println(exception.toString());
           // System.exit(1);
            return;
        }
        Thread firstWorker = new Thread(new FirstWorker());
        firstWorker.start();
        try {
            firstWorker.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
