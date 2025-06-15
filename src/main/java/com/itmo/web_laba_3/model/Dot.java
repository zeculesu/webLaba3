package com.itmo.web_laba_3.model;

import java.time.Instant;

public class Dot {
    private int x;
    private double y;
    private int r;
    private boolean hit;
    private Instant time;

    public Dot() {
    }

    public Dot(int x, double y, int r){
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public Dot(int x, double y, int r, boolean hit) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = hit;
    }

    public Dot(int x, double y, int r, boolean hit, Instant time) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = hit;
        this.time = time;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }
}
