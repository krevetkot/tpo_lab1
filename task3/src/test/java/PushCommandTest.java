import commands.PushCommand;
import enums.Color;
import modules.Coordinates;
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
        Ship ship = new Ship("Корабль", new Coordinates(0, 0, 0));
        Handle green = new Handle(Color.GREEN, TrackType.RIGHT);

        PushCommand command = new PushCommand(ford, ship, green);
        command.execute();

        assertTrue(green.isGrabbed());
        assertTrue(ford.getGrabbedHandles().contains(green));
        assertEquals(TrackType.RIGHT, ship.getTrackType());
        assertFalse(ship.isShaking());
        assertEquals(1.0, ship.getCoordinates().getX());
    }


    @Test
    @DisplayName("PushCommand with different handle types makes ship shake")
    void pushCommandDifferentTypesCauseShakingTest() {
        Human ford = new Human("Форд", 35);
        Ship ship = new Ship("Корабль", new Coordinates(0, 0, 0));
        Handle blue = new Handle(Color.BLUE, TrackType.FORWARD);
        Handle red = new Handle(Color.RED, TrackType.UP);

        PushCommand command = new PushCommand(ford, ship, blue, red);
        command.execute();

        assertTrue(ship.isShaking());
        assertEquals(TrackType.UP, ship.getTrackType());
        assertEquals(1.0, ship.getCoordinates().getY());
        assertEquals(1.0, ship.getCoordinates().getZ());
    }
}