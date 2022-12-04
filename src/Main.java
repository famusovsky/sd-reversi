import java.util.Scanner;

/**
 * Главный класс программы.
 */
public class Main {
    /**
     * Сканер для ввода данных из консоли.
     */
    public static Scanner scanner = new Scanner(System.in);

    /**
     * Точка входа в программу.
     *
     * @param args Аргументы командной строки.
     */
    public static void main(String[] args) {
        while (true) {
            Game.start();
            System.out.println("Хотите сыграть ещё раз? (Y/N)");
            while (true) {
                String input = scanner.nextLine();
                switch (input) {
                    case "Y":
                        break;
                    case "N":
                        scanner.close();
                        return;
                    default:
                        System.out.println("Неверный ввод. Попробуйте ещё раз.");
                        continue;
                }
                break;
            }
        }
    }
}