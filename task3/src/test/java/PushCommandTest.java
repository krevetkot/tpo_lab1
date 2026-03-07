import commands.PushCommand;
import enums.Color;
import enums.TrackType;
import modules.Handle;
import modules.Human;
import modules.Ship;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PushCommandTest {

    @Test
    @DisplayName("PushCommand grabs handle and updates ship state")
    void pushCommandOneHandleTest() {
        Human ford = new Human("Форд", 35);
        Ship ship = new Ship("Корабль");
        Handle green = new Handle("Зелёная рукоятка", Color.GREEN, TrackType.AROUND);

        PushCommand command = new PushCommand(ford, ship, green);
        command.execute();

        assertTrue(green.isGrabbed());
        assertTrue(ford.getGrabbedHandles().contains(green));
        assertEquals(TrackType.AROUND, ship.getTrackType());
        assertFalse(ship.isShaking());
    }

    @Test
    @DisplayName("PushCommand with different handle types makes ship shake")
    void pushCommandDifferentTypesCauseShakingTest() {
        Human ford = new Human("Форд", 35);
        Ship ship = new Ship("Корабль");
        Handle blue = new Handle("Синяя рукоятка", Color.BLUE, TrackType.STRAIGHT);
        Handle red = new Handle("Красная рукоятка", Color.RED, TrackType.ARC);

        PushCommand command = new PushCommand(ford, ship, blue, red);
        command.execute();

        assertTrue(ship.isShaking());
        assertEquals(TrackType.ARC, ship.getTrackType());
    }
}