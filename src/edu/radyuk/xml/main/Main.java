package edu.radyuk.xml.main;

import edu.radyuk.xml.entity.Train;
import edu.radyuk.xml.exception.RailwayCarriageException;
import edu.radyuk.xml.parser.RailwayCarriageDomParser;
import edu.radyuk.xml.parser.TrainDomWriter;

public class Main {
    public static final String FILE_PATH_FOR_PARSING = "src/resources/railwayCarriages.xml";
    public static final String FILE_PATH_FOR_WRITING = "src/resources/railwayCarriages.xml";

    public static void main(String[] args) {
        try {
            RailwayCarriageDomParser parser = new RailwayCarriageDomParser();
            Train train = new Train(parser.parseRailwayCarriages(FILE_PATH_FOR_PARSING));
            System.out.println(train);
            TrainDomWriter trainDomWriter = new TrainDomWriter();
            trainDomWriter.writeTrain(train, FILE_PATH_FOR_WRITING);
        } catch (RailwayCarriageException e) {
            System.err.println("RailwayCarriageException");
        }
    }
}
