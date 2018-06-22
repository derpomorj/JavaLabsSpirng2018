package Java.Labs.ThirdLab;


import java.math.BigInteger;

/*3. Создать 2 потока, один из которых генерирует случайным образом 2  числа Фибоначчи  в разделенные между потоками переменные ,
а другой считывает эти числа , и выводит на печать сумму этих чисел.
Задать с помощью параметра сколько раз выполняется цикл в программе.
Выводить записываемые числа   для первого потока и сумму для второго потока. Выполнить задание   с использованием конструкции synchronized .
Не использовать в этом задании флаги для синхронизации потоков, а только методы wait и notify.
Также не использовать любые задержки для потоков после начала их работы в виде методов sleep, yield или wait c параметром.
(
Числа Фибоначчи — элементы числовой последовательности
1, 2, 3, 4, 5, 6, 7, 8,  9,  10, 11, 12, 13,  14,  15,  16   17   18    19    20    21
0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765,  …
в которой каждое последующее число равно сумме двух предыдущих чисел. )*/

public class ThirdLab {
    private static volatile BigInteger firstNumber;
    private static volatile BigInteger secondNumber;
    private static int counter = 1;
    final private static Object lock = new Object();

    private static class SumFibonacci implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < counter; i++) {
                synchronized (lock) {
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Sum = " + firstNumber.add(secondNumber));
                }
            }
        }
    }


    private static class GetFibonacci implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                new Thread(new SumFibonacci()).start();
                for (int c = 0; c < counter; c++) {
                    int random_number1 = (int) (Math.random() * 10) + 1;
                    int random_number2 = (int) (Math.random() * 10) + 1;
                    BigInteger first;
                    BigInteger second;
                    if (random_number1 > random_number2) {
                        int temp = random_number1;
                        random_number1 = random_number2;
                        random_number2 = temp;
                    }
                    BigInteger n0 = new BigInteger("0");
                    BigInteger n1 = new BigInteger("1");
                    BigInteger n2 = new BigInteger("1");
                    if (random_number1 <= 3) {
                        if (random_number1 == 1) {
                            first = new BigInteger("0");
                        } else {
                            first = new BigInteger("1");
                        }
                    } else {
                        for (int i = 3; i <= random_number1; i++) {
                            n2 = n1.add(n0);
                            n0 = n1;
                            n1 = new BigInteger(n2.toString());
                        }
                        first = new BigInteger(n2.toString());
                    }
                    if (random_number2 <= 3) {
                        if (random_number2 == 1) {
                            second = new BigInteger("0");
                        } else {
                            second = new BigInteger("1");
                        }
                    } else {
                        for (int i = random_number1 + 1; i <= random_number2; i++) {
                            n2 = n1.add(n0);
                            n0 = n1;
                            n1 = new BigInteger(n2.toString());
                        }
                        second = new BigInteger(n2.toString());
                    }
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Fibonacci Numbers:");
                    System.out.println(first);
                    System.out.println(second);
                    firstNumber = first;
                    secondNumber = second;
                    lock.notify();
                }
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Wrong Arguments number!");
            System.exit(1);
        }
        try {
            counter = Integer.parseInt(args[0]);
        } catch (NumberFormatException ex) {
            System.err.println("Wrong Argument!" + args[0]);
            System.exit(1);
        }
        Thread thread1 = new Thread(new GetFibonacci());
        thread1.start();
    }
}
