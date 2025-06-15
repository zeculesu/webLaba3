package com.itmo.web_laba_3.beans;

import com.itmo.web_laba_3.dbManager.DotInfluxDBManager;
import com.itmo.web_laba_3.model.Dot;
import com.itmo.web_laba_3.services.AreaCheck;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Named
@ApplicationScoped
public class DotController implements Serializable {

    private Dot dot;
    private Dot dotCanvas;

    public DotController() {
        this.dot = new Dot();
        this.dotCanvas = new Dot();
    }

    public Dot getDot() {
        return dot;
    }

    public void setDot(Dot dot) {
        this.dot = dot;
    }

    public Dot getDotCanvas() {
        return dotCanvas;
    }

    public void setDotCanvas(Dot dotCanvas) {
        this.dotCanvas = dotCanvas;
    }

    public void save(Dot dot) {
        boolean hit = AreaCheck.checkHit(dot.getX(), dot.getY(), dot.getR());
        dot.setHit(hit);
        DotInfluxDBManager.addDot(dot);
    }

    public void submitForm() {
        save(this.dot);
        int last_r = dot.getR();
        dot = new Dot();
        dot.setR(last_r);
    }

    public void submitFormCanvas() {
        save(this.dotCanvas);
        int last_r = dot.getR();
        this.dotCanvas = new Dot();
        this.dotCanvas.setR(last_r);
    }

    public List<Dot> getDots() {
        return new ArrayList<>(DotInfluxDBManager.getDots());
    }

    public void clearDots() {
        DotInfluxDBManager.clearAllDots();
    }
}
