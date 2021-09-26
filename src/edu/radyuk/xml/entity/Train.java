package edu.radyuk.xml.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Train {
    private int totalPassengerNumber;
    private int totalCarryingCapacity;
    private final List<RailwayCarriage> railwayCarriages;

    public Train() {
        railwayCarriages = new ArrayList<>();
    }

    public Train(List<RailwayCarriage> railwayCarriageList) {
        railwayCarriages = railwayCarriageList;
        totalPassengerNumber = calculateTotalPassengersNumber();
        totalCarryingCapacity = calculateTotalCarryingCapacity();
    }

    public void addRailwayCarriage(RailwayCarriage railwayCarriage) {
        railwayCarriages.add(railwayCarriage);
        if (railwayCarriage instanceof FreightCar) {
            totalCarryingCapacity += ((FreightCar) railwayCarriage).getCarryingCapacity();
        } else if (railwayCarriage instanceof PassengerCarriage) {
            totalPassengerNumber += ((PassengerCarriage) railwayCarriage).getPassengersNumber();
        }
    }

    public void removeRailwayCarriage(RailwayCarriage railwayCarriage) {
        if (railwayCarriage == null) {
            return;
        }
        railwayCarriages.remove(railwayCarriage);
        if (railwayCarriage instanceof FreightCar) {
            totalCarryingCapacity -= ((FreightCar) railwayCarriage).getCarryingCapacity();
        } else if (railwayCarriage instanceof PassengerCarriage) {
            totalPassengerNumber -= ((PassengerCarriage) railwayCarriage).getPassengersNumber();
        }
    }

    public int getTotalCarryingCapacity() {
        return totalCarryingCapacity;
    }

    public int getTotalPassengerNumber() {
        return totalPassengerNumber;
    }

    public List<RailwayCarriage> getRailwayCarriages() {
        return railwayCarriages;
    }

    private int calculateTotalCarryingCapacity() {
        int totalCarryingCapacity = 0;
        for (RailwayCarriage railwayCarriage : railwayCarriages) {
            if (railwayCarriage instanceof FreightCar) {
                totalCarryingCapacity += ((FreightCar) railwayCarriage).getCarryingCapacity();
            }
        }
        return totalCarryingCapacity;
    }

    private int calculateTotalPassengersNumber() {
        int totalPassengersNumber = 0;
        for (RailwayCarriage railwayCarriage : railwayCarriages) {
            if (railwayCarriage instanceof PassengerCarriage) {
                totalPassengersNumber += ((PassengerCarriage) railwayCarriage).getPassengersNumber();
            }
        }
        return totalPassengersNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train1 = (Train) o;
        return Objects.equals(railwayCarriages, train1.railwayCarriages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(railwayCarriages);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Train{");
        sb.append("totalPassengerNumber=").append(totalPassengerNumber);
        sb.append(", totalCarryingCapacity=").append(totalCarryingCapacity);
        sb.append(", railwayCarriages=").append(railwayCarriages);
        sb.append('}');
        return sb.toString();
    }
}
