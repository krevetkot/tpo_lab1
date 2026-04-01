package enums;

public enum Color {
    RED("Красная"),
    BLUE("Синяя"),
    GREEN("Зеленая"),
    BLACK("Черная"),
    YELLOW("Желтая");

    private final String name;
    Color(String colorName){
        this.name = colorName;
    }

    public String getName(){
        return name;
    }
}
