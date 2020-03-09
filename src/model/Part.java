package model;

import transforms.Col;

public class Part {

    private final Type type;
    private final int start;
    private final int count;
    private final Col color;

    public Part(Type type, int start, int count, Col color) {
        this.type = type;
        this.count = count;
        this.start = start;
        this.color = color;

    }

    public Col getColor(){
        return color;
    }
    public Type getType() {
        return type;
    }

    public int getStart(){
        return start;
    }

    public int getCount(){
        return count;
    }
}
