import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Translator translator = new Translator();

        translator.addWord("hello", "привіт");
        translator.addWord("world", "світ");
        translator.addWord("i", "я");
        translator.addWord("present", "представляю");
        translator.addWord("you", "вам");
        translator.addWord("my", "мою");
        translator.addWord("sixth", "шосту");
        translator.addWord("lab", "лабораторну");
        translator.addWord("work", "роботу");

        while (true) {
            System.out.println("\nСписок можливих дій:");
            System.out.println("1 - Додати слово до словника");
            System.out.println("2 - Перекласти речення");
            System.out.println("3 - Показати весь словник");
            System.out.println("\nОберіть дію: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.println("Введіть слово англійською: ");
                    String english = scanner.nextLine();

                    System.out.println("Введіть переклад українською: ");
                    String ukrainian = scanner.nextLine();

                    translator.addWord(english, ukrainian);
                    System.out.println("Додано переклад: " + english + " = " + ukrainian);
                    break;

                case "2":
                    System.out.println("Введіть речення англійською: ");
                    String sentence = scanner.nextLine();
                    String translation = translator.translate(sentence);
                    System.out.println("Англійською: " + sentence);
                    System.out.println("Українською: " + translation);
                    break;

                case "3":
                    translator.printDictionary();
                    break;
            }
        }
    }
}