package org.example.Application.producingwebservice;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet messageDispatcherServlet = null;
        try {
            messageDispatcherServlet = new MessageDispatcherServlet();
            messageDispatcherServlet.setApplicationContext(applicationContext);
            messageDispatcherServlet.setTransformWsdlLocations(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ServletRegistrationBean<>(messageDispatcherServlet, "/ws/*");
    }

    @Bean(name = "countries")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema) {
        DefaultWsdl11Definition defaultWsdl11Definition = null;
        try {
            defaultWsdl11Definition = new DefaultWsdl11Definition();
            defaultWsdl11Definition.setPortTypeName("CountriesPort");
            defaultWsdl11Definition.setLocationUri("/ws");
            defaultWsdl11Definition.setTargetNamespace("http://www.example.org/Application/producingwebservice");
            defaultWsdl11Definition.setSchema(countriesSchema);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultWsdl11Definition;
    }

    @Bean
    public XsdSchema countriesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("scheme.xsd"));
    }
}