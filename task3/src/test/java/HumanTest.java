import enums.Color;
import enums.TrackType;
import modules.Handle;
import modules.Human;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HumanTest {

    @Test
    @DisplayName("Human is created with correct fields")
    void humanCreationTest() {
        Human ford = new Human("Форд", 35);

        assertEquals("Форд", ford.getName());
        assertEquals(35, ford.getAge());
        assertTrue(ford.getGrabbedHandles().isEmpty());
        assertNull(ford.getLastGrabbedHandle());
    }

    @Test
    @DisplayName("Human grabs one handle")
    void grabOneHandleTest() {
        Human ford = new Human("Форд", 35);
        Handle blue = new Handle(Color.BLUE, TrackType.STRAIGHT);

        ford.grab(blue);

        assertTrue(blue.isGrabbed());
        assertEquals(1, ford.getGrabbedHandles().size());
        assertTrue(ford.getGrabbedHandles().contains(blue));
        assertEquals(blue, ford.getLastGrabbedHandle());
    }

    @Test
    @DisplayName("Human grabs several handles and last grabbed is remembered")
    void grabSeveralHandlesTest() {
        Human ford = new Human("Форд", 35);
        Handle blue = new Handle(Color.BLUE, TrackType.STRAIGHT);
        Handle red = new Handle(Color.RED, TrackType.ARC);

        ford.grab(blue, red);

        assertEquals(2, ford.getGrabbedHandles().size());
        assertTrue(blue.isGrabbed());
        assertTrue(red.isGrabbed());
        assertEquals(red, ford.getLastGrabbedHandle());
    }

    @Test
    @DisplayName("Re-grabbing handle makes it the last grabbed one")
    void regrabHandleTest() {
        Human ford = new Human("Форд", 35);
        Handle blue = new Handle(Color.BLUE, TrackType.STRAIGHT);
        Handle red = new Handle(Color.RED, TrackType.ARC);

        ford.grab(blue, red);
        assertEquals(red, ford.getLastGrabbedHandle());

        ford.grab(blue);

        assertEquals(blue, ford.getLastGrabbedHandle());
        assertEquals(2, ford.getGrabbedHandles().size());
    }

    @Test
    @DisplayName("Release half releases one handle out of two")
    void releaseHalfTwoHandlesTest() {
        Human ford = new Human("Форд", 35);
        Handle blue = new Handle(Color.BLUE, TrackType.STRAIGHT);
        Handle red = new Handle(Color.RED, TrackType.ARC);

        ford.grab(blue, red);
        List<Handle> released = ford.releaseHalf();

        assertEquals(1, released.size());
        assertEquals(1, ford.getGrabbedHandles().size());
        assertFalse(released.get(0).isGrabbed());
    }

    @Test
    @DisplayName("Release half from empty set returns empty list")
    void releaseHalfEmptyTest() {
        Human ford = new Human("Форд", 35);

        List<Handle> released = ford.releaseHalf();

        assertTrue(released.isEmpty());
        assertTrue(ford.getGrabbedHandles().isEmpty());
    }
}