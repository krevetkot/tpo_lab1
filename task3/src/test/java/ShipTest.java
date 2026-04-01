import enums.Color;
import modules.Coordinates;
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
        assertEquals(TrackType.FORWARD, ship.getTrackType());
        assertFalse(ship.isShaking());
        assertNull(ship.getTarget());
        assertEquals(0, ship.getCoordinates().getX());
        assertEquals(0, ship.getCoordinates().getY());
        assertEquals(0, ship.getCoordinates().getZ());
    }

    @Test
    @DisplayName("Ship can be created with coordinates")
    void shipCreationWithCoordinatesTest() {
        Coordinates coordinates = new Coordinates(0, 0, 0);
        Ship ship = new Ship("Корабль", coordinates);

        assertEquals(coordinates, ship.getCoordinates());
    }

    @Test
    @DisplayName("Ship coordinates can be changed")
    void setCoordinatesTest() {
        Ship ship = new Ship("Корабль");
        Coordinates coordinates = new Coordinates(10, 20, 30);

        ship.setCoordinates(coordinates);

        assertEquals(coordinates, ship.getCoordinates());
    }


    @Test
    @DisplayName("One handle does not make the ship shake")
    void oneHandleNoShakingTest() {
        Human ford = new Human("Форд", 35);
        Ship ship = new Ship("Корабль");
        Handle blue = new Handle(Color.BLUE, TrackType.FORWARD);

        ford.grab(blue);
        ship.applyHandles(ford);

        assertFalse(ship.isShaking());
        assertEquals(TrackType.FORWARD, ship.getTrackType());
    }

    @Test
    @DisplayName("Different handle types make the ship shake")
    void differentHandleTypesCauseShakingTest() {
        Human ford = new Human("Форд", 35);
        Ship ship = new Ship("Корабль");
        Handle blue = new Handle(Color.BLUE, TrackType.FORWARD);
        Handle red = new Handle(Color.RED, TrackType.UP);

        ford.grab(blue, red);
        ship.applyHandles(ford);

        assertTrue(ship.isShaking());
    }

    @Test
    @DisplayName("Last grabbed handle defines ship track type")
    void lastGrabbedHandleDefinesTrackTypeTest() {
        Human ford = new Human("Форд", 35);
        Ship ship = new Ship("Корабль");
        Handle blue = new Handle(Color.BLUE, TrackType.FORWARD);
        Handle red = new Handle(Color.RED, TrackType.UP);

        ford.grab(blue);
        ship.applyHandles(ford);
        assertEquals(TrackType.FORWARD, ship.getTrackType());

        ford.grab(red);
        ship.applyHandles(ford);
        assertEquals(TrackType.UP, ship.getTrackType());
    }

    @Test
    @DisplayName("Ship target can be set")
    void setTargetTest() {
        Ship ship = new Ship("Корабль");

        ship.setTarget(Ship.Target.ROCKETS);

        assertEquals(Ship.Target.ROCKETS, ship.getTarget());
    }

    @Test
    @DisplayName("Ship moves left")
    void goLeftTest() {
        Ship ship = new Ship("Корабль", new Coordinates(0, 0, 0));
        Handle handle = new Handle(Color.BLUE, TrackType.LEFT);
        handle.setTrackEffectDistance(2.0);

        ship.applyHandle(handle);

        assertEquals(-2.0, ship.getCoordinates().getX());
        assertEquals(0.0, ship.getCoordinates().getY());
        assertEquals(0.0, ship.getCoordinates().getZ());
    }

    @Test
    @DisplayName("Ship moves right")
    void goRightTest() {
        Ship ship = new Ship("Корабль", new Coordinates(0, 0, 0));
        Handle handle = new Handle(Color.BLUE, TrackType.RIGHT);
        handle.setTrackEffectDistance(2.0);

        ship.applyHandle(handle);

        assertEquals(2.0, ship.getCoordinates().getX());
    }

    @Test
    @DisplayName("Ship moves forward")
    void goForwardTest() {
        Ship ship = new Ship("Корабль", new Coordinates(0, 0, 0));
        Handle handle = new Handle(Color.BLUE, TrackType.FORWARD);
        handle.setTrackEffectDistance(3.0);

        ship.applyHandle(handle);

        assertEquals(3.0, ship.getCoordinates().getY());
    }

    @Test
    @DisplayName("Ship moves backward")
    void goBackwardTest() {
        Ship ship = new Ship("Корабль", new Coordinates(0, 0, 0));
        Handle handle = new Handle(Color.BLUE, TrackType.BACKWARD);
        handle.setTrackEffectDistance(3.0);

        ship.applyHandle(handle);

        assertEquals(-3.0, ship.getCoordinates().getY());
    }

    @Test
    @DisplayName("Ship moves up")
    void goUpTest() {
        Ship ship = new Ship("Корабль", new Coordinates(0, 0, 0));
        Handle handle = new Handle(Color.BLUE, TrackType.UP);
        handle.setTrackEffectDistance(4.0);

        ship.applyHandle(handle);

        assertEquals(4.0, ship.getCoordinates().getZ());
    }

    @Test
    @DisplayName("Ship moves down")
    void goDownTest() {
        Ship ship = new Ship("Корабль", new Coordinates(0, 0, 0));
        Handle handle = new Handle(Color.BLUE, TrackType.DOWN);
        handle.setTrackEffectDistance(4.0);

        ship.applyHandle(handle);

        assertEquals(-4.0, ship.getCoordinates().getZ());
    }
}