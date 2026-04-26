package e1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringGamesTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void bestCharactersTest() {
        assertEquals("a12345", StringGames.bestCharacters("ABC123", "a12345"));
        assertEquals("IAlwaysWin123", StringGames.bestCharacters("IAlwaysWin123","ilostyetagain"));
        assertEquals("aB0", StringGames.bestCharacters("aaa", "aB0"));
        assertEquals("XyZ", StringGames.bestCharacters("XyZ", "123"));
        assertEquals("aaa", StringGames.bestCharacters("aaa", "bbb"));
        assertThrows(IllegalArgumentException.class, () -> StringGames.bestCharacters("abc", "ab"));
    }

    @Test
    void crossingWordsTest() {
        assertEquals(2, StringGames.crossingWords("abc", "bcd"));
        assertEquals(4, StringGames.crossingWords("abcd", "abcd"));
        assertEquals(9, StringGames.crossingWords("zzz", "zzz"));
        assertEquals(0, StringGames.crossingWords("house", "lack"));
        assertEquals(3, StringGames.crossingWords("house", "bongos"));
    }

    /**
    * wackyAlphabet example:
    * wackyAlphabet("hello", "zyxwvutsrqponmlkjihgfedcba") must return "ollhe"
    */
    @Test
    void wackyAlphabetTest() {
        assertEquals("ollhe", StringGames.wackyAlphabet("hello", "zyxwvutsrqponmlkjihgfedcba"));
        assertEquals("hello", StringGames.wackyAlphabet("hello", "abcdfgijkmnhelopqrtsuvwxyz"));
        assertEquals("aaegnrrty", StringGames.wackyAlphabet("targaryen", "aeioubcdfghjklmnpqrstvwxyz"));
        assertEquals("eioghhrtw", StringGames.wackyAlphabet("hightower", "aeioubcdfghjklmnpqrstvwxyz"));
        assertEquals("ytrrngeaa", StringGames.wackyAlphabet("targaryen", "zyxwvutsrqponmlkjihgfedcba"));
        assertThrows(IllegalArgumentException.class, () -> StringGames.wackyAlphabet("allas", "abcdafghajklmnapqrtsavwxyz"));
        assertThrows(IllegalArgumentException.class, () -> StringGames.wackyAlphabet("onlyas", "aaaaaaaaaaaaaaaaaaaaaaaaaa"));
        assertThrows(IllegalArgumentException.class, () -> StringGames.wackyAlphabet("novowels", "bcdfgjkmnhlpqrtsvwxyz"));
        assertThrows(IllegalArgumentException.class, () -> StringGames.wackyAlphabet("numbers", "0bcd1fgjkmn2hl3pqrts4vwxyz"));
    }
}