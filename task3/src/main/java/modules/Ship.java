package modules;

import enums.Color;
import enums.TrackType;
import java.util.HashSet;
import java.util.Set;


public class Ship {

    public enum Target { ROCKETS }
    private final String name;
    private boolean shaking;
    private TrackType trackType = TrackType.FORWARD;
    private Target target;
    private Coordinates coordinates;

    public Ship(String name) {
        this.name = name;
        coordinates = new Coordinates(0, 0, 0);
    }
    public Ship(String name, Coordinates coordinates){
        this.name = name;
        this.coordinates = coordinates;
    }

    public String getName() { return name; }
    public boolean isShaking() { return shaking; }
    public TrackType getTrackType() { return trackType; }

    public Target getTarget() { return target; }
    public void setTarget(Target target) { this.target = target; }
    public Coordinates getCoordinates(){ return coordinates; }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void applyHandles(Human human) {
        Set<Color> types = new HashSet<>();
        for (Handle handle : human.getGrabbedHandles()) {
            if (handle != null && handle.isGrabbed()) {
                types.add(handle.getType());
                applyHandle(handle);
            }
        }

        this.shaking = types.size() >= 2;

        Handle last = human.getLastGrabbedHandle();
        if (last != null && last.isGrabbed()) {
            this.trackType = last.getTrackEffect();
        }
    }

    public void applyHandle(Handle handle){
        double dist = handle.getTrackEffectDistance();
        switch (handle.getTrackEffect()){
            case LEFT:
                goLeft(dist);
                break;
            case RIGHT:
                goRight(dist);
                break;
            case FORWARD:
                goForward(dist);
                break;
            case BACKWARD:
                goBackward(dist);
                break;
            case UP:
                goUp(dist);
                break;
            case DOWN:
                goDown(dist);
                break;
        }
    }

    public void goRight(double dist){
        coordinates.changeX(dist);
    }
    public void goLeft(double dist){
        coordinates.changeX(-dist);
    }
    public void goForward(double dist){
        coordinates.changeY(dist);
    }
    public void goBackward(double dist){
        coordinates.changeY(-dist);
    }
    public void goUp(double dist){
        coordinates.changeZ(dist);
    }
    public void goDown(double dist){
        coordinates.changeZ(-dist);
    }
}