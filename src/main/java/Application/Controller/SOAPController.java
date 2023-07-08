package Application.Controller;

import Application.Service.ServiceXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SOAPController {

    @Autowired
    private ServiceXml serviceXml;

    @PostMapping(value = "/SOAP")
    public String soapPostMapping(@RequestBody String xml){
        String xmlXSLT = serviceXml.xmlDeleteCDATA(xml);
        return xmlXSLT;
    }
}
