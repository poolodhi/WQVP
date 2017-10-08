/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(urlPatterns = {"/overall"})
public class overall extends HttpServlet {

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
            globalinfo g=new globalinfo();
            overallstat o=new overallstat();
            g.getdata();
            System.out.println("*********GOT total********");
            o.getdata(g);
            System.out.println("*********GOT data********");
            Map<Integer,ArrayList<Double>> data=o.getinfodata();
            //System.out.println(data);
            
            String []doc_para={"Unsafe","Safe","Good","Prefect"};
            String []ph_para={"Unsafe (Acidic)","Safe","Unsafe (Basic)"};
            int []doc=new int[4];
            int []ph=new int[3];
            
            Set set=data.entrySet();
            Iterator itr=set.iterator();  
            while(itr.hasNext()){  
                //Converting to Map.Entry so that we can get key and value separately  
                Map.Entry entry=(Map.Entry)itr.next();
                ArrayList current = data.get(entry.getKey());
                //System.out.println(entry.getKey()+" "+(Double)current.get(0));
                if((Double)current.get(0)<5.0){
                    doc[0]++;
                }else if((Double)current.get(0)>=5.0&&(Double)current.get(0)<6.0){
                    doc[1]++;
                }else if((Double)current.get(0)>=7.0&&(Double)current.get(0)<8.0){
                    doc[2]++;
                }else if((Double)current.get(0)>=8.0){
                    doc[3]++;
                }
                if((Double)current.get(1)<6.5){
                    ph[0]++;
                }else if((Double)current.get(1)>=6.5&&(Double)current.get(0)<=8.5){
                    ph[1]++;
                }else if((Double)current.get(1)>8.0){
                    ph[2]++;
                }
            }
            request.setAttribute("ph_para",ph_para);
            request.setAttribute("doc_para",doc_para);
            request.setAttribute("ph",ph);
            request.setAttribute("doc",doc);
            
            request.getRequestDispatcher("overall.jsp").forward(request, response);
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
