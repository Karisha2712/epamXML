package edu.radyuk.xml.entity;

import java.util.Objects;

public class PassengerCarriage extends RailwayCarriage {
    private int passengersNumber;

    public PassengerCarriage(int railwayCarriageId) {
        super(railwayCarriageId);
    }

    public int getPassengersNumber() {
        return passengersNumber;
    }

    public void setPassengersNumber(int passengersNumber) {
        this.passengersNumber = passengersNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassengerCarriage that = (PassengerCarriage) o;
        return super.getRailwayCarriageId() == that.getRailwayCarriageId()
                && passengersNumber == that.passengersNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getRailwayCarriageId(), passengersNumber);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PassengerCarriage{");
        sb.append("railwayCarriageId=").append(super.getRailwayCarriageId());
        sb.append(", passengersNumber=").append(passengersNumber);
        sb.append('}');
        return sb.toString();
    }
}
