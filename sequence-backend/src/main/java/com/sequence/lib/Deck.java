package com.sequence.lib;

import java.util.Collections;
import java.util.LinkedList;

public class Deck {
    private LinkedList<Card> cards;
    private int cardNum;

    // Initialize Deck of cards
    public void initDeck(Board board) {
        cards = new LinkedList<>();
        cardNum = 104; // 2 decks of cards (2 * 52 = 104)

        // Go through each card
        for (int i = 0; i < 104; i++) {
            int[][] sample = new int[2][2];
            int cardSuitNum = calcCardSuitNum(i);   // Get card's suit and number
            int sampleXIndex = 0;

            // Get locations of where the cards are on the board, based on their unique cardSuitNum
            for(int a = 0; a < 10; a++){
                for(int b = 0; b < 10; b++){
                    if(board.getBoard()[a][b].getCardSuitNum() == cardSuitNum) {
                        sample[sampleXIndex][0] = a;
                        sample[sampleXIndex][1] = b;
                        sampleXIndex++;
                    }
                }
            }

            // Add card onto deck
            cards.add(new Card(i, cardSuitNum, sample));
            System.out.println(
                    "id: " + i +
                    ", cardSuitNumber: " + cardSuitNum +
                    ", locations: (" + sample[0][0] + "," + sample[0][1] + "), (" + sample[1][0] + "," + sample[1][1] + ")");
        }
        shuffle();
    }

    // Shuffles the deck
    public void shuffle() {
        Collections.shuffle(cards);
    }

    // Pops one card out from the deck
    public Card deal() {
        cardNum--;
        return cards.poll();
    }

    public int getCardNum() {
        return cardNum;
    }

    // Calculate/Identify the card's suit and number based on the card's ID
    private int calcCardSuitNum(int cardID) {
        cardID = cardID % 52;
        int suit = cardID / 13 + 1;
        int rank = cardID % 13 + 1;
        return suit * 100 + rank;
    }
}
