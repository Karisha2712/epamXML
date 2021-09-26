package edu.radyuk.xml.entity;

import java.util.Objects;

public class FreightCar extends RailwayCarriage {
    private int carryingCapacity;

    public FreightCar(int railwayCarriageId) {
        super(railwayCarriageId);
    }

    public int getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FreightCar that = (FreightCar) o;
        return carryingCapacity == that.carryingCapacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), carryingCapacity);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FreightCar{");
        sb.append("railwayCarriageId=").append(super.getRailwayCarriageId());
        sb.append(", carryingCapacity=").append(carryingCapacity);
        sb.append('}');
        return sb.toString();
    }
}
