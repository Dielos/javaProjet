/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.BoxTypeDao;
import DAO.DaoFactoryJpa;
import DAO.JpaBoxTypeDao;
import Model.Box;
import Model.BoxType;
import backend.Parser;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Dielos
 */
@WebServlet(name = "controler", urlPatterns = {"/controler"})
public class Controller extends HttpServlet {

    private BoxTypeDao boxTypeManager;
    private String action = "";

    @Override
    public void init() {
        boxTypeManager = DaoFactoryJpa.getInstance(JpaBoxTypeDao.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //product
        
        // ----- //
        
	action = request.getParameter("action");
       
        if (action != null)
            {
            switch(action) {

                case "timeline":
                    
                    Collection<BoxType> products = boxTypeManager.findAll();
                    
                    request.setAttribute("products", products);
                    request.getRequestDispatcher("/view/timeline.jsp").forward(request, response);
                break;
                
                case "stats":
                    request.getRequestDispatcher("/view/stats.jsp").forward(request, response);
                break;
                
                case "process":
                    request.getRequestDispatcher("/view/process.jsp").forward(request, response);
                break;
                
                case "order":
                    request.getRequestDispatcher("/view/order.jsp").forward(request, response);
                break;
                
                case "hompage":
                    request.getRequestDispatcher("/view/hompage.jsp").forward(request, response);
                break;
                
                default:
                    request.getRequestDispatcher("/view/homepage.jsp").forward(request, response);
                    break;

              }
            }
        else
            request.getRequestDispatcher("/view/homepage.jsp").forward(request, response);
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        String fileName = filePart.getSubmittedFileName();
        InputStream fileContent = filePart.getInputStream();
        String fileText = getStringFromInputStream(fileContent);
        
        Parser test = new Parser();
        test.getEntityInFile(fileText);
        
        request.setAttribute("text", fileText);
        request.getRequestDispatcher("/view/homepage.jsp").forward(request, response);
        
    }
    
    // convert InputStream to String
    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } 
        
        catch (IOException e) {
            e.printStackTrace();
        } 
        
        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

}
