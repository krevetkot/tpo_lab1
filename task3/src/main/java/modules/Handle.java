package modules;

import enums.Color;
import enums.TrackType;


public class Handle {
    private final String name;
    private final Color type;
    private final TrackType trackEffect;
    private boolean grabbed;

    public Handle(Color type, TrackType trackEffect) {
        this.name = type.getName() + " рукоятка";
        this.type = type;
        this.trackEffect = trackEffect;
        this.grabbed = false;

    }

    public String getName() { return name; }
    public Color getType() { return type; }
    public TrackType getTrackEffect() { return trackEffect; }

    public boolean isGrabbed() { return grabbed; }
    public void setGrabbed(boolean grabbed) { this.grabbed = grabbed; }

}