/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Instance;

/**
 *
 * @author Robin
 */
public interface InstanceDao extends Dao<Instance>{
    public Instance getInstanceByName(String Name);
}
