/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slackers.inc.Boundary;

/**
 *
 * @author John Stegeman <j.stegeman@labyrinth-tech.com>
 */
public class InputValidator {
    
    public static int validateInt(String value, String fieldName)
    {
        try
        {
            return Integer.parseInt(value);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("You must enter a number in the " + fieldName + " field");
        }
    }
    
    public static int validateInt(String value, String fieldName, int min, int max)
    {
        int val = validateInt(value, fieldName);
        
        if (val<min || val > max)
        {
            throw new IllegalArgumentException(fieldName + " must be within "+min+" and "+max);
        }
        return val;
    }
    
    public static double validateDouble(String value, String fieldName)
    {
        try
        {
            return Double.parseDouble(value);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("You must enter a number in the " + fieldName + " field");
        }
    }
    
    public static double validateDouble(String value, String fieldName, double min, double max)
    {
        double val = validateDouble(value, fieldName);
        
        if (val<min || val > max)
        {
            throw new IllegalArgumentException(fieldName + " must be within "+min+" and "+max);
        }
        return val;
    }
    
    public static String validateStringNotEmpty(String value, String fieldName)
    {
        if (value == null || value.equals(""))
        {
            throw new IllegalArgumentException(fieldName + " must be not be empty");
        }
        return value;
    }
    
    public static String validateStringWithinLength(String value, String fieldName, int minLength, int maxLength)
    {
        value = value.trim();
        if (value == null || value.length()<minLength)
        {
            throw new IllegalArgumentException(fieldName + " must be at least "+minLength+" and less than "+maxLength + " characters");
        }
        return value;
    }
    public static String validateStringExactLength(String value, String fieldName, int length)
    {
        value = value.trim();
        if (value == null || value.length()!=length)
        {
            throw new IllegalArgumentException(fieldName + " must be" + length+ " characters long");
        }
        return value;
    }
}
