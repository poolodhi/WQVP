
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
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
public class overallstat implements datasetinfo{
    Map<Integer,ArrayList<Double>> data;
    final long recordcount=10;
    public overallstat(){
       data=new LinkedHashMap<Integer,ArrayList<Double>>();
   }
    public Map<Integer,ArrayList<Double>> getinfodata(){
    return data;
    }
    public void getdata(globalinfo g){
        Long[] total=g.gettotal();
        HttpClient httpClient;
        httpClient = new DefaultHttpClient();
        try{
            for(int i=0;i<totalrivers;i++){
                long offset=total[i]-recordcount;
            String path="https://data.qld.gov.au/api/action/datastore_search?resource_id="
                    + datasetnames[i]+"&offset="+offset;
            HttpGet httpGetRequest = new HttpGet(path);
            // Execute HTTP request
            HttpResponse httpResponse = httpClient.execute(httpGetRequest);
            HttpEntity entity = httpResponse.getEntity();
            BufferedReader rd = new BufferedReader(new InputStreamReader(entity.getContent()));
            StringBuffer content = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                content.append(line);
            }
            String r=content.toString();
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(r);
            JSONObject datachunk = (JSONObject)obj;
            JSONObject result = (JSONObject)datachunk.get("result");
            JSONArray records = (JSONArray) result.get("records");
            processdata(records,i);
           
            
            }
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
    public void processdata(JSONArray records,int index){
        System.out.print("****"+index+"****");
        double ph=0.0,doc=0.0,tn=0.0,tp=0.0,sal=0.0,turb=0.0;
        for (int i = 0, size = records.size(); i < size; i++){
            
            Object object = records.get(i);
            JSONObject o=(JSONObject) object;
            if(o.get("pH (Unit)")!=null){
                ph+=Float.parseFloat((String)o.get("pH (Unit)"));
            }
            if(o.get("Total Nitrogen as N (mg/L)")!=null 
                    && !((String)o.get("Total Nitrogen as N (mg/L)")).isEmpty()){
                tn+=Float.parseFloat((String)o.get("Total Nitrogen as N (mg/L)"));
            }
            if(o.get("Dissolved Oxygen concentration (mg/L)")!=null 
                    && !((String)o.get("Dissolved Oxygen concentration (mg/L)")).isEmpty()){
                doc+=Float.parseFloat((String)o.get("Dissolved Oxygen concentration (mg/L)"));
            }
            if(o.get("Total Phosphorus as P (mg/L)")!=null
                    && !((String)o.get("Total Phosphorus as P (mg/L)")).isEmpty()){
                tp+=Float.parseFloat((String)o.get("Total Phosphorus as P (mg/L)"));
            }
            if(o.get("Salinity (PSU)")!=null
                    && !((String)o.get("Salinity (PSU)")).isEmpty()){
                sal+=Float.parseFloat((String)o.get("Salinity (PSU)"));
            }
            if(o.get("Turbidity (NTU)")!=null
                    && !((String)o.get("Turbidity (NTU)")).isEmpty()){
                turb+=Float.parseFloat((String)o.get("Turbidity (NTU)"));
            }
        }
        ArrayList<Double> x=new ArrayList<Double>();
        x.add(ph/(double)recordcount);
        x.add(doc/(double)recordcount);
        //x.add((double)tn/(double)recordcount);
        //x.add((double)tp/(double)recordcount);
        //x.add(sal/(double)recordcount);
        //x.add(turb/(double)recordcount);
        data.put(index,x);
    }
}
