package edu.radyuk.xml.main;

import edu.radyuk.xml.entity.Train;
import edu.radyuk.xml.exception.RailwayCarriageException;
import edu.radyuk.xml.parser.RailwayCarriageDomParser;

public class Main {
    public static final String FILE_PATH = "src/resources/railwayCarriages.xml";

    public static void main(String[] args) {
        try {
            RailwayCarriageDomParser parser = new RailwayCarriageDomParser();
            Train train = new Train(parser.parseRailwayCarriages(FILE_PATH));
            System.out.println(train);
        } catch (RailwayCarriageException e) {
            System.err.println("RailwayCarriageException");
        }
    }
}
