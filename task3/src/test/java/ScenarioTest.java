import commands.PushCommand;
import commands.ReleaseCommand;
import enums.Color;
import modules.Coordinates;
import enums.TrackType;
import modules.Handle;
import modules.Human;
import modules.Ship;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScenarioTest {

    @Test
    @DisplayName("Ford grabs several handles, ship shakes, then releases half and heads toward rockets")
    void mainScenarioTest() {
        Human ford = new Human("Форд", 35);
        Ship ship = new Ship("Корабль", new Coordinates(0, 0, 0));

        Handle forward = new Handle(Color.BLUE, TrackType.FORWARD);
        Handle up = new Handle(Color.RED, TrackType.UP);
        Handle right = new Handle(Color.GREEN, TrackType.RIGHT);
        Handle left = new Handle(Color.YELLOW, TrackType.LEFT);

        new PushCommand(ford, ship, forward, up, right, left).execute();

        assertEquals(4, ford.getGrabbedHandles().size());
        assertTrue(ship.isShaking());

        new ReleaseCommand(ford, ship, up, right, left).execute();

        assertEquals(1, ford.getGrabbedHandles().size());
        assertEquals(Ship.Target.ROCKETS, ship.getTarget());
        assertFalse(ship.isShaking());
    }
}