package commands;

import modules.Handle;
import modules.Human;
import modules.Ship;

import java.util.Objects;

public class PushCommand implements Command {

    private final Human human;
    private final Ship ship;
    private final Handle[] handles;

    public PushCommand(Human human, Ship ship, Handle... handles) {
        this.human = Objects.requireNonNull(human);
        this.ship = Objects.requireNonNull(ship);
        this.handles = handles;
    }

    @Override
    public void execute() {
        human.grab(handles);
        ship.applyHandles(human);
        System.out.println(human.getName()
                + " схватился за рукоятки; траектория: "
                + ship.getTrackType().getTitle()
                + "; корабль трясёт? "
                + ship.isShaking());
    }
}