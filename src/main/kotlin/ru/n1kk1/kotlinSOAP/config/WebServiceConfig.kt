package ru.n1kk1.kotlinSOAP.config

import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.ws.config.annotation.EnableWs
import org.springframework.ws.config.annotation.WsConfigurerAdapter
import org.springframework.ws.transport.http.MessageDispatcherServlet
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition
import org.springframework.xml.xsd.SimpleXsdSchema
import org.springframework.xml.xsd.XsdSchema

import ru.n1kk1.kotlinSOAP.endpoint.CustomerEndpoint
import javax.servlet.Servlet

@EnableWs
@Configuration
class WebServiceConfig : WsConfigurerAdapter() {
   @Suppress("UNCHECKED_CAST")
   @Bean
   fun messageDispatcherServlet(applicationContext: ApplicationContext?): ServletRegistrationBean<Servlet> {
       val servlet: MessageDispatcherServlet = MessageDispatcherServlet()
       servlet.setApplicationContext(applicationContext!!)
       servlet.isTransformWsdlLocations = true
       return ServletRegistrationBean(servlet, "/ws/*")
   }

    // 127.0.0.1:8081/ws/customers.wsdl

    @Bean(name = ["customers"])
    fun defaultWsdl11Definition(schema: XsdSchema?): DefaultWsdl11Definition {
        val wsdl11Definition: DefaultWsdl11Definition = DefaultWsdl11Definition()
        wsdl11Definition.setPortTypeName("CustomersPort")
        wsdl11Definition.setLocationUri("/ws")
        wsdl11Definition.setTargetNamespace(CustomerEndpoint.NAMESPACE_URI)
        wsdl11Definition.setSchema(schema)
        return wsdl11Definition
    }

    @Bean
    fun customersSchema(): XsdSchema {
        return SimpleXsdSchema(ClassPathResource("customers.xsd"))
    }
}
