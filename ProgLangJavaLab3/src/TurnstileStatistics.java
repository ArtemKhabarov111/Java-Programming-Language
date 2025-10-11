import java.util.HashMap;
import java.util.Map;

public class TurnstileStatistics {
    private int totalAllowed = 0;
    private int totalDenied = 0;

    // Статистика по типах карток (Учнівська, Студентська, Звичайна)
    private final Map<CardType, Integer> allowedByCardType = new HashMap<>();
    private final Map<CardType, Integer> deniedByCardType = new HashMap<>();

    // Ініціалізація всіх типів карток з нульовими значеннями
    public TurnstileStatistics() {
        for (CardType type : CardType.values()) {
            allowedByCardType.put(type, 0);
            deniedByCardType.put(type, 0);
        }
    }

    // Записати дозвіл проходу
    public void recordAllowed(CardType cardType) {
        totalAllowed++;
        allowedByCardType.put(cardType, allowedByCardType.get(cardType) + 1);
    }

    // Записати відмову проходу
    public void recordDenied(CardType cardType) {
        totalDenied++;
        deniedByCardType.put(cardType, deniedByCardType.get(cardType) + 1);
    }

    // Записати відмову (коли картка не знайдена або помилка зчитування)
    public void recordDeniedUnknown() {
        totalDenied++;
    }

    // Отримати загальну кількість дозволених проходів
    public int getTotalAllowed() {
        return totalAllowed;
    }

    // Отримати загальну кількість відмов
    public int getTotalDenied() {
        return totalDenied;
    }

    // Отримати загальну кількість спроб
    public int getTotalAttempts() {
        return totalAllowed + totalDenied;
    }

    // Отримати кількість дозволів для конкретного типу картки
    public int getAllowedByCardType(CardType cardType) {
        return allowedByCardType.getOrDefault(cardType, 0);
    }

    // Отримати кількість відмов для конкретного типу картки
    public int getDeniedByCardType(CardType cardType) {
        return deniedByCardType.getOrDefault(cardType, 0);
    }

    // Отримати загальну статистику
    public String getSummaryStatistics() {
        return "Всього спроб: " + getTotalAttempts() + "\n" +
                "Дозволено проходів: " + totalAllowed + "\n" +
                "Відмовлено проходів: " + totalDenied + "\n";
    }

    // Отримати статистику по типах карток
    public String getDetailedStatisticsByCardType() {
        StringBuilder sb = new StringBuilder();
        for (CardType type : CardType.values()) {
            int allowed = allowedByCardType.get(type);
            int denied = deniedByCardType.get(type);
            int total = allowed + denied;

            sb.append("---- ").append(type.getDisplayName()).append(" ----\n");
            sb.append("Дозволено: ").append(allowed).append("\n");
            sb.append("Відмовлено: ").append(denied).append("\n");
            sb.append("Всього спроб: ").append(total).append("\n");
            sb.append("\n");
        }

        return sb.toString();
    }
}