public class Card {

    private int cardID;         // Unique Id for Card
    private int cardSuitNum; // Suit & Number for card

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public int getCardSuitNum() {
        return cardSuitNum;
    }

    public void setCardSuitNum(String cardSuitNum) {
        this.cardSuitNum = cardSuitNum;
    }

}
