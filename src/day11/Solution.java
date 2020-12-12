package day11;

import util.InputReader;

import java.io.IOException;
import java.util.List;

public class Solution {

    public static void main(String[] args) throws IOException {
        final List<String> lines = InputReader.readLines("/day11/input.txt");

        char[][] seats = new char[lines.size()][lines.get(0).length()];

        for (int i = 0; i < seats.length; i++) {
            seats[i] = lines.get(i).toCharArray();
        }

        System.out.println(part1(seats));
        System.out.println(part2(seats));
    }

    private static long part2(char[][] seats) {
        boolean isSame = false;
        while (!isSame) {
            char[][] temp = updateSeats2(seats);
            isSame = seatsAreSame(temp, seats);
            seats = temp;
        }

        return countOccupiedSeats(seats);
    }

    private static long part1(char[][] seats) {

        boolean isSame = false;
        while (!isSame) {
            char[][] temp = updateSeats1(seats);
            isSame = seatsAreSame(temp, seats);
            seats = temp;
        }

        return countOccupiedSeats(seats);
    }

    private static int countOccupiedSeats(char[][] seats) {
        int ctr = 0;
        for (char[] seat : seats) {
            for (char c : seat) {
                if (c == '#') {
                    ctr++;
                }
            }
        }
        return ctr;
    }

    private static boolean seatsAreSame(char[][] temp, char[][] seats) {
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                if (seats[i][j] != temp[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static char[][] updateSeats1(char[][] seats) {

        final char[][] updatedSeats = new char[seats.length][seats[0].length];

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {

                char c = seats[i][j];

                switch (c) {
                    case 'L' :
                        boolean occupied = false;
                        for (int iOff = -1; iOff < 2; iOff++) {
                            if (occupied) {
                                break;
                            }
                            for (int jOff = -1; jOff < 2; jOff++) {
                                if (iOff == 0 && jOff == 0 ||
                                        (i + iOff) < 0 ||
                                        (j + jOff) < 0 ||
                                        (i + iOff) > updatedSeats.length - 1 ||
                                        (j + jOff) > updatedSeats[0].length - 1) {
                                    continue;
                                }

                                char charAt = seats[i + iOff][j + jOff];

                                if (charAt == '#') {
                                    occupied = true;
                                    break;
                                }
                            }
                        }
                        updatedSeats[i][j] = occupied ? 'L' : '#';
                    break;
                    case '#' :
                        int occupiedCtr = 0;
                        for (int iOff = -1; iOff < 2; iOff++) {
                            if (occupiedCtr >= 4) {
                                break;
                            }
                            for (int jOff = -1; jOff < 2; jOff++) {
                                if (iOff == 0 && jOff == 0 ||
                                        (i + iOff) < 0 ||
                                        (j + jOff) < 0 ||
                                        (i + iOff) > updatedSeats.length - 1 ||
                                        (j + jOff) > updatedSeats[0].length - 1) {
                                    continue;
                                }

                                char charAt = seats[i + iOff][j + jOff];

                                if (charAt == '#') {
                                    occupiedCtr++;
                                }
                            }
                        }
                        updatedSeats[i][j] = occupiedCtr >= 4 ? 'L' : '#';
                        break;
                    default :
                        updatedSeats[i][j] = '.';
                        break;
                }
            }
        }
        return updatedSeats;
    }

    private static char[][] updateSeats2(char[][] seats) {

        final char[][] updatedSeats = new char[seats.length][seats[0].length];

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {

                char c = seats[i][j];

                switch (c) {
                    case 'L' :
                        boolean occupied = false;
                        for (int iOff = -1; iOff < 2; iOff++) {
                            if (occupied) {
                                break;
                            }
                            for (int jOff = -1; jOff < 2; jOff++) {
                                if (iOff == 0 && jOff == 0) {
                                    continue;
                                }
                                if (occupied) {
                                    break;
                                }

                                int scale = Math.max(updatedSeats.length, updatedSeats[0].length);
                                for (int k = 1; k < scale; k++) {
                                    int iOffScale = iOff * k;
                                    int jOffScale = jOff * k;

                                    if(i + iOffScale < 0 ||
                                            j + jOffScale < 0 ||
                                            i + iOffScale >= updatedSeats.length ||
                                            j + jOffScale >= updatedSeats[i].length) {
                                        continue;
                                    }

                                    char charAt = seats[i + iOffScale][j + jOffScale];

                                    if (charAt == '#') {
                                        occupied = true;
                                        break;
                                    } else if (charAt == 'L') {
                                        break;
                                    }
                                }

                            }
                        }
                        updatedSeats[i][j] = occupied ? 'L' : '#';
                    break;
                    case '#' :
                        int occupiedCtr = 0;
                        for (int iOff = -1; iOff < 2; iOff++) {
                            for (int jOff = -1; jOff < 2; jOff++) {
                                if (iOff == 0 && jOff == 0) {
                                    continue;
                                }
                                int scale = Math.max(updatedSeats.length, updatedSeats[0].length);
                                for (int k = 1; k < scale; k++) {
                                    int iOffScale = iOff * k;
                                    int jOffScale = jOff * k;

                                    if(i + iOffScale < 0 ||
                                            j + jOffScale < 0 ||
                                            i + iOffScale >= updatedSeats.length ||
                                            j + jOffScale >= updatedSeats[i].length) {
                                        continue;
                                    }

                                    char charAt = seats[i + iOffScale][j + jOffScale];

                                    if (charAt == '#') {
                                        occupiedCtr++;
                                        break;
                                    } else if (charAt == 'L') {
                                        break;
                                    }
                                }

                            }
                        }
                        updatedSeats[i][j] = occupiedCtr >= 5 ? 'L' : '#';
                    break;
                default :
                    updatedSeats[i][j] = '.';
                    break;
                }
            }
        }
        return updatedSeats;
    }
}
