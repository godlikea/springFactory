package com.kai.config;

import com.kai.service.UserService;
import com.kai.service.impl.UserServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRegistration;
import javax.xml.ws.Endpoint;

/**
 * @author ggk
 * @date 2019/5/30 0030 下午 6:00
 */
@Configuration
public class CxfConfig {


    @Autowired
    private UserService userService;

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus(){
        return  new SpringBus();
    }
    @Bean("cxfServletRegistration")
    public ServletRegistrationBean dispatchServlet(){
        return new ServletRegistrationBean(new CXFServlet(),"/soap/*");
    }
    @Bean
    public Endpoint endPoint(){
        EndpointImpl endpoint=new EndpointImpl(springBus(),userService);
        long a=123;
        endpoint.publish("/user");
        return endpoint;
    }
}
