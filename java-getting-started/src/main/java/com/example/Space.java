package com.example;

public class Space {

    private int xLocation;      // Horizontal Axis (Range: 0-9)
    private int yLocation;      // Vertical Axis (Range: 0-9)
    private int cardSuitNum;    // Suit & Number for card
    private int occupancy;      // Is space occupied by player {-1, 0, 1}

    public Space(int xLocation, int yLocation, int cardSuitNum, int occupancy) {
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.cardSuitNum= cardSuitNum;
        this.occupancy = occupancy;
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

    public int getCardSuitNum() {
        return cardSuitNum;
    }

    public void setCardSuitNum(int cardSuitNum) {
        this.cardSuitNum = cardSuitNum;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }


}
