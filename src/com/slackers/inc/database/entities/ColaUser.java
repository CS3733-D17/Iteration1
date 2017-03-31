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

    public ColaUser(String username, String password, String email) {
        super(username, password, email);
        init();
    }

    public ColaUser(String username, String password) {
        super(username, password);
        init();
    }

    public ColaUser(String username) {
        super(username);
        init();
    }
    
    private void init()
    {
        super.setUserType(User.UserType.COLA_USER);
    }
}
