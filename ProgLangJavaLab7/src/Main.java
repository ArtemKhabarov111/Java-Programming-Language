import java.util.Arrays;
import java.util.Comparator;

// Варіант 2: Визначити слово, в якому кількість різних символів мінімальна.
// Якщо таких слів декілька, то визначити перше з таких. На вхід поступає рядок із словами.
// Словом вважається послідовність символів між пробілами та символами «white space». На виході – масив String.
public class Main {
    public static void main(String[] args) {
        String[] examples = {
                "Ой у лузі червона калина",
                "!@# !@ !",
                "12345 6789 /|\\",
                "ab \t\t\tcd        ddd",
                "abc" + " cde" + " fgh",
                "\nранок \tдень ,вечір",
                "qwerty"
        };

        Arrays.stream(examples).forEach(example -> {
            String[] result = findWord(example);
            System.out.println("Тестовий рядок: " + example);
            System.out.println("Слово, в якому кількість різних символів мінімальна: " + Arrays.toString(result) + "\n");
        });
    }

    public static String[] findWord(String input) {
        String result = Arrays.stream(input.trim().split("\\s+"))
                .min(Comparator.comparingInt(word -> (int) word.chars().distinct().count())).orElse("");

        return new String[]{result};
    }
}