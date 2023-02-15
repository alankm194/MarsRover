package org.alan.model.field;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class CircleFieldTest {


    @Test
    public void test4x4CircleArrayHasCorrectOutput() {
        Field field = new CircleField(4, 4);
        String[][] CircleArray4x4 ={
            {"-1", " 0", " 0", "-1"},
            {" 0", " 0", " 0", " 0" },
            {" 0", " 0", " 0", " 0" },
            {"-1", " 0", " 0", "-1"},
        };
        assertArrayEquals(CircleArray4x4, field.getField());
    }

    @Test
    public void test7x5CircleArrayHasCorrectOutput() {
        Field field = new CircleField(7, 5);
        String[][] CircleArray7x5 ={
                {"-1", "-1", " 0" , "-1", "-1"},
                {"-1", " 0", " 0", " 0", "-1"},
                {" 0", " 0", " 0", " 0", " 0"},
                {" 0", " 0", " 0", " 0" ," 0"},
                {" 0", " 0", " 0", " 0" ," 0"},
                {"-1", " 0", " 0", " 0", "-1",},
                {"-1", "-1", " 0", "-1", "-1",},
        };
        assertArrayEquals(CircleArray7x5, field.getField());
    }

    @Test
    public void test4x10CircleArrayHasCorrectOutput() {
        Field field = new CircleField(4, 10);
        String[][] CircleArray4x10 = {
                {"-1", "-1", " 0", " 0", " 0", " 0", " 0", " 0", "-1", "-1"},
                {"-1", " 0", " 0", " 0", " 0", " 0", " 0", " 0", " 0", "-1"},
                {"-1", " 0", " 0", " 0", " 0", " 0", " 0", " 0"," 0", "-1",},
                {"-1", "-1", " 0", " 0", " 0", " 0", " 0", " 0", "-1", "-1",}
        };
        assertArrayEquals(CircleArray4x10, field.getField());
    }
}
