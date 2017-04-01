/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slackers.inc.database.entities;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author John Stegeman <j.stegeman@labyrinth-tech.com>
 */
public class ColaUser extends User{

    public ColaUser(String password, String email) {
        super(password, email);
        init();
    }

    public ColaUser(String email) {
        super(email);
        init();
    }

    
    
    private void init()
    {
        super.setUserType(User.UserType.COLA_USER);
    }
}