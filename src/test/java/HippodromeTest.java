import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {
    @Test
    public void nullHorsesListException(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }
    @Test
    public void emptyHorsesListException(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(List.of()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }
    @Test
    public void getHorsesTest(){
        List<Horse> listTest = new ArrayList<>();
        for (int i = 1; i < 31; i++) {
            listTest.add(new Horse("name"+ i, i, 31-i));
        }
        Hippodrome horses = new Hippodrome(listTest);
        assertEquals(listTest, horses.getHorses());
    }
    @Test
    public void moveTest(){
        List<Horse> listTest = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            listTest.add(mock(Horse.class));
        }
        new Hippodrome(listTest).move();
        for (Horse horse : listTest){
            verify(horse).move();
        }
    }
    @Test
    public void getWinnerTest(){
        Horse h1 = new Horse("zxc", 1, 1);
        Horse h2 = new Horse("zxc", 1, 2);
        Horse h3 = new Horse("zxc", 1, 3);
        assertEquals(h3, new Hippodrome(List.of(h1, h2, h3)).getWinner());
    }
}
