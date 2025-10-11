public enum CardSubType {
    MONTHLY("Місячна"),
    TEN_DAYS("10 днів"),
    FIVE_TRIPS("5 поїздок"),
    TEN_TRIPS("10 поїздок"),
    ACCUMULATIVE("Накопичувальна");

    private final String displayName;

    CardSubType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
