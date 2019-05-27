package cc.stan.sample.gradlemultiprojectsample.model;

import java.io.Serializable;

public class Apple implements Serializable {
    private String color;

    public Apple() {
    }

    public Apple(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return String.format("%s Apple", color);
    }
}
