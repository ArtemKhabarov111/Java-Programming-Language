import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// Визначити слово, в якому кількість різних символів мінімальна.
// Якщо таких слів декілька, то визначити перше з таких.
public static String[] findWord(String input) {
    // Розбиваємо слова по пробілах
    String[] words = input.trim().split("\\s+");
    String result = null;
    int minUnique = Integer.MAX_VALUE;

    for (String word : words) {
        // Рахуємо кількість різних символів
        Set<Character> set = new HashSet<>();
        for (char c : word.toCharArray()) {
            set.add(c);
        }
        int uniqueCount = set.size();

        if (uniqueCount < minUnique) {
            minUnique = uniqueCount;
            result = word;
        }
    }

    // Повертаємо масив результатів
    return new String[]{result};
}

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

    for (String example : examples) {
        String[] output = findWord(example);
        System.out.println("Тестовий рядок: " + example);
        System.out.println("Слово, в якому кількість різних символів мінімальна: " + Arrays.toString(output) + "\n");
    }
}

