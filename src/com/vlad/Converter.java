package com.vlad;

import java.io.StringWriter;
import java.text.ParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.vlad.parser.ParseItem;
import com.vlad.parser.Parser;

public class Converter {

	public static String generateXML(String lispLikeString) throws ParseException, ParserConfigurationException,
			TransformerFactoryConfigurationError, TransformerException {
		Parser parser = new Parser(new StringBuilder(lispLikeString));

		DocumentBuilder xmlDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document xmlDocument = xmlDocumentBuilder.newDocument();
		xmlDocument.setXmlVersion("1.0");
		xmlDocument.setXmlStandalone(true);

		generateXMLStructure(xmlDocument, null, parser.getRootItem());

		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		StreamResult result = new StreamResult(new StringWriter());
		DOMSource source = new DOMSource(xmlDocument);
		transformer.transform(source, result);

		return result.getWriter().toString();
	}

	private static void generateXMLStructure(Document document, Element rootElement, ParseItem item)
			throws ParseException {
		Element element = document.createElement(item.getName());
		if (rootElement != null) {
			rootElement.appendChild(element);
		} else {
			document.appendChild(element);
			element.setAttribute("xmlns:android", "http://schemas.android.com/apk/res/android");
		}

		for (ParseItem child : item.getChildren()) {
			if (child.isAttribute()) {
				element.setAttribute("android:" + child.getName(), child.getValue());
			} else {
				generateXMLStructure(document, element, child);
			}
		}
	}
}
