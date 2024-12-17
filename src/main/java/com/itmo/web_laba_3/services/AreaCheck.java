package com.itmo.web_laba_3.services;

public class AreaCheck {
    public static boolean checkHit(int x, double y, int r) {
        if (x <= 0 && y >= 0) {
            return x * x + y * y <= r * r;
        }
        if (x <= 0 && y <= 0) {
            return y >= -2 * x - r;
        }
        if (x >= 0 && y <= 0) {
            return x <= r && y >= -r;
        }
        return false;
    }
}
