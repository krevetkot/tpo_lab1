package modules;

@Getter
public class Human {
    protected String name;
    protected Integer age;
    public Human(String name, Integer age){
        this.name = name;
        this.age = age;
    }

    public String getName(){
        return this.name;
    }

}
