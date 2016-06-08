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
public class JpaBoxDao extends JpaDao<Box> implements BoxDao{
    
    @Override
    public int getTotalPrice() {
        return 0;
    }
    
}
