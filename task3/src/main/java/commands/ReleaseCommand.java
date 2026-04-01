package commands;

import modules.Handle;
import modules.Human;
import modules.Ship;

import java.util.Objects;

public class ReleaseCommand implements Command{
    private final Human human;
    private final Ship ship;
    private final Handle[] handles;

    public ReleaseCommand(Human human, Ship ship, Handle... handles) {
        this.human = Objects.requireNonNull(human);
        this.ship = Objects.requireNonNull(ship);
        this.handles = handles;
    }

    @Override
    public void execute() {
        int grabbedSize = human.getGrabbedHandles().size();
        int releasedSize = human.release(handles);

        ship.applyHandles(human);

        if (grabbedSize > 0 && releasedSize * 2 >= grabbedSize) {
            ship.setTarget(Ship.Target.ROCKETS);
        }
    }
}
