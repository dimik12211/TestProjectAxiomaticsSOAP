package Application.Service;

import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

@Service
public class ServiceXml {

    public String xmlDeleteCDATA(String xml) {
        xml = xml.trim();
        if (xml.startsWith("<![CDATA[")) {
            xml = xml.substring(9);
            int i = xml.indexOf("]]>");
            if (i != -1) {
                xml = xml.substring(0, i);
            }
        }
        return xmlXSLT(xml);
    }
    public String xmlXSLT(String xml) {
        StringReader reader = new StringReader(xml);
        Source source = new StreamSource(reader);

        StringWriter writer = new StringWriter();
        Result result = new StreamResult(writer);

        Source xsltSource = new StreamSource(new File("src/main/resources/response_soap_xslt.xsl"));

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer(xsltSource);
            transformer.transform(source, result);
        } catch (TransformerException e ) {
            e.printStackTrace();
        }
        xml = writer.toString();
        return xml;
    }
}
