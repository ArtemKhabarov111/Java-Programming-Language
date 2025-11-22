import java.util.HashMap;

public class Translator {
    HashMap<String, String> dictionary = new HashMap<>();

    public void addWord(String english, String ukrainian) {
        dictionary.put(english.toLowerCase(), ukrainian.toLowerCase());
    }

    public void printDictionary() {
        for (String key : dictionary.keySet()) {
            System.out.println(key + " = " + dictionary.get(key));
        }
    }

    public String translate(String sentence) {
        String[] words = sentence.split("\\s+");
        StringBuilder translation = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            // Слово без пунктуації
            String thisWord = word.replaceAll("[^a-zA-Z]", "");

            // Пунктуація
            String punctuation = word.replaceAll("[a-zA-Z]", "");

            String getCase = dictionary.getOrDefault(thisWord.toLowerCase(), "[" + thisWord + "]");

            String translated = applyCase(thisWord, getCase);

            // Переклад + пунктуація
            translation.append(translated).append(punctuation);

            // Додаємо пробіл лише між словами
            if (i < words.length - 1) {
                translation.append(" ");
            }
        }

        return translation.toString();
    }

    private String applyCase(String original, String translated) {

        // Якщо все слово у верхньому регістрі
        if (original.equals(original.toUpperCase())) {
            return translated.toUpperCase();
        }

        // Якщо тільки перша літера у верхньому регістрі
        if (Character.isUpperCase(original.charAt(0))) {
            return translated.substring(0, 1).toUpperCase() + translated.substring(1);
        }

        // Якщо слово у нижньому регістрі
        return translated.toLowerCase();
    }

}
