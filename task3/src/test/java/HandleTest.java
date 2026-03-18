import enums.Color;
import enums.TrackType;
import modules.Handle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HandleTest {

    @Test
    @DisplayName("Handle is created with correct properties")
    void handleCreationTest() {
        Handle handle = new Handle(Color.BLUE, TrackType.STRAIGHT);

        assertEquals("Синяя рукоятка", handle.getName());
        assertEquals(Color.BLUE, handle.getType());
        assertEquals(TrackType.STRAIGHT, handle.getTrackEffect());
        assertFalse(handle.isGrabbed());
    }

    @Test
    @DisplayName("Handle grabbed state can be changed")
    void handleGrabbedStateTest() {
        Handle handle = new Handle(Color.RED, TrackType.ARC);

        handle.setGrabbed(true);
        assertTrue(handle.isGrabbed());

        handle.setGrabbed(false);
        assertFalse(handle.isGrabbed());
    }
}