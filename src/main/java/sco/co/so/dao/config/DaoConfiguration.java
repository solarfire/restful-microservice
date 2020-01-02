package sco.co.so.dao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import sco.co.so.model.Bean;

/**
 * Created on 06/12/2017.
 */
@Configuration
@ComponentScan("sco.so.dao.config")
public class DaoConfiguration {

    @Autowired
    private Bean bean;


}
