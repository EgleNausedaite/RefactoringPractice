package com.linkedin.kata;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RomanTests {
    @Test
    public void junitWorks()
    {
        assertTrue(true);
    }

    @Test
    public void convertsSingleDigitRoman()
    {
        assertEquals(1, Roman.convert("I"));
        assertEquals(5, Roman.convert("V"));
        assertEquals(10, Roman.convert("X"));
        assertEquals(50, Roman.convert("L"));
        assertEquals(100, Roman.convert("C"));
        assertEquals(500, Roman.convert("D"));
        assertEquals(1000, Roman.convert("M"));
    }

    public void romanNumeralAddition()
    {
        assertEquals(2, Roman.convert("II"));
    }

    public void romanNumeralToArabicNumeralInWords()
    {
        assertEquals(12, Roman.romanToArabicInWords("XII"));
        assertEquals(56, Roman.romanToArabicInWords("LVI"));

    }


}
