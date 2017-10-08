/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
@WebServlet(urlPatterns = {"/map"})
public class map extends HttpServlet implements datasetinfo {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String river=request.getParameter("rivername");
            String parameter=request.getParameter("parameter");
            eachriver d=new eachriver();
            System.out.println(river+" "+parameter);
            Integer r=Integer.parseInt(river);
            Integer p=Integer.parseInt(parameter);
            d.getdataeachriver(r,p);
            Map<String,ArrayList<Double>> data=d.data;
            String date="";
            String []insights=new String[7];
            for(int i=0;i<7;i++){
            insights[i]=new String("");
            }
            Set set=data.entrySet();
            Iterator itr=set.iterator();  
            while(itr.hasNext()){  
                //Converting to Map.Entry so that we can get key and value separately  
                Map.Entry entry=(Map.Entry)itr.next();
                
                date+="'"+entry.getKey()+"',";
                ArrayList current = data.get(entry.getKey());
                DecimalFormat df = new DecimalFormat("#.##");
                if(p==-1){
                insights[0]+=df.format(current.get(0))+",";
                insights[1]+=df.format(current.get(1))+",";
                insights[2]+=df.format(current.get(2))+",";
                insights[3]+=df.format(current.get(3))+",";
                insights[4]+=df.format(current.get(4))+",";
                insights[5]+=df.format(current.get(5))+",";
                insights[6]+=df.format(current.get(6))+",";
                }else{
                insights[p]+=df.format(current.get(p))+",";
                }
                //System.out.println(entry.getKey()+" "+entry.getValue());  
            }  
            date=date.substring(0,date.length()-1);
            System.out.println(date);
            request.setAttribute("title",rivernames[r]);
            request.setAttribute("pramentername",parameters);
            request.setAttribute("date",date);
            request.setAttribute("insights",insights);
            request.setAttribute("parameter",parameter);
            request.getRequestDispatcher("visulaize1.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
