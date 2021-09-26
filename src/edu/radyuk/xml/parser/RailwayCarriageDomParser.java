package edu.radyuk.xml.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import edu.radyuk.xml.entity.FreightCar;
import edu.radyuk.xml.entity.PassengerCarriage;
import edu.radyuk.xml.entity.RailwayCarriage;
import edu.radyuk.xml.exception.RailwayCarriageException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class RailwayCarriageDomParser {
    private final List<RailwayCarriage> carriagesList;
    private final DocumentBuilder docBuilder;

    public RailwayCarriageDomParser() throws RailwayCarriageException {
        carriagesList = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println(e.getMessage());
            throw new RailwayCarriageException(e);
        }
    }

    public List<RailwayCarriage> parseRailwayCarriages(String filePath) throws RailwayCarriageException {
        Document document;
        try {
            document = docBuilder.parse(filePath);
            Element root = document.getDocumentElement();
            NodeList railwayCarriagesList = root.getElementsByTagName(XmlTags.PASSENGER_CARRIAGE.toString());
            for (int i = 0; i < railwayCarriagesList.getLength(); i++) {
                Element railwayCarriageElement = (Element) railwayCarriagesList.item(i);
                RailwayCarriage railwayCarriage = createPassengerCarriage(railwayCarriageElement);
                carriagesList.add(railwayCarriage);
            }
            railwayCarriagesList = root.getElementsByTagName(XmlTags.FREIGHT_CAR.toString());
            for (int i = 0; i < railwayCarriagesList.getLength(); i++) {
                Element railwayCarriageElement = (Element) railwayCarriagesList.item(i);
                RailwayCarriage railwayCarriage = createFreightCar(railwayCarriageElement);
                carriagesList.add(railwayCarriage);
            }
        } catch (IOException e) {
            System.err.printf("Error while reading file %s. %s%n", filePath, e.getMessage());
            throw new RailwayCarriageException("Error while reading file" + filePath, e);
        } catch (SAXException e) {
            System.err.printf("Error while parsing file %s. %s%n", filePath, e.getMessage());
            throw new RailwayCarriageException("Error while parsing file" + filePath, e);
        }
        return carriagesList;
    }

    private FreightCar createFreightCar(Element railwayCarriageElement) throws RailwayCarriageException {
        FreightCar railwayCarriage;
        try {
            int railwayCarriageId =
                    Integer.parseInt(railwayCarriageElement.getAttribute(XmlTags.RAILWAY_CARRIAGE_ID.toString()));
            railwayCarriage = new FreightCar(railwayCarriageId);
            railwayCarriage.setCarryingCapacity(Integer.parseInt(getElementTextContent(railwayCarriageElement,
                    XmlTags.CARRYING_CAPACITY.toString())));
        } catch (NumberFormatException e) {
            System.err.println("Document contains invalid data"); //TODO
            throw new RailwayCarriageException("Document contains invalid data", e);
        }
        return railwayCarriage;
    }

    private PassengerCarriage createPassengerCarriage(Element railwayCarriageElement) throws RailwayCarriageException {
        PassengerCarriage railwayCarriage;
        try {
            int railwayCarriageId = Integer.parseInt(railwayCarriageElement
                    .getAttribute(XmlTags.RAILWAY_CARRIAGE_ID.toString()));
            railwayCarriage = new PassengerCarriage(railwayCarriageId);
            railwayCarriage.setPassengersNumber(Integer.parseInt(getElementTextContent(railwayCarriageElement,
                    XmlTags.PASSENGER_NUMBER.toString())));
        } catch (NumberFormatException e) {
            System.err.println("Document contains invalid data");//TODO
            throw new RailwayCarriageException("Document contains invalid data", e);
        }
        return railwayCarriage;
    }

    private static String getElementTextContent(Element element, String tagName) throws RailwayCarriageException {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() == 0) {
            System.err.printf("Element %s doesn't contain tag %s%n", element, tagName);
            throw new RailwayCarriageException("Element %s doesn't contain tag %s%n".formatted(element, tagName));
        }
        Node node = nodeList.item(0);
        return node.getTextContent();
    }
}
