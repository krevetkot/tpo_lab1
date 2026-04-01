package modules;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Human {

    private final String name;
    private Integer age;

    private final LinkedHashSet<Handle> grabbedHandles = new LinkedHashSet<>();

    public Human(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }


    public Set<Handle> getGrabbedHandles() {
        return Set.copyOf(grabbedHandles);
    }

    public Handle getLastGrabbedHandle() {
        Handle last = null;
        for (Handle h : grabbedHandles) last = h;
        return last;
    }

    public void grab(Handle... handles) {
        for (Handle h : handles) {
            if (h == null) continue;

            grabbedHandles.remove(h);
            grabbedHandles.add(h);

            h.setGrabbed(true);
        }
    }

    public int release(Handle... handles){
        int releasedSize = 0;
        for (Handle h : handles) {
            if (h == null) continue;

            if (grabbedHandles.remove(h)) {
                h.setGrabbed(false);
                releasedSize++;
            }
        }
        return releasedSize;
    }
}