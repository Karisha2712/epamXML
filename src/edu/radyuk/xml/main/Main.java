package edu.radyuk.xml.main;

import edu.radyuk.xml.entity.RailwayCarriage;
import edu.radyuk.xml.entity.Train;
import edu.radyuk.xml.exception.RailwayCarriageException;
import edu.radyuk.xml.parser.RailwayCarriageDomParser;

public class Main {
    public static void main(String[] args) {
        try {
            RailwayCarriageDomParser parser = new RailwayCarriageDomParser();
            Train train = parser.buildTrain("");
        } catch (RailwayCarriageException e) {

        }
    }
}
