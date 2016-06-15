/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.BoxTypeDao;
import DAO.DaoFactoryJpa;
import DAO.InstanceDao;
import DAO.JpaBoxTypeDao;
import DAO.JpaInstanceDao;
import DAO.JpaOrderDao;
import DAO.OrderDao;
import Model.BoxType;
import Model.Instance;
import Model.Order;
import DAO.JpaProductionLineDao;
import DAO.ProductionLineDao;
import Model.BoxType;
import Model.Instance;
import Model.ProductionLine;
import backend.Parser;
import backend.ReverseParser;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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
    
    private String solutionString;
    private BoxTypeDao boxTypeManager;
    private InstanceDao instanceManager;
    private OrderDao orderManager;
    private ProductionLineDao productionLineManager;
    private String action = "";
    private int id;
    static final long serialVersionUID = 1L;
    private static final int BUFSIZE = 4096;
    Collection<BoxType> tabBT;
    private String filePath;
    private String instanceName="FileName1";

    @Override
    public void init() {
        boxTypeManager = DaoFactoryJpa.getInstance(JpaBoxTypeDao.class);
        instanceManager = DaoFactoryJpa.getInstance(JpaInstanceDao.class);
        orderManager = DaoFactoryJpa.getInstance(JpaOrderDao.class);
        productionLineManager = DaoFactoryJpa.getInstance(JpaProductionLineDao.class);
        instanceManager = DaoFactoryJpa.getInstance(JpaInstanceDao.class);
        filePath = getServletContext().getRealPath("") + File.separator + "data.txt";
        
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //product
        
        // ----- //
        
	action = request.getParameter("action");
        if(request.getParameter("id")!=null){
            id = Integer.parseInt(request.getParameter("id"));
        }
        
        Instance instance = instanceManager.getInstanceByName(instanceName);
        Collection<Order> navOrders = orderManager.findAll();
        request.setAttribute("navOrders", navOrders);
        request.setAttribute("instanceName", instanceName);
        if (action != null)
            {
            switch(action) {

                case "timeline":
                    
                    Collection<ProductionLine> lines = instance.getProductionLines();
                   
                    Collection<Instance> instances = instanceManager.findAll();
                    
                    List<String> colors = Arrays.asList("red", "black");
                    request.setAttribute("lines", lines);
                    request.setAttribute("colors", colors);
                    request.setAttribute("instances", instances);
                    //get all orders to fill navbar
                    
                    Collection<BoxType> products = boxTypeManager.findAll();
                    request.setAttribute("products", products);
                    
                    request.getRequestDispatcher("/view/timeline.jsp").forward(request, response);
                break;
                
                case "stats":
                    Collection<BoxType> boxTypes = instance.getBoxTypes();
                    Collection<Order> orders = instance.getOrders();
                    request.setAttribute("boxTypes", boxTypes);
                    
                    request.setAttribute("orders", orders);
                    request.getRequestDispatcher("/view/stats.jsp").forward(request, response);
                break;
                
                case "process":
                    request.getRequestDispatcher("/view/process.jsp").forward(request, response);
                break;
                
                case "order":
                    Order order = instance.getOrderById(Integer.parseInt(id));
                    //instance = instanceManager.getInstanceByName("FileName1");
                    //orders = instance.getOrders();

                    request.setAttribute("order", order);
                    request.getRequestDispatcher("/view/order.jsp").forward(request, response);
                break;
                
                case "homepage":
                    request.getRequestDispatcher("/view/homepage.jsp").forward(request, response);
                break;

                case "download":
                    
                    ReverseParser reverseParser = new ReverseParser();
                    solutionString = reverseParser.getTypeBoxInfos();
                    File file = new File(filePath);
                    int length = 0;
                    
                    try(  PrintWriter out = new PrintWriter(filePath)  ){
                        out.println(solutionString);
                    }
                    ServletOutputStream outStream = response.getOutputStream();
                    ServletContext context  = getServletConfig().getServletContext();
                    String mimetype = context.getMimeType(filePath);

                    // sets response content type
                    if (mimetype == null) {
                        mimetype = "application/octet-stream";
                    }
                    response.setContentType(mimetype);
                    response.setContentLength((int)file.length());
                    String fileName = (new File(filePath)).getName();

                    // sets HTTP header
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

                    byte[] byteBuffer = new byte[BUFSIZE];
                    DataInputStream in = new DataInputStream(new FileInputStream(file));

                    // reads the file's bytes and writes them to the response stream
                    while ((in != null) && ((length = in.read(byteBuffer)) != -1))
                    {
                        outStream.write(byteBuffer,0,length);
                    }

                    in.close();
                    outStream.close();
                    request.getRequestDispatcher("/view/homepage.jsp").forward(request, response);
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
        test.getEntityInFile(fileText, fileName);
        
        request.setAttribute("text", "Upload done");
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
