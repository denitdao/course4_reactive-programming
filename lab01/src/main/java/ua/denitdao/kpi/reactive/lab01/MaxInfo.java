package ua.denitdao.kpi.reactive.lab01;

public class MaxInfo {
    int value;
    int index;

    public MaxInfo(int value, int index) {
        this.value = value;
        this.index = index;
    }

    public int getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "MaxInfo{" +
                "value=" + value +
                ", index=" + index +
                '}';
    }
}
