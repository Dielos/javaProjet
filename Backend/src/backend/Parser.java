/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;


import DAO.BoxTypeDao;
import DAO.DaoFactoryJpa;
import DAO.JpaBoxTypeDao;
import DAO.JpaOrderDao;
import DAO.JpaProductTypeDao;
import DAO.OrderDao;
import DAO.ProductTypeDao;
import Model.BoxType;
import Model.Order;
import Model.OrderLine;
import Model.ProductType;

import Model.ProductType;

import java.util.ArrayList;

/**
 *
 * @author Mélody
 */
public class Parser {
    String instanceFile = "10 40 5 3\n" +
    "\n" +
    "P001   10   20  100   50    2\n" +
    "P002    5    6   65   50    3\n" +
    "P003   14   24   55   60    5\n" +
    "P004    8   24  190  100    1\n" +
    "P005   23   20   90  135    1\n" +
    "P006    9   14  100  170    2\n" +
    "P007    9   15   95  105    3\n" +
    "P008   10    6  145  135    1\n" +
    "P009   17   12  195  100    1\n" +
    "P010   14    8  135   80    2\n" +
    "\n" +
    "C001    7      320    9.35    2    3   14    2    0    2    2    0    0    2\n" +
    "C002   18     2064   16.30    0    0    0    0    0    3    0    0    0    2\n" +
    "C003   19     1394    5.65    1    0    0    1    0    0    0    0    1    0\n" +
    "C004    9     1269   11.20    2    3    5    2    0    0    2    3    1    2\n" +
    "C005    7      674   10.10    4   15    5    2    2    0    2    1    0    2\n" +
    "C006    7      457    4.45    0    1    0    1    0    0    1    0    0    0\n" +
    "C007   15     2542   11.30    2    9    5    1    0    2    1    2    0    0\n" +
    "C008   12     3226    8.60    4    5    6    0    1    0    3    1    0    1\n" +
    "C009   12     3375    3.75    1    0    0    0    0    0    0    0    0    1\n" +
    "C010   10      965   11.80    5    6    1    1    1    2    0    0    2    4\n" +
    "C011    5     1934   10.85    3    2    4    0    2    6    1    0    1    1\n" +
    "C012   11     2094   13.50    0    1    7    0    0    4    3    0    0    4\n" +
    "C013   15     1570   13.55    4    5    6    1    1    3    0    1    1    2\n" +
    "C014    7     2978    6.75    0    0    6    0    1    1    0    0    0    1\n" +
    "C015   10       42   10.30    0    0    0    1    0    0    0    1    0    0\n" +
    "C016    9     2678   11.65    0    2    1    1    2    0    0    3    0    0\n" +
    "C017    8      441   11.20    1    1    3    1    1    4    0    0    2    1\n" +
    "C018   16     2089   13.40    0    1    0    2    0    2    0    0    1    0\n" +
    "C019   18     2379    7.20    0    0    0    0    0    1    1    0    1    0\n" +
    "C020   18     1916    9.75    5    3    8    0    0    2    6    0    0    0\n" +
    "C021   16     2628    5.75    0    1   10    1    0    1    4    0    0    0\n" +
    "C022   15     1504   11.85    0    6    0    2    0    0    0    0    1    1\n" +
    "C023   13     4346    9.45    2   12    8    0    1    0    2    3    1    1\n" +
    "C024   17     3357    5.65    0    1    0    0    0    0    1    0    0    1\n" +
    "C025    9     3101   10.10    4    5    0    3    0    0    2    0    0    4\n" +
    "C026    7      140    6.15    0    1   10    0    1    0    1    2    0    0\n" +
    "C027   15     1468   14.10    0    3    0    0    0    0    2    1    2    0\n" +
    "C028   12     1386    9.60    7    1    7    1    3    0    1    1    0    3\n" +
    "C029   15     2971   10.80    2    0    6    2    1    0    1    1    0    0\n" +
    "C030   11     2094    8.85    2    0    0    1    1    0    2    0    1    1\n" +
    "C031   10     2440    7.60    0    1    5    0    0    0    4    0    0    0\n" +
    "C032    9     2942   12.95    0    3    0    3    1    0    1    0    1    0\n" +
    "C033   10     3147    8.90    0    3   12    2    0    0    0    0    1    4\n" +
    "C034   16     4101   11.70    5    0    6    1    0    2    3    1    1    3\n" +
    "C035    9      679    7.60    1    3    8    0    1    0    0    1    2    0\n" +
    "C036    6     3540   14.90    1    6    1    2    1    2    6    1    1    2\n" +
    "C037   15     2826    7.65    4    1    0    2    0    0    0    1    0    0\n" +
    "C038   19     2795    8.80    0    4    4    1    1    0    3    1    0    1\n" +
    "C039    7      971    5.90    2    6   13    1    1    0    1    1    0    0\n" +
    "C040   18     3594    9.75    4    8    7    0    0    0    0    3    0    2\n" +
    "\n" +
    "B001  400  270    980.00\n" +
    "B002  290  290    920.30\n" +
    "B003  340  250    850.90\n" +
    "B004  160  140    150.40\n" +
    "B005  100  280    260.20";

    /**
     * 
     */
    public void getEntityInFile() {
        String[] lines = instanceFile.split("\n");
        String[] generalInfos = lines[0].split(" ");
        String regex = " +|\t";
        
        int i = 2;
        int nbProduct =0;

        ProductTypeDao ProductTypeManager = DaoFactoryJpa.getInstance(JpaProductTypeDao.class);
        OrderDao orderManager = DaoFactoryJpa.getInstance(JpaOrderDao.class);
        BoxTypeDao boxTypeManager = DaoFactoryJpa.getInstance(JpaBoxTypeDao.class);
        
        ArrayList<ProductType> products = new ArrayList();
        System.out.println("coucou");
        
        while(lines[i].length() != 0){
            
            
            lines[i]=lines[i].replaceAll(regex, "\t");
            String[] productInfos = lines[i].split("\t");
            
            ProductType product = new ProductType(productInfos[0],
                    Integer.parseInt(productInfos[1]),
                    Integer.parseInt(productInfos[2]),
                    Integer.parseInt(productInfos[3]),
                    Integer.parseInt(productInfos[4]),
                    Integer.parseInt(productInfos[5]));
            products.add(product);
            ProductTypeManager.create(product);
            System.out.println(product);
            i++;
        }
        //we are in the next paragraph : all the orders
        i++;
        System.out.println("taille products"+products.size());
        while(lines[i].length() != 0){
            lines[i]=lines[i].replaceAll(regex, "\t");
            String[] orderInfos = lines[i].split("\t");
            Order order = new Order(orderInfos[0],
                    Integer.parseInt(orderInfos[1]),
                    Integer.parseInt(orderInfos[2]),
                    Float.parseFloat(orderInfos[3]));
            for(ProductType p: products){
                order.addOrderLine(new OrderLine(Integer.parseInt(orderInfos[4]),p));
            }
          
            orderManager.create(order);
            System.out.println(order);
            nbProduct++;
            i++;
        }
        i++;
        while(lines[i].length() != 0){
            lines[i]=lines[i].replaceAll(regex, "\t");
            String[] boxInfos = lines[i].split("\t");
            BoxType box = new BoxType(boxInfos[0],Integer.parseInt(boxInfos[1]),Integer.parseInt(boxInfos[2]),Float.parseFloat(boxInfos[3]));
            boxTypeManager.create(box);
            System.out.println(box);
            nbProduct++;
            i++;
        }
    }
    
    public static void main(String[] args) {
        Parser test = new Parser();
        test.getEntityInFile();
    }
    
    
}
