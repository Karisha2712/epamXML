package edu.radyuk.xml.parser;

import edu.radyuk.xml.entity.FreightCar;
import edu.radyuk.xml.entity.PassengerCarriage;
import edu.radyuk.xml.entity.RailwayCarriage;
import edu.radyuk.xml.entity.Train;
import edu.radyuk.xml.exception.RailwayCarriageException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.IOException;

public class TrainDomWriter {
    private final Document document;

    public TrainDomWriter() throws RailwayCarriageException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            document = documentBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            System.err.println(e.getMessage());
            throw new RailwayCarriageException(e);
        }
    }

    public void writeTrain(Train train, String filePath) throws RailwayCarriageException {
        Element trainElement = document.createElement(XmlTags.TRAIN.toString());
        document.appendChild(trainElement);
        createTrainProperties(trainElement, train);
        createRailwayCarriageElements(trainElement, train);
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileWriter(filePath));
            transformer.transform(source, result);
        } catch (IOException e) {
            System.err.printf("Error while opening file %s. %s%n", filePath, e.getMessage());
            throw new RailwayCarriageException("Error while opening file" + filePath, e);
        } catch (TransformerConfigurationException e) {
            System.err.printf("Error while configuration file %s. %s%n", filePath, e.getMessage());
            throw new RailwayCarriageException("Error while configuration file" + filePath, e);
        } catch (TransformerException e) {
            System.err.printf("Error while transforming file %s. %s%n", filePath, e.getMessage());
            throw new RailwayCarriageException("Error while transforming file" + filePath, e);
        }
    }

    private void createRailwayCarriageElements(Element trainElement, Train train) {
        for (RailwayCarriage railwayCarriage : train.getRailwayCarriages()) {
            Element railwayCarriageElement;
            if (railwayCarriage instanceof FreightCar) {
                railwayCarriageElement = createPassengerCarriageElement(railwayCarriage);
            } else {
                railwayCarriageElement = createFreightCarElement(railwayCarriage);
            }
            railwayCarriageElement.setAttribute(XmlTags.RAILWAY_CARRIAGE_ID.toString(),
                    Integer.toString(railwayCarriage.getRailwayCarriageId()));
            trainElement.appendChild(railwayCarriageElement);
        }
    }

    private void createTrainProperties(Element trainElement, Train train) {
        Element totalCarryingCapacityElement = document.createElement(XmlTags.TOTAL_CARRYING_CAPACITY.toString());
        Element totalPassengersNumberElement = document.createElement(XmlTags.TOTAL_PASSENGERS_NUMBER.toString());
        totalCarryingCapacityElement
                .appendChild(document.createTextNode(Integer.toString(train.getTotalCarryingCapacity())));
        totalPassengersNumberElement
                .appendChild(document.createTextNode(Integer.toString(train.getTotalPassengerNumber())));
        trainElement.appendChild(totalCarryingCapacityElement);
        trainElement.appendChild(totalPassengersNumberElement);
    }

    private Element createPassengerCarriageElement(RailwayCarriage railwayCarriage) {
        Element railwayCarriageElement = document.createElement(XmlTags.FREIGHT_CAR.toString());
        Element carryingCapacityElement = document.createElement(XmlTags.CARRYING_CAPACITY.toString());
        carryingCapacityElement.appendChild(document.createTextNode(
                Integer.toString(((FreightCar) railwayCarriage).getCarryingCapacity())));
        railwayCarriageElement.appendChild(carryingCapacityElement);
        return railwayCarriageElement;
    }

    private Element createFreightCarElement(RailwayCarriage railwayCarriage) {
        Element railwayCarriageElement = document.createElement(XmlTags.PASSENGER_CARRIAGE.toString());
        Element passengerNumberElement = document.createElement(XmlTags.PASSENGERS_NUMBER.toString());
        passengerNumberElement.appendChild(document.createTextNode(
                Integer.toString(((PassengerCarriage) railwayCarriage).getPassengersNumber())));
        railwayCarriageElement.appendChild(passengerNumberElement);
        return railwayCarriageElement;
    }

}
