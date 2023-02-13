package org.alan.model.vehicle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovementTest {

    @Test
    public void testCreateMovement() {
        Movement move = new Movement(4, 2);
        assertEquals(4, move.moveX());
        assertEquals(2, move.moveY());
    }
}
