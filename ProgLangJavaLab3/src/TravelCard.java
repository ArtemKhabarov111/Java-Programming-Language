import java.time.LocalDate;

public class TravelCard {
    Integer cardId;
    Integer remainingTrips;
    Integer balance;
    Integer tripPrice = 10;
    CardType cardType;
    CardSubType cardSubType;
    LocalDate expiryDate;

    public TravelCard(Integer cardId, CardType cardType, CardSubType cardSubType) {
        this.cardId = cardId;
        this.cardType = cardType;
        this.cardSubType = cardSubType;

        if(cardSubType == CardSubType.MONTHLY){
            this.expiryDate = LocalDate.now().plusMonths(1);
        } else if (cardSubType == CardSubType.TEN_DAYS) {
            this.expiryDate = LocalDate.now().plusDays(10);
        } else if (cardSubType == CardSubType.TEN_TRIPS) {
            this.remainingTrips = 10;
        } else if (cardSubType == CardSubType.FIVE_TRIPS) {
            this.remainingTrips = 5;
        } else if (cardSubType == CardSubType.ACCUMULATIVE) {
            this.balance = 0;
            expiryDate = null;
        }
    }

    public Integer getCardId() {
        return cardId;
    }

    public Integer getRemainingTrips() {
        return remainingTrips;
    }

    public Integer getBalance() {
        return balance;
    }

    public Integer getTripPrice() {
        return tripPrice;
    }

    public CardType getCardType() {
        return cardType;
    }

    public CardSubType getCardSubType() {
        return cardSubType;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public boolean isExpired() {
        if(expiryDate == null){
            return false;
        }
        return LocalDate.now().isAfter(expiryDate);
    }

    // Скористатися карткою. Відняти вартість квитка чи 1 поїздку
    public void useCard(){
        if(cardSubType == CardSubType.ACCUMULATIVE && balance >= tripPrice){
            balance -= tripPrice;
        } else if(remainingTrips != null && remainingTrips > 0){
            remainingTrips--;
        }
    }

    // Поповнити накопичувальну картку
    public void addBalance(int amount){
        if(cardSubType == CardSubType.ACCUMULATIVE && amount > 0){
            balance += amount;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID картки: ").append(cardId).append("\n");
        sb.append("Тип картки: ").append(cardType).append("\n");
        sb.append("Підтип картки: ").append(cardSubType).append("\n");

        if (expiryDate != null) {
            sb.append("Термін дії картки: ").append(expiryDate).append("\n");
        }
        if (remainingTrips != null) {
            sb.append("Залишилось поїздок: ").append(remainingTrips).append("\n");
        }
        if (balance != null) {
            sb.append("Баланс: ").append(balance).append("\n");
        }

        return sb.toString();
    }
}
