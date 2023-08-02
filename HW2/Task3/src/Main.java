import java.util.Scanner;

class NumberValidator {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Введите первое число: ");
            int num1 = scanner.nextInt();
            checkFirstNumber(num1);

            System.out.print("Введите второе число: ");
            int num2 = scanner.nextInt();
            checkSecondNumber(num2);

            System.out.print("Введите третье число: ");
            int num3 = scanner.nextInt();

            int sum = num1 + num2;
            checkNumberSum(sum);

            System.out.println("Все числа прошли проверку.");
        } catch (NumberOutOfRangeException | NumberSumException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void checkFirstNumber(int num) throws NumberOutOfRangeException {
        if (num > 100) {
            throw new NumberOutOfRangeException("Первое число вне допустимого диапазона");
        }
    }

    public static void checkSecondNumber(int num) throws NumberOutOfRangeException {
        if (num < 0) {
            throw new NumberOutOfRangeException("Второе число вне допустимого диапазона");
        }
    }

    public static void checkNumberSum(int sum) throws NumberSumException {
        if (sum < 10) {
            throw new NumberSumException("Сумма первого и второго чисел слишком мала");
        }
    }
}

class NumberOutOfRangeException extends Exception {
    public NumberOutOfRangeException(String message) {
        super(message);
    }
}

class NumberSumException extends Exception {
    public NumberSumException(String message) {
        super(message);
    }
}
