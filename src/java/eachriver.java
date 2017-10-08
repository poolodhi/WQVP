import java.io.*;
import java.util.*;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
public class eachriver implements datasetinfo{
   public Map<String,ArrayList<Double>> data;  
   public eachriver(){
       data=new LinkedHashMap<String,ArrayList<Double>>();
   }
    public void processdata(JSONArray records){
        
        
         String date="",newdate;
         int count=0;
         double ph=0.0,doc=0.0,temp=0.0,tn=0.0,tp=0.0,sal=0.0,turb=0.0;
        for (int i = 0, size = records.size(); i < size; i++)
        {
            
            Object object = records.get(i);
            JSONObject o=(JSONObject) object;
           
            if(i==0){
                count=1;
                date=(String)o.get("SURVEY_DATE");
                date=date.substring(0, 10);
                if(o.get("pH (Unit)")!=null){
                    ph+=Float.parseFloat((String)o.get("pH (Unit)"));
                }
                if(o.get("Total Nitrogen as N (mg/L)")!=null){
                    tn+=Float.parseFloat((String)o.get("Total Nitrogen as N (mg/L)"));
                }
                if(o.get("Dissolved Oxygen concentration (mg/L)")!=null){
                    doc+=Float.parseFloat((String)o.get("Dissolved Oxygen concentration (mg/L)"));
                }
                if(o.get("Total Phosphorus as P (mg/L)")!=null){
                    tp+=Float.parseFloat((String)o.get("Total Phosphorus as P (mg/L)"));
                }
                if(o.get("Salinity (PSU)")!=null){
                    sal+=Float.parseFloat((String)o.get("Salinity (PSU)"));
                }
                if(o.get("Temperature (deg C)")!=null){
                    temp+=Float.parseFloat((String)o.get("Temperature (deg C)"));
                }
                if(o.get("Turbidity (NTU)")!=null){
                    turb+=Float.parseFloat((String)o.get("Turbidity (NTU)"));
                }
            }else{
                newdate=(String)o.get("SURVEY_DATE");
                newdate=newdate.substring(0, 10);
                if(newdate.equals(date)){
                    count++;
                    if(o.get("pH (Unit)")!=null){
                        ph+=Float.parseFloat((String)o.get("pH (Unit)"));
                    }
                    if(o.get("Total Nitrogen as N (mg/L)")!=null){
                        tn+=Float.parseFloat((String)o.get("Total Nitrogen as N (mg/L)"));
                    }
                    if(o.get("Dissolved Oxygen concentration (mg/L)")!=null){
                        doc+=Float.parseFloat((String)o.get("Dissolved Oxygen concentration (mg/L)"));
                    }
                    if(o.get("Total Phosphorus as P (mg/L)")!=null){
                        tp+=Float.parseFloat((String)o.get("Total Phosphorus as P (mg/L)"));
                    }
                    if(o.get("Salinity (PSU)")!=null){
                        sal+=Float.parseFloat((String)o.get("Salinity (PSU)"));
                    }
                    if(o.get("Temperature (deg C)")!=null){
                        temp+=Float.parseFloat((String)o.get("Temperature (deg C)"));
                    }
                    if(o.get("Turbidity (NTU)")!=null){
                        turb+=Float.parseFloat((String)o.get("Turbidity (NTU)"));
                    }
                }else{
                    //"pH (Unit)","Dissolved Oxygen concentration (mg/L)","Temperature (deg C)",
                    //"Total Nitrogen as N (mg/L)","Total Phosphorus as P (mg/L)","Salinity (PSU)",
                    //"Turbidity (NTU)"
                    ArrayList<Double> x=new ArrayList<Double>();
                    x.add(ph/(double)count);
                    x.add(doc/(double)count);
                    x.add(temp/(double)count);
                    x.add((double)tn);
                    x.add((double)tp);
                    x.add(sal/(double)count);
                    x.add(turb/(double)count);
                    data.put(date,x);
                    
                    count=1;
                    date=newdate;
                    ph=0;doc=0;temp=0;tn=0;tp=0;sal=0;turb=0;
                    if(o.get("pH (Unit)")!=null){
                        ph+=Float.parseFloat((String)o.get("pH (Unit)"));
                    }
                    if(o.get("Total Nitrogen as N (mg/L)")!=null){
                        tn+=Float.parseFloat((String)o.get("Total Nitrogen as N (mg/L)"));
                    }
                    if(o.get("Dissolved Oxygen concentration (mg/L)")!=null){
                        doc+=Float.parseFloat((String)o.get("Dissolved Oxygen concentration (mg/L)"));
                    }
                    if(o.get("Total Phosphorus as P (mg/L)")!=null){
                        tp+=Float.parseFloat((String)o.get("Total Phosphorus as P (mg/L)"));
                    }
                    if(o.get("Salinity (PSU)")!=null){
                        sal+=Float.parseFloat((String)o.get("Salinity (PSU)"));
                    }
                    if(o.get("Temperature (deg C)")!=null){
                        temp+=Float.parseFloat((String)o.get("Temperature (deg C)"));
                    }
                    if(o.get("Turbidity (NTU)")!=null){
                        turb+=Float.parseFloat((String)o.get("Turbidity (NTU)"));
                    }
                }
            }
        }
    }
    
    
    
    
    
    
    public void getdataeachriver(Integer river,Integer parameter){
        HttpClient httpClient;
        httpClient = new DefaultHttpClient();
        try{
            String path="https://data.qld.gov.au/api/action/datastore_search?resource_id="
                    + datasetnames[river];
            Long next,total;
            do{
                
            System.out.println(path);
            HttpGet httpGetRequest = new HttpGet(path);
            // Execute HTTP request
            HttpResponse httpResponse = httpClient.execute(httpGetRequest);
            System.out.println("----------------------------------------");
            System.out.println(httpResponse.getStatusLine());
            System.out.println("----------------------------------------");
            
            // Get hold of the response entity
            HttpEntity entity = httpResponse.getEntity();
            //System.out.println("----------------------------------------");
            //System.out.println(entity.getContent());
            //System.out.println("----------------------------------------");
            BufferedReader rd = new BufferedReader(new InputStreamReader(entity.getContent()));

            StringBuffer content = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                content.append(line);
            }
            
            //System.out.println("----------------------------------------");
            //System.out.println(content);
            //System.out.println("----------------------------------------");
            
            String r=content.toString();
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(r);
            JSONObject datachunk = (JSONObject)obj;
            JSONObject result = (JSONObject)datachunk.get("result");
            total=(long)result.get("total");
            
            JSONArray records = (JSONArray) result.get("records");
            JSONObject links = (JSONObject) result.get("_links");
            String l=(String)links.get("next");
            next=Long.parseLong(l.substring(l.indexOf("=")+1,l.indexOf("&")));
            System.out.println("Total records:"+total+"\nnext chunk:"+next);
            processdata(records);
            path="https://data.qld.gov.au"+l;
            }while(next<200);//change 200 to total
            
            
            
            
            
        }catch(org.json.simple.parser.ParseException e){
            e.printStackTrace();
        }
        catch (ClientProtocolException e) {
            // thrown by httpClient.execute(httpGetRequest)
            e.printStackTrace();
        } catch (IOException e) {
            // thrown by entity.getContent();
            e.printStackTrace();
        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resource
            httpClient.getConnectionManager().shutdown();
        }
    
}
}
