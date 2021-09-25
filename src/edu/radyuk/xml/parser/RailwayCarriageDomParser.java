package edu.radyuk.xml.parser;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import edu.radyuk.xml.entity.FreightCar;
import edu.radyuk.xml.entity.PassengerCarriage;
import edu.radyuk.xml.entity.RailwayCarriage;
import edu.radyuk.xml.entity.Train;
import edu.radyuk.xml.exception.RailwayCarriageException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class RailwayCarriageDomParser {
    private final Train train;
    private DocumentBuilder docBuilder;

    public RailwayCarriageDomParser() {
        train = new Train();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {

        }
    }

    public Train buildTrain(String filename) throws RailwayCarriageException {
        Document document;
        try {
            document = docBuilder.parse(filename);
            Element root = document.getDocumentElement();
            NodeList railwayCarriagesList = root.getElementsByTagName(XmlTags.CARRIAGE.toString());
            for (int i = 0; i < railwayCarriagesList.getLength(); i++) {
                Element railwayCarriageElement = (Element) railwayCarriagesList.item(i);
                RailwayCarriage railwayCarriage = buildRailwayCarriage(railwayCarriageElement);
                if (railwayCarriage != null) {
                    train.addRailwayCarriage(railwayCarriage);
                }
            }
        } catch (IOException | SAXException e) {
            throw new RailwayCarriageException("Exception!", e);
        }
        return train;
    }

    private RailwayCarriage buildRailwayCarriage(Element railwayCarriageElement) {
        RailwayCarriage railwayCarriage = null;
        if (railwayCarriageElement.hasAttribute(XmlTags.PASSENGER.toString())) {
            railwayCarriage = createPassengerCarriage(railwayCarriageElement);
        } else if (railwayCarriageElement.hasAttribute(XmlTags.FREIGHT.toString())) {
            railwayCarriage = createFreightCar(railwayCarriageElement);
        }
        return railwayCarriage;
    }

    private FreightCar createFreightCar(Element railwayCarriageElement) {
        int railwayCarriageId = Integer.parseInt(getElementTextContent(railwayCarriageElement,
                XmlTags.ID.toString()));
        FreightCar railwayCarriage = new FreightCar(railwayCarriageId);
        railwayCarriage.setCarryingCapacity(Integer.parseInt(getElementTextContent(railwayCarriageElement,
                XmlTags.CAPACITY.toString())));
        return railwayCarriage;
    }

    private PassengerCarriage createPassengerCarriage(Element railwayCarriageElement) {
        int railwayCarriageId = Integer.parseInt(getElementTextContent(railwayCarriageElement,
                XmlTags.ID.toString()));
        PassengerCarriage railwayCarriage = new PassengerCarriage(railwayCarriageId);
        railwayCarriage.setPassengersNumber(Integer.parseInt(getElementTextContent(railwayCarriageElement,
                XmlTags.NUMBER.toString())));
        return railwayCarriage;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}
