package org.alan.model.field;

import java.util.Arrays;

import static java.lang.Math.abs;

public class CircleField extends Field {

    public CircleField(int rowLength, int colLength) {
        super(rowLength, colLength);
    }

    public void setUpCircularField(int rowLength, int colLength) {
        String[][] field = super.getField();
        int x0 = 0;
        int x1 = rowLength-1;
        int y0 = 0;
        int y1 = colLength-1;

        int a = abs(x1 - x0);
        int b = abs(y1 - y0);
        int b1 = b & 1;
        long dx = 4L * (1 - a) * b * b;
        long dy = 4L * (b1 + 1) * a * a;
        long err = dx + dy + (long) b1 * a * a;
        long e2;

        if (x0 > x1) {
            x0 = x1;
            x1 += a;
        }
        if (y0 > y1) y0 = y1;
        y0 += (b + 1) / 2;
        y1 = y0 - b1;
        a *= 8 * a;
        b1 = 8 * b * b;

        do {

            field[x1][y0] = INBOUND_MARK;
            field[x0][y0] = INBOUND_MARK;
            field[x0][y1] = INBOUND_MARK;
            field[x1][y1] = INBOUND_MARK;

            e2 = 2 * err;
            if (e2 <= dy) {
                y0++;
                y1--;
                err += dy += a;
            }
            if (e2 >= dx || 2 * err > dy) {
                x0++;
                x1--;
                err += dx += b1;
            }
        } while (x0 <= x1);

        while (y0 - y1 < b) {
            field[x0 - 1][y0] =   INBOUND_MARK;
            field[x1 + 1][y0++] = INBOUND_MARK;
            field[x0 - 1][y1] =   INBOUND_MARK;
            field[x1 + 1][y1--] = INBOUND_MARK;
        }
    }

    @Override
    protected void fillInboundField() {
        String[][] field = super.getField();
        setUpCircularField(field.length, field[0].length);
        for (String[] strings : field) {
            boolean foundLeftCircleEdge = false;
            boolean foundOOBMarkInBetweenEdges = false;
            boolean foundRightCircleEdge = false;
            int firstIndex = -1;
            int secondIndex = -1;
            for (int j = 0; j < strings.length; j++) {

                if (strings[j].equals(INBOUND_MARK) && !foundLeftCircleEdge) {
                    foundLeftCircleEdge = true;
                    firstIndex = j;
                    continue;
                }
                if (foundLeftCircleEdge && strings[j].equals(OUT_OF_BOUNDS_MARK) && !foundOOBMarkInBetweenEdges) {
                    foundOOBMarkInBetweenEdges = true;
                    continue;
                }
                if (foundLeftCircleEdge && foundOOBMarkInBetweenEdges && strings[j].equals(INBOUND_MARK)) {
                    secondIndex = j;
                    foundRightCircleEdge = true;
                    break;
                }
            }
            if (foundRightCircleEdge) {
                Arrays.fill(strings, firstIndex, secondIndex, INBOUND_MARK);
            }
        }
    }


}
