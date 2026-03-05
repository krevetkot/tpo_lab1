package enums;

public enum Color {
    RED("красный"),
    BLUE("синий"),
    GREEN("зеленый"),
    BLACK("черный");

    private final String name;
    Color(String colorName){
        this.name = colorName;
    }

    public String getName(){
        return name;
    }
}
