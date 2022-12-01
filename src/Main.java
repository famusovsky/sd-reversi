import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            Game.start();
            Game.play();
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