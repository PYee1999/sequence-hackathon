package com.sequence.lib;

import java.util.Collections;
import java.util.LinkedList;

public class Deck {
    private LinkedList<Card> cards;
    private int cardNum;

    public void initDeck(Board board) {
        cards = new LinkedList<>();
        cardNum = 104;

        for (int i = 0; i < 104; i++) {
            int[][] sample = new int[2][2];
            int cardSuitNum = calcCardSuitNum(i);
            int sampleXIndex = 0;

            for(int a = 0; a < 10; a++){
                for(int b = 0; b < 10; b++){
                    if(board.getBoard()[a][b].getCardSuitNum() == cardSuitNum) {
                        sample[sampleXIndex][0] = a;
                        sample[sampleXIndex][1] = b;
                        sampleXIndex++;
                    }
                }
            }
            cards.add(new Card(i, cardSuitNum, sample));
            System.out.println(
                    "id: " + i +
                    ", cardSuitNumber: " + cardSuitNum +
                    ", locations: (" + sample[0][0] + "," + sample[0][1] + "), (" + sample[1][0] + "," + sample[1][1] + ")");
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card deal() {
        cardNum--;
        return cards.poll();
    }

    public int getCardNum() {
        return cardNum;
    }

    private int calcCardSuitNum(int cardID) {
        cardID = cardID % 52;
        int suit = cardID / 13 + 1;
        int rank = cardID % 13 + 1;
        return suit * 100 + rank;
    }
}
