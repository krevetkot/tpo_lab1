package modules;

import enums.Color;
import enums.TrackType;
import java.util.HashSet;
import java.util.Set;


public class Ship {

    public enum Target { ROCKETS }
    private final String name;
    private boolean shaking;
    private TrackType trackType = TrackType.STRAIGHT;
    private Target target;

    public Ship(String name) { this.name = name; }

    public String getName() { return name; }
    public boolean isShaking() { return shaking; }
    public TrackType getTrackType() { return trackType; }

    public Target getTarget() { return target; }
    public void setTarget(Target target) { this.target = target; }

    public void applyHandles(Human human) {
        Set<Color> types = new HashSet<>();
        for (Handle h : human.getGrabbedHandles()) {
            if (h != null && h.isGrabbed()) {
                types.add(h.getType());
            }
        }

        this.shaking = types.size() >= 2;

        Handle last = human.getLastGrabbedHandle();
        if (last != null && last.isGrabbed()) {
            this.trackType = last.getTrackEffect();
        }
    }
}