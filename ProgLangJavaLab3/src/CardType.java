public enum CardType {
    STUDENT("Учнівська"),
    UNIVERSITY("Студентська"),
    REGULAR("Звичайна");

    private final String displayName;

    CardType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
