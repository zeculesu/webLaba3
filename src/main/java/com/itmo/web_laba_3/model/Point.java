package com.itmo.web_laba_3.model;

import jakarta.persistence.*;

@Entity
@Table(name = "points")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "x")
    private int x;

    @Column(name = "y")
    private double y;

    @Column(name = "r")
    private int r;

    @Column(name = "hit")
    private boolean hit;

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public double getY() {
        return this.y;
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
        return this.hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }
}
