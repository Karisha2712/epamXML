package edu.radyuk.xml.entity;

import java.util.List;
import java.util.Objects;

public class Train {
    private final List<RailwayCarriage> train;
    private int totalCarryingCapacity;
    private int totalPassengersNumber;

    public Train(List<RailwayCarriage> railwayCarriageList) {
        train = railwayCarriageList;
    }

    public int getTotalCarryingCapacity() {
        calculateTotalCarryingCapacity();
        return totalCarryingCapacity;
    }

    private void calculateTotalCarryingCapacity() {
        for (RailwayCarriage railwayCarriage : train) {
            if (railwayCarriage instanceof FreightCar) {
                totalCarryingCapacity = totalCarryingCapacity
                        + ((FreightCar) railwayCarriage).getCarryingCapacity();
            }
        }
    }

    public int getTotalPassengersNumber() {
        calculateTotalPassengersNumber();
        return totalPassengersNumber;
    }

    public void calculateTotalPassengersNumber() {
        for (RailwayCarriage railwayCarriage : train) {
            if (railwayCarriage instanceof PassengerCarriage) {
                totalPassengersNumber = totalPassengersNumber
                        + ((PassengerCarriage) railwayCarriage).getPassengersNumber();
            }
        }
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
