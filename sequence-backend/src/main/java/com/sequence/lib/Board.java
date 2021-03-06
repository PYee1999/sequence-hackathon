package com.sequence.lib;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private Space[][] board = new Space[10][10]; // Set up the size of the board
    private List<Space> newSequenceList = new ArrayList<>(); // Get newly-founded sequence

    private final int MOVE_LEFT = -1;
    private final int MOVE_RIGHT = 1;
    private final int MOVE_UP = 1;
    private final int MOVE_DOWN = -1;
    private final int STAY = 0;

    // Get board
    public Space[][] getBoard() {
        return board;
    }

    // Get specified space on board
    public Space getSpaceOnBoard(int x, int y) {
        return board[x][y];
    }

    // Initialize board by populating the Spaces.
    public void initBoard() {
        // board[y][x] = new Space(x, y, cardSuitNum, occupancy)
        // 501, 502, 503, 504 = cardSuitNum for Wildcard

        // Row 0
        board[0][0] = new Space(0, 0, 501, 0);  // Wildcard
        board[1][0] = new Space(1, 0, 301, 0);  // A, Diamonds
        board[2][0] = new Space(2, 0, 313, 0);  // K, Diamonds
        board[3][0] = new Space(3, 0, 312, 0);  // Q, Diamonds
        board[4][0] = new Space(4, 0, 310, 0);  // 10, Diamonds
        board[5][0] = new Space(5, 0, 309, 0);  // 9, Diamonds
        board[6][0] = new Space(6, 0, 308, 0);  // 8, Diamonds
        board[7][0] = new Space(7, 0, 307, 0);  // 7, Diamonds
        board[8][0] = new Space(8, 0, 306, 0);  // 6, Diamonds
        board[9][0] = new Space(9, 0, 502, 0);  // Wildcard

        // Row 1
        board[0][1] = new Space(0, 1, 201, 0);  // A, Clubs
        board[1][1] = new Space(1, 1, 107, 0);  // 7, Spades
        board[2][1] = new Space(2, 1, 106, 0);  // 6, Spades
        board[3][1] = new Space(3, 1, 105, 0);  // 5, Spades
        board[4][1] = new Space(4, 1, 104, 0);  // 4, Spades
        board[5][1] = new Space(5, 1, 103, 0);  // 3, Spades
        board[6][1] = new Space(6, 1, 102, 0);  // 2, Spades
        board[7][1] = new Space(7, 1, 402, 0);  // 2, Hearts
        board[8][1] = new Space(8, 1, 403, 0);  // 6, Hearts
        board[9][1] = new Space(9, 1, 305, 0);  // 5, Diamonds

        // Row 2
        board[0][2] = new Space(0, 2, 213, 0);  // K, Clubs
        board[1][2] = new Space(1, 2, 108, 0);  // 8, Spades
        board[2][2] = new Space(2, 2, 210, 0);  // 10, Clubs
        board[3][2] = new Space(3, 2, 212, 0);  // Q, Clubs
        board[4][2] = new Space(4, 2, 213, 0);  // K, Clubs
        board[5][2] = new Space(5, 2, 201, 0);  // A, Clubs
        board[6][2] = new Space(6, 2, 301, 0);  // A, Diamonds
        board[7][2] = new Space(7, 2, 313, 0);  // K, Diamonds
        board[8][2] = new Space(8, 2, 404, 0);  // 4, Hearts
        board[9][2] = new Space(9, 2, 304, 0);  // 4, Diamonds

        // Row 3
        board[0][3] = new Space(0, 3, 212, 0);  // Q, Clubs
        board[1][3] = new Space(1, 3, 109, 0);  // 9, Spades
        board[2][3] = new Space(2, 3, 209, 0);  // 9, Clubs
        board[3][3] = new Space(3, 3, 408, 0);  // 8, Hearts
        board[4][3] = new Space(4, 3, 409, 0);  // 9, Hearts
        board[5][3] = new Space(5, 3, 410, 0);  // 10, Hearts
        board[6][3] = new Space(6, 3, 412, 0);  // Q, Hearts
        board[7][3] = new Space(7, 3, 312, 0);  // Q, Diamonds
        board[8][3] = new Space(8, 3, 405, 0);  // 5, Hearts
        board[9][3] = new Space(9, 3, 303, 0);  // 3, Diamonds

        // Row 4
        board[0][4] = new Space(0, 4, 210, 0);  // 10, Clubs
        board[1][4] = new Space(1, 4, 110, 0);  // 10, Spades
        board[2][4] = new Space(2, 4, 208, 0);  // 8, Clubs
        board[3][4] = new Space(3, 4, 407, 0);  // 7, Hearts
        board[4][4] = new Space(4, 4, 402, 0);  // 2, Hearts
        board[5][4] = new Space(5, 4, 403, 0);  // 3, Hearts
        board[6][4] = new Space(6, 4, 413, 0);  // K, Hearts
        board[7][4] = new Space(7, 4, 310, 0);  // 10, Diamonds
        board[8][4] = new Space(8, 4, 406, 0);  // 6, Hearts
        board[9][4] = new Space(9, 4, 302, 0);  // 2, Diamonds

        // Row 5
        board[0][5] = new Space(0, 5, 209, 0);  // 9, Clubs
        board[1][5] = new Space(1, 5, 112, 0);  // Q, Spades
        board[2][5] = new Space(2, 5, 207, 0);  // 7, Clubs
        board[3][5] = new Space(3, 5, 406, 0);  // 6, Hearts
        board[4][5] = new Space(4, 5, 405, 0);  // 5, Hearts
        board[5][5] = new Space(5, 5, 404, 0);  // 4, Hearts
        board[6][5] = new Space(6, 5, 401, 0);  // A, Hearts
        board[7][5] = new Space(7, 5, 309, 0);  // 9, Diamonds
        board[8][5] = new Space(8, 5, 407, 0);  // 7, Hearts
        board[9][5] = new Space(9, 5, 101, 0);  // A, Spades

        // Row 6
        board[0][6] = new Space(0, 6, 208, 0);  // 8, Clubs
        board[1][6] = new Space(1, 6, 113, 0);  // K, Spades
        board[2][6] = new Space(2, 6, 206, 0);  // 6, Clubs
        board[3][6] = new Space(3, 6, 205, 0);  // 5, Clubs
        board[4][6] = new Space(4, 6, 204, 0);  // 4, Clubs
        board[5][6] = new Space(5, 6, 203, 0);  // 3, Clubs
        board[6][6] = new Space(6, 6, 202, 0);  // 2, Clubs
        board[7][6] = new Space(7, 6, 308, 0);  // 8, Diamonds
        board[8][6] = new Space(8, 6, 408, 0);  // 8, Hearts
        board[9][6] = new Space(9, 6, 113, 0);  // K, Spades

        // Row 7
        board[0][7] = new Space(0, 7, 207, 0);  // 7, Clubs
        board[1][7] = new Space(1, 7, 101, 0);  // A, Spades
        board[2][7] = new Space(2, 7, 302, 0);  // 2, Diamonds
        board[3][7] = new Space(3, 7, 303, 0);  // 3, Diamonds
        board[4][7] = new Space(4, 7, 304, 0);  // 4, Diamonds
        board[5][7] = new Space(5, 7, 305, 0);  // 5, Diamonds
        board[6][7] = new Space(6, 7, 306, 0);  // 6, Diamonds
        board[7][7] = new Space(7, 7, 307, 0);  // 7, Diamonds
        board[8][7] = new Space(8, 7, 409, 0);  // 9, Hearts
        board[9][7] = new Space(9, 7, 112, 0);  // Q, Spades

        // Row 8
        board[0][8] = new Space(0, 8, 206, 0);  // 6, Clubs
        board[1][8] = new Space(1, 8, 205, 0);  // 5, Clubs
        board[2][8] = new Space(2, 8, 204, 0);  // 4, Clubs
        board[3][8] = new Space(3, 8, 203, 0);  // 3, Clubs
        board[4][8] = new Space(4, 8, 202, 0);  // 2, Clubs
        board[5][8] = new Space(5, 8, 401, 0);  // A, Hearts
        board[6][8] = new Space(6, 8, 413, 0);  // K, Hearts
        board[7][8] = new Space(7, 8, 412, 0);  // Q, Hearts
        board[8][8] = new Space(8, 8, 410, 0);  // 10, Hearts
        board[9][8] = new Space(9, 8, 110, 0);  // 10, Spades

        // Row 9
        board[0][9] = new Space(0, 9, 504, 0);  // Wildcard
        board[1][9] = new Space(1, 9, 102, 0);  // 2, Spades
        board[2][9] = new Space(2, 9, 103, 0);  // 3, Spades
        board[3][9] = new Space(3, 9, 104, 0);  // 4, Spades
        board[4][9] = new Space(4, 9, 105, 0);  // 5, Spades
        board[5][9] = new Space(5, 9, 106, 0);  // 6, Spades
        board[6][9] = new Space(6, 9, 107, 0);  // 7, Spades
        board[7][9] = new Space(7, 9, 108, 0);  // 8, Spades
        board[8][9] = new Space(8, 9, 109, 0);  // 9, Spades
        board[9][9] = new Space(9, 9, 503, 0);  // Wildcard
    }

    // Checks if there exists a sequence in the board
    public int checkSequence(int x, int y, Player player) {

        System.out.println("\nCHECKING FOR A SEQUENCE, Starting at (" + x + ", " + y + ")");

        boolean sequenceFound = false;
        newSequenceList.clear();

        int checkAllSides = 1;
        switch (checkAllSides) {
            case 1:
                System.out.print("Looking UP ");
                // Check if there is any horizontal sequence (x-axis)
                int totalXCount =
                        checkNextSquare(x, y, MOVE_RIGHT, STAY, player.getPlayerMarker(), 5);
                if (totalXCount < 5) {
                    System.out.print("Looking DOWN ");
                    totalXCount +=
                        checkNextSquare(x-1, y, MOVE_LEFT, STAY, player.getPlayerMarker(), 5 - totalXCount);
                }
                if (totalXCount == 5) { // If you have a sequence,
                    sequenceFound = true; // Set sequenceFound to be true
                    break;
                } else {
                    newSequenceList.clear();
                }
            case 2:
                System.out.print("Looking LEFT ");
                // Check if there is any vertical sequence (y-axis)
                int totalYCount =
                        checkNextSquare(x, y, STAY, MOVE_UP, player.getPlayerMarker(), 5);
                if (totalYCount < 5) {
                    System.out.print("Looking RIGHT ");
                    totalYCount +=
                        checkNextSquare(x, y-1, STAY, MOVE_DOWN, player.getPlayerMarker(), 5 - totalYCount);
                }
                if (totalYCount == 5) { // If you have a sequence,
                    sequenceFound = true; // Set sequenceFound to be true
                    break;
                } else {
                    newSequenceList.clear();
                }
            case 3:
                System.out.print("Looking DOWN-LEFT ");
                // Check if there is a left diagonal sequence (diagonal: top-left to bottom-right)
                int totalLDiagonalCount =
                        checkNextSquare(x, y, MOVE_LEFT, MOVE_UP, player.getPlayerMarker(), 5);
                if (totalLDiagonalCount < 5) {
                    System.out.print("Looking UP-RIGHT ");
                    totalLDiagonalCount +=
                        checkNextSquare(x+1, y-1, MOVE_RIGHT, MOVE_DOWN, player.getPlayerMarker(), 5 - totalLDiagonalCount);
                }
                if (totalLDiagonalCount == 5) { // If you have a sequence,
                    sequenceFound = true; // Set sequenceFound to be true
                    break;
                } else {
                    newSequenceList.clear();
                }
            case 4:
                System.out.print("Looking UP-LEFT ");
                // Check if there is a right diagonal sequence (diagonal: bottom-left to top-right)
                int totalRDiagonalCount =
                        checkNextSquare(x, y, MOVE_RIGHT, MOVE_UP, player.getPlayerMarker(), 5);
                if (totalRDiagonalCount < 5) {
                    System.out.print("Looking DOWN-RIGHT ");
                    totalRDiagonalCount +=
                        checkNextSquare(x-1, y-1, MOVE_LEFT, MOVE_DOWN, player.getPlayerMarker(), 5 - totalRDiagonalCount);
                }
                if (totalRDiagonalCount == 5) { // If you have a sequence,
                    sequenceFound = true; // Set sequenceFound to be true
                } else {
                    newSequenceList.clear();
                }
        }

        if (sequenceFound) {
            System.out.println("--- SEQUENCE FOUND ---");
            System.out.print("newSequenceList: ");
            printSeqList(newSequenceList);
            System.out.print("First Sequence found: ");
            printSeqList(player.getSequenceList());

            if (player.getSequenceCounter() == 1) { // If you have 1 sequence found previously,
                int intersections = 0;
                for(Space val : newSequenceList) { // Check for any intersections between sequences
                    printSpaceInfo(val);
                    System.out.println("Does player's first seq contain val: " + player.spaceInSequence(val));
                    if (player.spaceInSequence(val)) {
                        intersections++;
                        System.out.println("Intersection at: (" + val.getxLocation() + ", " + val.getyLocation() + ")");
                    }
                };
                System.out.println("Total Intersections: " + intersections);
                if (intersections <= 1) {   // If there is at most one sequence,
                    player.setSequenceCounter(2);   // Set sequence count to 2
                    player.setSequenceList2(newSequenceList); // Save second sequence
                }
            } else { // Otherwise, if you have 0 sequences previously,
                player.setSequenceCounter(1); // Set sequence counter to 1
                player.setSequenceList(newSequenceList); // Save first sequence
            }
        }

        System.out.print("PLAYER: ");
        if (player.getPlayerMarker() == -1) {
            System.out.print("Blue, ");
        } else {
            System.out.print("Red,  ");
        }

        System.out.println("MARKER AT: (" + x + ", " + y + ")");

        System.out.print("PLAYER: ");
        if (player.getPlayerMarker() == -1) {
            System.out.print("Blue, ");
        } else {
            System.out.print("Red,  ");
        }

        System.out.println("TOTAL SEQUENCES: " + player.getSequenceCounter());

        System.out.print("PLAYER: ");
        if (player.getPlayerMarker() == -1) {
            System.out.print("Blue, ");
        } else {
            System.out.print("Red,  ");
        }

        player.printSeqList(1);

        System.out.print("PLAYER: ");
        if (player.getPlayerMarker() == -1) {
            System.out.print("Blue, ");
        } else {
            System.out.print("Red,  ");
        }

        player.printSeqList(2);

        return player.getSequenceCounter();
    }

    public void printSeqList(List<Space> sequenceList) {
        System.out.print("{ ");
        if (sequenceList != null) {
            for (int i = 0; i < sequenceList.size(); i++) {
                System.out.print("(" + sequenceList.get(i).getxLocation() + ", " + sequenceList.get(i).getyLocation() + ") ");
            }
        }
        System.out.println("}");
    }

    public void printSpaceInfo(Space space) {
        System.out.print("Space Location: (" + space.getxLocation() + ", " + space.getyLocation() + ")  ");
        System.out.print("CardSuitNum: " + space.getCardSuitNum() + "   ");
        System.out.println("Occupancy: " + space.getOccupancy());
    }

    // Helper recursive method to find adjacent spaces with same marker to find possible sequence
    public int checkNextSquare(int x, int y, int xMove, int yMove, int playerMarker, int maxDist) {

        System.out.println("at Coordinates: (" + x + ", " + y + ")");
        System.out.println("Spaces remaining: " + maxDist);

        // Check if x and y are out of bounds.
        if (x < 0 || x > 9 || y < 0 || y > 9) {

            System.out.println("Stop searching: Out of bounds");
            return 0; // If so, return 0 and stop recursing
        }

        // Check if we have reach the maximum distance.
        if (maxDist <= 0) {
            System.out.println("Stop searching: Reach maximum distance");
            return 0; // If so, return 0 and stop recursing
        }

        // Check if that space happens to be on a Wildcard (or at the corner of the board)
        if ((getSpaceOnBoard(x, y).getCardSuitNum() == 501) ||
            (getSpaceOnBoard(x, y).getCardSuitNum() == 502) ||
            (getSpaceOnBoard(x, y).getCardSuitNum() == 503) ||
            (getSpaceOnBoard(x, y).getCardSuitNum() == 504)) {

            // Add sequence to list to keep track.
            newSequenceList.add(new Space(x, y, getSpaceOnBoard(x, y).getCardSuitNum(), getSpaceOnBoard(x, y).getOccupancy()));

            System.out.println("Stop searching: At wildcard");

            return 1; // Count it by returning 1 and stop recursing
        }

        // Check if the space has the same marker as the player
        if (getSpaceOnBoard(x, y).getOccupancy() != playerMarker) {
            System.out.println("Stop searching: space is not player's marker; player's marker: " + playerMarker + ", marker occupied with: " + getSpaceOnBoard(x, y).getOccupancy());
            return 0; // If NOT, return 0 and stop recursing
        }

        // Check if the space is at the edge of the board (exclude corners)
        if ((x == 0 && xMove < 0) ||    // Check left edge
                (x == 9 && xMove > 0) ||    // Check right edge
                (y == 0 && yMove < 0) ||    // Check bottom edge
                (y == 9 && yMove > 0)) {    // Check top edge

            // Add sequence to list to keep track.
            newSequenceList.add(new Space(x, y, getSpaceOnBoard(x, y).getCardSuitNum(), getSpaceOnBoard(x, y).getOccupancy()));

            System.out.println("Stop searching: At edge of board");

            return 1; // If so, return 1 and stop recursing
        }

        // Add sequence to list to keep track.
        newSequenceList.add(new Space(x, y, getSpaceOnBoard(x, y).getCardSuitNum(), getSpaceOnBoard(x, y).getOccupancy()));

        // Beyond all checks, recurse to the next space and check if it has the same marker.
        return 1 + checkNextSquare(x + xMove, y + yMove, xMove, yMove, playerMarker, maxDist-1);
    }

}
