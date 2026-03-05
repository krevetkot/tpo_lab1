package commands;

import modules.Ship;

public class FlyCommand implements Command {
    private final Ship object;

    public FlyCommand(Ship ship){
        this.object = ship;
    }

    @Override
    public void execute() {
        System.out.println(object.getName() + " летит " + object.getTrackType().getTitle());
    }
}
