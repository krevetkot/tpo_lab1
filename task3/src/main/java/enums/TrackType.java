package enums;

public enum TrackType {
    LEFT("лево"),
    RIGHT("право"),
    FORWARD("вперед"),
    BACKWARD("назад"),
    UP("вверх"),
    DOWN("вниз");

    private final String title;

    TrackType(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
