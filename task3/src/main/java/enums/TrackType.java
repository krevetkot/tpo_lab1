package enums;

public enum TrackType {
    STRAIGHT("прямо"),
    ARC("по дуге"),
    AROUND("по кругу");

    private final String title;

    TrackType(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
