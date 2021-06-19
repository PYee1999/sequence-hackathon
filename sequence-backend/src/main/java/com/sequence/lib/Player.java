package com.sequence.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Player {
    private int playerMarker;
    private int sequenceCounter;
    private List<Space> sequenceList;
    private List<Card> cardsList;
    private Board board;
    private Deck deck;

    // playerColor: 1 is red, -1 is blue
    public Player(Board board, Deck deck, int playerColor) {
        this.board = board;
        this.deck = deck;
        playerMarker = playerColor;
        sequenceCounter = 0;
        cardsList = new ArrayList<>();
    }

    public void addCard(Card card) {
        cardsList.add(card);
    }

    public int getPlayerMarker() {
        return playerMarker;
    }

    public int getSequenceCounter() {
        return sequenceCounter;
    }

    public void setSequenceCounter(int val) {
        sequenceCounter = val;
    }

    public List<Card> getCardsList() {
        return cardsList;
    }

    public List<Space> getSequenceList() {
        return sequenceList;
    }

    public void setSequenceList(List<Space> val) {
        sequenceList = new ArrayList<>(val);
    }

    public Card selectCard(Card card) {
        if (cardsList.contains(card)) {
            cardsList.remove(card);
            return card;
        }
        return null;
    }

    public Card selectCard(int card) {
        List<Card> foundCards = cardsList.stream().filter(c -> c.getCardSuitNum() == card).collect(Collectors.toList());
        if (foundCards.size() >= 1) {
            cardsList.remove(foundCards.get(0));
            return foundCards.get(0);
        }
        return null;
    }

    // Selects the space available for the given card
    public Space selectSpace(int cardSuitNum, int x, int y) {
        // One-eyed jack
        if ((cardSuitNum == 111) || (cardSuitNum == 411)) {
            board.getSpaceOnBoard(x, y).setOccupancy(0);
            return board.getSpaceOnBoard(x, y);
        }
        board.getSpaceOnBoard(x, y).setOccupancy(playerMarker);
        return board.getSpaceOnBoard(x, y);
    }

    public List<Space> listAvailableSpaces(Card card, Player otherPlayer) {

        List<Space> temp = new ArrayList<Space>();

        if (card.getCardSuitNum() == 211 || card.getCardSuitNum() == 311) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (board.getBoard()[i][j].getOccupancy() == 0 &&
                            board.getBoard()[i][j].getCardSuitNum() / 100 != 5) {
                        temp.add(board.getBoard()[i][j]);
                    }
                }
            }
            return temp;
        }
        if (card.getCardSuitNum() == 111 || card.getCardSuitNum() == 411) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (board.getBoard()[i][j].getOccupancy() == otherPlayer.getPlayerMarker() &&
                            ((otherPlayer.sequenceCounter == 0) || !otherPlayer.getSequenceList().contains(board.getBoard()[i][j]))) {
                        temp.add(board.getBoard()[i][j]);
                    }
                }
            }
            return temp;
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((card.getCardSuitNum() == board.getBoard()[i][j].getCardSuitNum())
                        && (board.getBoard()[i][j].getOccupancy() == 0)) {
                    temp.add(board.getBoard()[i][j]);
                }
            }
        }
        return temp;
    }

    // Checks for any dead cards
    public void checkDeadCards() {

        // Checks every single space on the board, as well as every card in the deck for a match
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                for (int k = 0; k < cardsList.size(); k++) {
                    //System.out.println("begining of index " + k + " at i,j: " + i + "," + j);

                    // If there's a occupied card space that matches a deck card, check for duplicates. Also, in the rare
                    // occurance where the added card is a dead card, it goes through this process again.
                    while ((cardsList.get(k).getCardSuitNum() == board.getBoard()[i][j].getCardSuitNum())
                            && (board.getBoard()[i][j].getOccupancy() != 0)) {

                        // If there's a occupied space with the exact card name, verify that the next space isn't occupied
                        for (int x = i; x < board.getBoard().length; x++) {
                            for (int y = j; y < board.getBoard()[i].length; y++) {

                                // If the other space is occupied, delete dead card from deck and get a new card.
                                if ((cardsList.get(k).getCardSuitNum() == board.getBoard()[x][y].getCardSuitNum())
                                        && (board.getBoard()[x][y].getOccupancy() != 0)) {
                                    //System.out.println("Time to remove");
                                    cardsList.remove(k);
                                    cardsList.add(deck.deal());

                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean removeCard(int cardSuitNum) {
        return cardsList.remove(
                cardsList.stream().filter(c -> c.getCardSuitNum() == cardSuitNum).collect(Collectors.toList()).get(0));
    }

    public void printSeqList() {
        System.out.print("SEQUENCE LIST: { ");
        if (sequenceList != null) {
            for (int i = 0; i < sequenceList.size(); i++) {
                System.out.print("(" + sequenceList.get(i).getxLocation() + ", " + sequenceList.get(i).getyLocation() + ") ");
            }
        }
        System.out.println("}");
    }

}
