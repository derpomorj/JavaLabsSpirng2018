/*1. Создать приложение с 2 параметрами, которое вычислит количество лет ,
за которые сумма денег,положенная в банк, должна удвоится. В качестве параметров задаются
любые  неотрицательные  числа. 1-ое число - количество денег, 2-ое число-годовой  процент.
Учесть, что проценты начисляются раз в месяц и капитализируются.Сосчитать и вывести на дисплей
результат подсчета.*/

package Java.Labs.FirstLab;

public class FirstLab {
    private static int countMonth(double percents){
        if (percents >= 1200){
            return 1;
        } else {
            return (int) (Math.log(2) / Math.log1p(percents / 1200)) + 1;
        }
    }

    public static void main(String[] args) {
        if (args.length != 2){
            System.err.println("Wrong Arguments number!!");
            //System.exit(1);
            return;
        }
        try {
            double money = Double.parseDouble(args[0]);
            if (money <= 0){
                System.err.println("Wrong Argument!: " + args[0]);
                //System.exit(2);
                return;
            }
            double percent = Double.parseDouble(args[1]);
            if (percent <= 0){
                System.err.println("Wrong Argument!: " + args[0]);
                //System.exit(2);
                return;
            }
            int month = countMonth(percent);
            System.out.println(month/12 + " year(s) " + month%12 + " month(s)");
        } catch (NumberFormatException exception){
            System.err.println("Wrong Arguments");
            System.err.println(exception.toString());
            //System.exit(1);
        }

    }
}
