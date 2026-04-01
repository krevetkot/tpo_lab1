import enums.Color;
import enums.TrackType;
import modules.Handle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandleTest {

    @Test
    @DisplayName("Handle is created with correct properties")
    void handleCreationTest() {
        Handle handle = new Handle(Color.BLUE, TrackType.FORWARD);

        assertEquals("Синяя рукоятка", handle.getName());
        assertEquals(Color.BLUE, handle.getType());
        assertEquals(TrackType.FORWARD, handle.getTrackEffect());
        assertFalse(handle.isGrabbed());
        assertEquals(1, handle.getTrackEffectDistance());
    }

    @Test
    @DisplayName("Handle grabbed state can be changed")
    void handleGrabbedStateTest() {
        Handle handle = new Handle(Color.RED, TrackType.UP);

        handle.setGrabbed(true);
        assertTrue(handle.isGrabbed());

        handle.setGrabbed(false);
        assertFalse(handle.isGrabbed());
    }


    @Test
    @DisplayName("Handle track effect distance can be changed")
    void handleDistanceTest() {
        Handle handle = new Handle(Color.GREEN, TrackType.LEFT);

        handle.setTrackEffectDistance(3.5);
        assertEquals(3.5, handle.getTrackEffectDistance());
    }
}