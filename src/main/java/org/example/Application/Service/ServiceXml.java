package org.example.Application.Service;

import org.example.Application.producingwebservice.CountryEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

@Service
public class ServiceXml {

    Logger logger = LoggerFactory.getLogger(ServiceXml.class);

    public String xmlDeleteCDATA(String xml) {
        try {
            xml = xml.trim();
            if (xml.startsWith("<![CDATA[")) {
                xml = xml.substring(9);
                int i = xml.indexOf("]]>");
                if (i != -1) {
                    xml = xml.substring(0, i);
                }
            }
            logger.info("<![CDATA[...]]> удалена");
            if (xml.equals("")) {
                throw new IllegalStateException("Пустой XML");
            }
        } catch (IllegalStateException e) {
            logger.error("Ошибка, состояние XML не соответствует ожидаемому: " + e.getMessage());
            throw e;
        }
        return xmlToXSLT(xml);
    }

    private String xmlToXSLT(String xml) {
        StringWriter stringWriter = new StringWriter();
        try {
            StringReader stringReader = new StringReader(xml);
            Source source = new StreamSource(stringReader);
            Result result = new StreamResult(stringWriter);
            Source xsltSource = new StreamSource(new File("src/main/resources/response_soap_xslt.xsl"));
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer(xsltSource);
            transformer.transform(source, result);
            logger.info("XSLT преобразование выполнено");
        } catch (TransformerException e) {
            logger.error("Ошибка: XSLT преобразование не отработало: " + e.getMessage());
        }
        xml = stringWriter.toString();
        xml = "<![CDATA[" + xml + "]]>";
        logger.info("XML после XSLT преобразования сформирован");
        return xml;
    }
}
