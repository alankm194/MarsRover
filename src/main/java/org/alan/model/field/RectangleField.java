package org.alan.model.field;

import java.util.Arrays;

public class RectangleField extends Field {


    public RectangleField(int row, int col) {
        super(row, col);
    }
    @Override
    protected void fillInboundField() {
        for (String[] arr: super.getField()) {
            Arrays.fill(arr, INBOUND_MARK);
        }
    }
}
