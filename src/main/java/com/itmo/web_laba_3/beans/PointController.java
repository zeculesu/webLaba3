package com.itmo.web_laba_3.beans;

import com.itmo.web_laba_3.exceptions.ValidationException;
import com.itmo.web_laba_3.model.Point;
import com.itmo.web_laba_3.services.AreaCheck;
import com.itmo.web_laba_3.services.PointsDBManager;
import com.itmo.web_laba_3.validators.Validator;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//todo перенести с аннотаций в конфиг
//todo часики переделать
// кнопочку поправить наща

@Named
@SessionScoped
public class PointController implements Serializable {
    private final PointsDBManager points = new PointsDBManager();
    private Point point;
    private Point pointCanvas;

    public PointController() {
        this.point = new Point();
        this.pointCanvas = new Point();
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Point getPointCanvas() {
        return pointCanvas;
    }

    public void setPointCanvas(Point pointCanvas) {
        this.pointCanvas = pointCanvas;
    }

    public void save(Point point) {
        try {
            Validator.validateX(point.getX());
            Validator.validateY(point.getY());
            Validator.validateR(point.getR());
        } catch (ValidationException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
            return;
        }
        point.setHit(AreaCheck.checkHit(point.getX(), point.getY(), point.getR()));
        points.save(point);
    }

    public void submitForm() {
        save(this.point);
        int last_r = point.getR();
        point = new Point();
        point.setR(last_r);
    }

    public void submitFormCanvas() {
        save(this.pointCanvas);
        int last_r = point.getR();
        this.pointCanvas = new Point();
        this.pointCanvas.setR(last_r);
    }

    public List<Point> getPoints() {
        List<Point> reversed = new ArrayList<>(this.points.getShots());
        Collections.reverse(reversed);
        return reversed;
    }

}
