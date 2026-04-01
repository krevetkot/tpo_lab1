import commands.FlyCommand;
import modules.Coordinates;
import modules.Ship;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlyCommandTest {
    @Test
    @DisplayName("FlyCommand prints ship name and direction")
    void flyCommandPrintTest() {
        Ship ship = new Ship("Корабль", new Coordinates(0, 0, 0));
        FlyCommand command = new FlyCommand(ship);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8));
            command.execute();
        } finally {
            System.setOut(originalOut);
        }

        String printed = out.toString(StandardCharsets.UTF_8);
        assertTrue(printed.contains("Корабль"));
        assertTrue(printed.contains("летит"));
    }
}
