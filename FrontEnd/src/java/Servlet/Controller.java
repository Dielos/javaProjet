/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.BoxTypeDao;
import DAO.DaoFactoryJpa;
import DAO.JpaBoxTypeDao;
import Model.BoxType;
import backend.Parser;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collection;
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


    private BoxTypeDao boxTypeManager;
    private String action = "";
    static final long serialVersionUID = 1L;
    private static final int BUFSIZE = 4096;
    private String filePath;

    @Override
    public void init() {
        boxTypeManager = DaoFactoryJpa.getInstance(JpaBoxTypeDao.class);
        filePath = getServletContext().getRealPath("") + File.separator + "data.txt";
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
                
                case "download":
                    File file = new File(filePath);
                    int length = 0;
                    
                    try(  PrintWriter out = new PrintWriter(filePath)  ){
                        out.println(instanceFile);
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