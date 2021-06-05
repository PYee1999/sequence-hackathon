package com.sequence.lib;

import java.util.ArrayList;
import java.util.List;

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
        sequenceList = val;
    }

    public Card selectCard(Card card) {
        if (cardsList.contains(card)) {
            cardsList.remove(card);
            return card;
        }
        return null;
    }

    // Turns the chosen space's occupency to 0;
    public Space takeMarker() {
        Space chosen = youChoose(); // Select space on board

        // Have player reselect space if the space contains player's own marker
        while (chosen.getOccupancy() == playerMarker) {
            chosen = youChoose();
        }

        // Have player reselect space if the space contains no marker
        while (chosen.getOccupancy() == 0) {
            chosen = youChoose();
        }

        int x = chosen.getxLocation(); // Get x-coordinate of selected space
        int y = chosen.getyLocation(); // Get y-coordinate of selected space

        board.getSpaceOnBoard(x, y).setOccupancy(0); // Remove opponent's marker on board
        return chosen;
    }

    // Selects the space available for the given card
    public Space selectSpace(Card card) {
        // If the card is a two-eyed jack(wild card), go to youChoose()
        if((card.getCardSuitNum() == 211) || (card.getCardSuitNum() == 311) ) {
            return youChoose();
        }

        // If the card is a one-eyed jack(sabetour card), go to takeMarker()
        else if((card.getCardSuitNum() == 111) || (card.getCardSuitNum() == 411) ) {
            return takeMarker();
        }

        int spacesFound = 0;
        Space choice1 = null;
        Space choice2 = null;

        // Check the whole board to see if there are any non-occupied spaces
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                if (card.getCardSuitNum() == board.getBoard()[i][j].getCardSuitNum() &&
                        board.getBoard()[i][j].getOccupancy() == 0) {
                    // If there are spaces, update choices and spacesFound counter
                    if (choice1 == null) {
                        choice1 = board.getBoard()[i][j];
                    } else {
                        choice2 = board.getBoard()[i][j];
                    }
                    spacesFound++;
                }
            }
        }

        if (spacesFound == 2) {                 // If there are two spaces found,
            return pickOne(choice1, choice2);   // Pick one space of the two spaces
        } else {                                // Otherwise,
            return choice1;                     // Automatically pick the only space
        }
    }

    public List<Space> listAvailableSpaces(Card card){

        List<Space> temp = new ArrayList<Space>();

        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                if((card.getCardSuitNum() == board.getBoard()[i][j].getCardSuitNum())
                        && (board.getBoard()[i][j].getOccupancy() == 0)){
                    temp.add(board.getBoard()[i][j]);
                }
            }
        }
        return temp;
    }

    // Checks for any dead cards
    public void checkDeadCards() {

        // Checks every single space on the board, as well as every card in the deck for a match
        for(int i = 0; i < board.getBoard().length; i++) {
            for(int j = 0; j < board.getBoard()[i].length; j++) {
                for(int k = 0; k < cardsList.size(); k++) {
                    //System.out.println("begining of index " + k + " at i,j: " + i + "," + j);

                    // If there's a occupied card space that matches a deck card, check for duplicates. Also, in the rare
                    // occurance where the added card is a dead card, it goes through this process again.
                    while((cardsList.get(k).getCardSuitNum() == board.getBoard()[i][j].getCardSuitNum())
                            && (board.getBoard()[i][j].getOccupancy() != 0)) {

                        // If there's a occupied space with the exact card name, verify that the next space isn't occupied
                        for(int x = i; x < board.getBoard().length; x++) {
                            for(int y = j; y < board.getBoard()[i].length; y++) {

                                // If the other space is occupied, delete dead card from deck and get a new card.
                                if((cardsList.get(k).getCardSuitNum() == board.getBoard()[x][y].getCardSuitNum())
                                        && (board.getBoard()[x][y].getOccupancy() != 0)){
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


    // Outputs chosen space from 2 spaces
    public Space pickOne(Space option1, Space option2) {
        return null;
    }


    // Outputs chosen space from whole board
    public Space youChoose() {
        return null;
    }


}
