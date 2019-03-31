package model;

public class Part {

    private final Type type;
    private final int start;
    private final int count;

    public Part(Type type, int start, int count) {
        this.type = type;
        this.count = count;
        this.start = start;
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
