import enums.Color;
import modules.Coordinates;
import enums.TrackType;
import modules.Handle;
import modules.Human;
import modules.Ship;
import commands.ReleaseCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReleaseCommandTest {
    @Test
    @DisplayName("Releasing half of grabbed handles sets rockets as target")
    void releaseHalfSetsRocketsTargetTest() {
        Human ford = new Human("Форд", 35);
        Ship ship = new Ship("Корабль", new Coordinates(0, 0, 0));

        Handle h1 = new Handle(Color.BLUE, TrackType.FORWARD);
        Handle h2 = new Handle(Color.RED, TrackType.UP);

        ford.grab(h1, h2);
        ship.applyHandles(ford);
        assertTrue(ship.isShaking());

        ReleaseCommand command = new ReleaseCommand(ford, ship, h1);
        command.execute();

        assertEquals(Ship.Target.ROCKETS, ship.getTarget());
        assertFalse(h1.isGrabbed());
        assertTrue(h2.isGrabbed());
        assertFalse(ship.isShaking());
    }

    @Test
    @DisplayName("Releasing less than half does not set rockets target")
    void releaseLessThanHalfDoesNotSetTargetTest() {
        Human ford = new Human("Форд", 35);
        Ship ship = new Ship("Корабль", new Coordinates(0, 0, 0));

        Handle h1 = new Handle(Color.BLUE, TrackType.FORWARD);
        Handle h2 = new Handle(Color.RED, TrackType.UP);
        Handle h3 = new Handle(Color.GREEN, TrackType.LEFT);

        ford.grab(h1, h2, h3);

        ReleaseCommand command = new ReleaseCommand(ford, ship, h1);
        command.execute();

        assertEquals(2, ford.getGrabbedHandles().size());
        assertNull(ship.getTarget());
    }
}
