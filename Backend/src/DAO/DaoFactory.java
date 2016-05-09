
package DAO;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Robin
 */
public abstract class DaoFactory {
    protected static HashMap<Class, Object> kids = new HashMap<>();
    
    /**
     * Return an unique instance of the given class. All the instances are stored in the HashMap "kids"
     */
    public static <T> T getInstance(Class<T> cls) {
        if (! kids.containsKey(cls)) {
            try {
                kids.put(cls, cls.newInstance());
            } catch (InstantiationException ex) {
                Logger.getLogger(DaoFactory.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (IllegalAccessException ex) {
                Logger.getLogger(DaoFactory.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return (T)kids.get(cls);
    } 
}
