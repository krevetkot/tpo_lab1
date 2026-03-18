import enums.Color;
import enums.TrackType;
import modules.Handle;
import modules.Human;
import modules.Ship;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShipTest {

    @Test
    @DisplayName("Ship is created with initial state")
    void shipCreationTest() {
        Ship ship = new Ship("Корабль");

        assertEquals("Корабль", ship.getName());
        assertEquals(TrackType.STRAIGHT, ship.getTrackType());
        assertFalse(ship.isShaking());
        assertNull(ship.getTarget());
    }

    @Test
    @DisplayName("One handle does not make the ship shake")
    void oneHandleNoShakingTest() {
        Human ford = new Human("Форд", 35);
        Ship ship = new Ship("Корабль");
        Handle blue = new Handle(Color.BLUE, TrackType.STRAIGHT);

        ford.grab(blue);
        ship.applyHandles(ford);

        assertFalse(ship.isShaking());
        assertEquals(TrackType.STRAIGHT, ship.getTrackType());
    }

    @Test
    @DisplayName("Different handle types make the ship shake")
    void differentHandleTypesCauseShakingTest() {
        Human ford = new Human("Форд", 35);
        Ship ship = new Ship("Корабль");
        Handle blue = new Handle(Color.BLUE, TrackType.STRAIGHT);
        Handle red = new Handle(Color.RED, TrackType.ARC);

        ford.grab(blue, red);
        ship.applyHandles(ford);

        assertTrue(ship.isShaking());
    }

    @Test
    @DisplayName("Last grabbed handle defines ship track type")
    void lastGrabbedHandleDefinesTrackTypeTest() {
        Human ford = new Human("Форд", 35);
        Ship ship = new Ship("Корабль");
        Handle blue = new Handle(Color.BLUE, TrackType.STRAIGHT);
        Handle red = new Handle(Color.RED, TrackType.ARC);

        ford.grab(blue);
        ship.applyHandles(ford);
        assertEquals(TrackType.STRAIGHT, ship.getTrackType());

        ford.grab(red);
        ship.applyHandles(ford);
        assertEquals(TrackType.ARC, ship.getTrackType());
    }

    @Test
    @DisplayName("After releasing half the ship may stop shaking")
    void releaseHalfStopsShakingTest() {
        Human ford = new Human("Форд", 35);
        Ship ship = new Ship("Корабль");
        Handle blue = new Handle( Color.BLUE, TrackType.STRAIGHT);
        Handle red = new Handle(Color.RED, TrackType.ARC);

        ford.grab(blue, red);
        ship.applyHandles(ford);
        assertTrue(ship.isShaking());

        ford.releaseHalf();
        ship.applyHandles(ford);

        assertFalse(ship.isShaking());
    }

    @Test
    @DisplayName("Ship target can be set")
    void setTargetTest() {
        Ship ship = new Ship("Корабль");

        ship.setTarget(Ship.Target.ROCKETS);

        assertEquals(Ship.Target.ROCKETS, ship.getTarget());
    }
}