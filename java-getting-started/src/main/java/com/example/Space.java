public class Space {

    private int xLocation;
    private int yLocation;
    private String cardSuitNum;
    private int occupancy;

    public Space(int xLocation, int yLocation, String cardSuitNum, int occupancy) {
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.cardSuitNum= cardSuitNum; // Suit & Number
        this.occupancy = occupancy; // -1, 0, 1
    }

    public int getxLocation() {
        return xLocation;
    }

    public void setxLocation(int xLocation) {
        this.xLocation = xLocation;
    }

    public int getyLocation() {
        return yLocation;
    }

    public void setyLocation(int yLocation) {
        this.yLocation = yLocation;
    }

    public String getCardSuitNum() {
        return cardSuitNum;
    }

    public void setCardSuitNum(String cardSuitNum) {
        this.cardSuitNum = cardSuitNum;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }


}