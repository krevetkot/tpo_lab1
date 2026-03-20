import commands.FlyCommand;
import commands.PushCommand;
import enums.Color;
import enums.TrackType;
import modules.Handle;
import modules.Human;
import modules.Ship;

public class App {
    public static void main(String[] args) {
        Human ford = new Human("Форд", 35);
        Ship ship = new Ship("Корабль");

        Handle blue  = new Handle(Color.BLUE,  TrackType.STRAIGHT);
        Handle red   = new Handle(Color.RED,   TrackType.ARC);
        Handle green = new Handle(Color.GREEN, TrackType.AROUND);

        FlyCommand fly = new FlyCommand(ship);

        new PushCommand(ford, ship, blue).execute();
        fly.execute();

        new PushCommand(ford, ship, red).execute();
        fly.execute();

        ford.releaseHalf();
        ship.applyHandles(ford);
        System.out.println("После отпускания половины рукояток: траектория: " + ship.getTrackType().getTitle()
                + "; корабль трясёт? " + ship.isShaking());

        new PushCommand(ford, ship, green).execute();
        fly.execute();

        ship.setTarget(Ship.Target.ROCKETS);
        System.out.println("Цель: " + ship.getTarget());
    }
}