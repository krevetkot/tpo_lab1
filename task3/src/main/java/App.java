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

        Handle blue  = new Handle("Синяя рукоятка", Color.BLUE,  TrackType.STRAIGHT);
        Handle red   = new Handle("Красная рукоятка",Color.RED,   TrackType.ARC);
        Handle green = new Handle("Зелёная рукоятка",Color.GREEN, TrackType.AROUND);

        FlyCommand fly = new FlyCommand(ship);

        // Последняя схваченная определяет траекторию
        new PushCommand(ford, ship, blue).execute();
        fly.execute();

        // Два разных типа -> "трясёт"
        new PushCommand(ford, ship, red).execute();
        fly.execute();

        // Отпустил половину
        ford.releaseHalf();
        ship.applyHandles(ford);
        System.out.println("После отпускания половины: траектория: " + ship.getTrackType().getTitle()
                + "; корабль трясёт? " + ship.isShaking());

        // Схватил зелёную последней -> AROUND
        new PushCommand(ford, ship, green).execute();
        fly.execute();

        // Навстречу ракетам
        ship.setTarget(Ship.Target.ROCKETS);
        System.out.println("Цель: " + ship.getTarget());
    }
}