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
        Handle blue = new Handle(Color.BLUE, TrackType.FORWARD);

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
        Handle blue = new Handle(Color.BLUE, TrackType.FORWARD);
        Handle red = new Handle(Color.RED, TrackType.UP);

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
        Handle blue = new Handle(Color.BLUE, TrackType.FORWARD);
        Handle red = new Handle(Color.RED, TrackType.UP);

        ford.grab(blue, red);
        assertEquals(red, ford.getLastGrabbedHandle());

        ford.grab(blue);

        assertEquals(blue, ford.getLastGrabbedHandle());
        assertEquals(2, ford.getGrabbedHandles().size());
    }


    @Test
    @DisplayName("Release returns number of actually released handles")
    void releaseGrabbedHandlesTest() {
        Human ford = new Human("Форд", 35);
        Handle blue = new Handle(Color.BLUE, TrackType.FORWARD);
        Handle red = new Handle(Color.RED, TrackType.UP);

        ford.grab(blue, red);

        int released = ford.release(blue);

        assertEquals(1, released);
        assertFalse(blue.isGrabbed());
        assertTrue(red.isGrabbed());
        assertEquals(1, ford.getGrabbedHandles().size());
        assertFalse(ford.getGrabbedHandles().contains(blue));
        assertEquals(red, ford.getLastGrabbedHandle());
    }

    @Test
    @DisplayName("Release ignores null handles")
    void releaseNullHandleTest() {
        Human ford = new Human("Форд", 35);
        Handle blue = new Handle(Color.BLUE, TrackType.FORWARD);

        ford.grab(blue);

        int released = ford.release(null, blue, null);

        assertEquals(1, released);
        assertTrue(ford.getGrabbedHandles().isEmpty());
        assertFalse(blue.isGrabbed());
    }

    @Test
    @DisplayName("Release from empty set returns 0")
    void releaseHalfEmptyTest() {
        Human ford = new Human("Форд", 35);

        int released = ford.release();

        assertEquals(0, released);
        assertTrue(ford.getGrabbedHandles().isEmpty());
    }

    @Test
    @DisplayName("Releasing a handle that was not grabbed returns zero")
    void releaseNotGrabbedHandleTest() {
        Human ford = new Human("Форд", 35);
        Handle blue = new Handle(Color.BLUE, TrackType.FORWARD);

        int released = ford.release(blue);

        assertEquals(0, released);
        assertFalse(blue.isGrabbed());
        assertTrue(ford.getGrabbedHandles().isEmpty());
    }
}