package com.itmo.web_laba_3.validators;

import com.itmo.web_laba_3.exceptions.ValidationException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Validator {
    private static final Set<Integer> xValues= new HashSet<>(Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3));
    public static void validateX(int x) throws ValidationException {
        try{
            if (!xValues.contains(x)){
                throw new ValidationException("X от -5 до 3");
            }
        } catch (NumberFormatException e){
            throw new ValidationException("X должно быть числом");
        }
    }

    public static void validateY(double y) throws ValidationException{
        try{
            if (y < -5 || y > 3){
                throw new ValidationException("Y принадлежит [-5, 3]");
            }
        } catch (NumberFormatException e){
            throw new ValidationException("Y должно быть числом");
        }

    }

    public static void validateR(double r) throws ValidationException{
        try{
            if (r < 1 || r > 5){
                throw new ValidationException("R от 1 до 5");
            }
        } catch (NumberFormatException e){
            throw new ValidationException("R должно быть числом");
        }
    }
}
