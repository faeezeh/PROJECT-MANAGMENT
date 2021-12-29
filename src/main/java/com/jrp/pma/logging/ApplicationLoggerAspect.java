package com.jrp.pma.logging;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApplicationLoggerAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Pointcut("within(com.jrp.pma.controllers..*)")
    public void definePackagePointCuts(){
        //
    }
    @After("definePackagePointCuts()")
    public void log(){
        log.debug("------");
    }
}
