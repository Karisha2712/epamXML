package edu.radyuk.xml.entity;

import java.util.Objects;

public abstract class RailwayCarriage {
    private final int railwayCarriageId;

    public RailwayCarriage(int railwayCarriageId) {
        this.railwayCarriageId = railwayCarriageId;
    }

    public int getRailwayCarriageId() {
        return railwayCarriageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RailwayCarriage that = (RailwayCarriage) o;
        return railwayCarriageId == that.railwayCarriageId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(railwayCarriageId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RailwayCarriage{");
        sb.append("railwayCarriageId=").append(railwayCarriageId);
        sb.append('}');
        return sb.toString();
    }
}
