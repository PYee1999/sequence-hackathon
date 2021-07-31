package com.sequence.lib;

import com.sequence.res.DeadCardResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Player {
    private int playerMarker;
    private int sequenceCounter;
    private List<Space> sequenceList;
    private List<Space> sequenceList2;
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

    public void setSequenceList2(List<Space> val) {
        sequenceList2 = new ArrayList<>(val);
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

    // Checks if a given space is occupied by a sequence.
    public boolean spaceInSequence(Space space) {
        if (sequenceList == null) {
            return false;
        }
        for (Space s : sequenceList) {
            if (s.getCardSuitNum() == space.getCardSuitNum() &&
                s.getxLocation() == space.getxLocation() &&
                s.getyLocation() == space.getyLocation()) return true;
        }
        return false;
    }

    // Get list of available spaces on board for given card chosen by a given player
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
                            !otherPlayer.spaceInSequence(board.getBoard()[i][j])) {
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
    public DeadCardResponse checkDeadCards() {

        for (int i = 0; i < cardsList.size(); i++) {

            // Get card
            Card card = cardsList.get(i);

            // Get coordinate locations of the card on the map (there are two locations)
            int[] coord1 = new int[] {card.getCoordinates()[0][0], card.getCoordinates()[0][1]};
            int[] coord2 = new int[] {card.getCoordinates()[1][0], card.getCoordinates()[1][1]};

            //System.out.println("coord1: (" + coord1[0] + ", " + coord1[1] + ")");
            //System.out.println("coord2: (" + coord2[0] + ", " + coord2[1] + ")");

            // Check if the locations are occupied; if so, then we replace the card; if not, resume
            if (board.getBoard()[coord1[0]][coord1[1]].getOccupancy() != 0 &&
                board.getBoard()[coord2[0]][coord2[1]].getOccupancy() != 0) {
                //System.out.println("------- DEADCARD FOUND!!! -------");

                Card newCard = deck.deal();
                //System.out.println("newCard: " + newCard.getCardSuitNum());
                Card oldCard = cardsList.get(i);
                //System.out.println("oldCard: " + oldCard.getCardSuitNum());
                cardsList.remove(i);
                cardsList.add(newCard);
                //System.out.println("Updated cardsList: " + cardsList);
                //System.out.println("End of Dead Card Check: Dead Card " + oldCard + " has been replaced");
                return new DeadCardResponse(true, oldCard, newCard, cardsList);
            }

        }
        return new DeadCardResponse(false);
    }

    // Remove a specific card from the player's hand
    public boolean removeCard(int cardSuitNum) {
        return cardsList.remove(
                cardsList.stream().filter(c -> c.getCardSuitNum() == cardSuitNum).collect(Collectors.toList()).get(0));
    }

    // Print the first sequence list that player may have gotten from the game
    public void printSeqList(int number) {
        System.out.print("SEQUENCE LIST " + number + ": { ");

        if (number == 1) {
            if (sequenceList != null) {
                for (int i = 0; i < sequenceList.size(); i++) {
                    System.out.print("(" + sequenceList.get(i).getxLocation() + ", " + sequenceList.get(i).getyLocation() + ") ");
                }
            }
        } else {
            if (sequenceList2 != null) {
                for (int i = 0; i < sequenceList2.size(); i++) {
                    System.out.print("(" + sequenceList2.get(i).getxLocation() + ", " + sequenceList2.get(i).getyLocation() + ") ");
                }
            }
        }
        System.out.println("}");
    }
}
