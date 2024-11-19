package eu.iba.auto_test.converterbnb.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class HistoryUtils {

    public static String textFormattingGeneralHistory(String text){
        if (text.contains("<H>")) {
            StringBuilder builder = new StringBuilder();
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = factory.newDocumentBuilder();

                ByteArrayInputStream input = new ByteArrayInputStream(text.getBytes("windows-1251"));
                Document xmlDoc = docBuilder.parse(input);

                Node root = xmlDoc.getDocumentElement();
                if (root.getNodeName().equals("H")) {
                    NodeList nodeList = root.getChildNodes();
                    for (int i = 0; i < nodeList.getLength(); i++) {
                        Node node = nodeList.item(i);
                        if (node.getNodeName().equals("R")) {
                            Element eElement = (Element) node;
                            builder.append(" ").append("\"").append(eElement.getAttribute("N")).append("\"");
                            builder.append(": ");
                            builder.append("\"").append(eElement.getTextContent()).append("\"");
                            builder.append("\n");
                        }
                    }
                }
            } catch (ParserConfigurationException | SAXException | IOException ex) {
                return text;
            }
            return builder.toString();
        }
        return text;
    }

    public static String textFormattingTaskHistory(String text){
        if (text.contains("<Details>")) {
            StringBuilder builder = new StringBuilder();
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = factory.newDocumentBuilder();

                ByteArrayInputStream input = new ByteArrayInputStream(text.getBytes("windows-1251"));
                Document xmlDoc = docBuilder.parse(input);

                Node root = xmlDoc.getDocumentElement();
                if (root.getNodeName().equals("Details")) {
                    NodeList nodeList = root.getChildNodes();
                    for (int i = 0; i < nodeList.getLength(); i++) {
                        Node node = nodeList.item(i);
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            builder.append(" \"").append(node.getNodeName()).append("\"").append(":");
                            builder.append("\n");
                            if (node.hasAttributes()) {
                                for (int j = 0; j < node.getAttributes().getLength(); j++) {
                                    builder.append("  \"").append(node.getAttributes().item(j).getNodeName()).append("\"");
                                    builder.append(": ");
                                    builder.append("\"").append(node.getAttributes().item(j).getNodeValue()).append("\"");
                                    builder.append("\n");
                                }
                            }
                            NodeList nodeListAttr = node.getChildNodes();
                            for (int j = 0; j < nodeListAttr.getLength(); j++) {
                                Node nodeAttr = nodeListAttr.item(j);
                                if (nodeAttr.getNodeType() == Node.ELEMENT_NODE) {
                                    builder.append("  \"").append(nodeAttr.getNodeName()).append("\"").append(":");
                                    builder.append("\n");
                                    if (nodeAttr.hasAttributes()) {
                                        for (int k = 0; k < nodeAttr.getAttributes().getLength(); k++) {
                                            builder.append("   \"").append(nodeAttr.getAttributes().item(k).getNodeName()).append("\"");
                                            builder.append(": ");
                                            builder.append("\"").append(nodeAttr.getAttributes().item(k).getNodeValue()).append("\"");
                                            builder.append("\n");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (ParserConfigurationException | SAXException | IOException ex) {
                return text;
            }
            return builder.toString();
        }
        return text;
    }
}
