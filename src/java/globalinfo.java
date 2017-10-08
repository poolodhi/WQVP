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
public class globalinfo implements datasetinfo{
    Long []total;
    
    public globalinfo(){
        total=new Long[12];
        for(int i=0;i<12;i++){
        total[i]=new Long(0);
        }
    }
    
    public Long[] gettotal(){
        return total;
    }
    
    public void getdata(){
        HttpClient httpClient;
        httpClient = new DefaultHttpClient();
        try{
            for(int i=0;i<totalrivers;i++){
            String path="https://data.qld.gov.au/api/action/datastore_search?resource_id="
                    + datasetnames[i]+"&limit=1";
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
            total[i]=(long)result.get("total");
            
            
            
            
            
            
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
    
    
}
