/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slackers.inc.database.entities;

import java.io.Serializable;

/**
 *
 * @author John Stegeman <j.stegeman@labyrinth-tech.com>
 */
public abstract class Label implements Serializable{
    private double alchoholContent;
    
    public Label()
    {
        this.alchoholContent = -1;
    }

    public double getAlchoholContent() {
        return alchoholContent;
    }

    public void setAlchoholContent(double alchoholContent) {
        this.alchoholContent = alchoholContent;
    }
    
    
}
