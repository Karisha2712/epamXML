package edu.radyuk.xml.entity;

import java.util.ArrayList;
import java.util.Objects;

public class Train {
    private final ArrayList<RailwayCarriage> train;

    public Train() {
        train = new ArrayList<RailwayCarriage>();
    }

    public void addRailwayCarriage(RailwayCarriage railwayCarriage) {
        train.add(railwayCarriage);
    }

    public void removeRailwayCarriage(RailwayCarriage railwayCarriage) {
        train.remove(railwayCarriage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train1 = (Train) o;
        return Objects.equals(train, train1.train);
    }

    @Override
    public int hashCode() {
        return Objects.hash(train);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Train{");
        sb.append("train=").append(train);
        sb.append('}');
        return sb.toString();
    }
}
