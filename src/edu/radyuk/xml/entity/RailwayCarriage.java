package edu.radyuk.xml.entity;

public abstract class RailwayCarriage {
    private final int railwayCarriageId;

    public RailwayCarriage(int railwayCarriageId) {
        this.railwayCarriageId = railwayCarriageId;
    }

    public int getRailwayCarriageId() {
        return railwayCarriageId;
    }

}
