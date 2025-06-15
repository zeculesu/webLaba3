package com.itmo.web_laba_3.beans;


import com.itmo.web_laba_3.mBean.PointCounter;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MBeanRegistrar {

    private PointCounter pointCounter;

    @PostConstruct
    public void registerMBean() {
        try {
            this.pointCounter = new PointCounter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}