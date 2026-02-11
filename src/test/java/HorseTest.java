import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {

    @Test
    public void nullNameException(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
        assertEquals("Name cannot be null.", exception.getMessage());
    }
    @ParameterizedTest
    @MethodSource("argsProviderFactory")
    void spaceNameException(String argument) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(argument, 1, 1));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }
    static Stream<String> argsProviderFactory() {
        return Stream.of("", " ",  "    ", "\n");
    }
    @Test
    public void negativeSpeedException(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Bob", -1, 1));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }
    @Test
    public void negativeDistanceException(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Bob", 1, -1));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }
    @Test
    public void getNameTest(){
        Horse testHorse = new Horse("TestName", 1, 2);
        assertEquals("TestName", testHorse.getName());
    }
    @Test
    public void getSpeedTest(){
        Horse testHorse = new Horse("TestName", 1, 2);
        assertEquals(1, testHorse.getSpeed());
    }
    @Test
    public void getDistanceTest(){
        Horse testHorse = new Horse("TestName", 1, 2);
        assertEquals(2, testHorse.getDistance());
    }
    @Test
    public void zeroDistanceTest(){
        Horse testHorse = new Horse("TestName", 1);
        assertEquals(0, testHorse.getDistance());
    }
    @Test
    void getRandomDoubleTest(){
        try (MockedStatic<Horse> mockObject = mockStatic(Horse.class)){
            new Horse("zxc", 1, 2).move();
            mockObject.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }catch (Exception e){
        e.printStackTrace();
    }
    }
    @ParameterizedTest
    @ValueSource(doubles = {0.4, 0.5, 0.8})
    void getRandomDoubleFormulaTest(double mockValue){
        try(MockedStatic<Horse> mockObject = mockStatic(Horse.class)) {
            mockObject.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(mockValue);
            Horse horse = new Horse("zxc", 1, 2);
            horse.move();
            mockObject.verify(() -> Horse.getRandomDouble(0.2, 0.9));
            assertEquals(2+1*mockValue, horse.getDistance());
        }catch (Exception e){
        e.printStackTrace();
    }

    }
}
