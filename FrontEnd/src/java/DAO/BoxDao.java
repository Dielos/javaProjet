/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Model.Box;

/**
 *
 * @author Robin
 */
public interface BoxDao extends Dao<Box>{
    public int getTotalPrice();
}
