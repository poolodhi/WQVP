
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
public interface datasetinfo {
    public final int totalrivers=12;
    public final String[] datasetnames= {"831dc300-2e2c-4a90-a87e-3dcd74947025", //Fitzroy
        "6695c762-a441-40c8-87d8-e918bcd785b9", //Calliope
        "db525bfc-424e-4b2b-9f93-d2989370af5f", //Boyne
        "e8d7b7fa-5ae5-408a-b120-a09d4356df8b", //Baffle Creek
        "8d66df0f-2c1f-47ce-b543-2f4f0710fca2",//Kolan
        "8653d368-9201-47fd-bb84-0c643cf6694a",//Burnett
        "32e0fc64-033d-41da-9b3a-a3d209cd1c00",//Burrum
        "988175ce-2e18-4fe0-a870-f899c33b94e7",//Isis
        "d5d5e1cf-04b4-476d-966f-132a20446d78",//Gregory
        "9ce9d222-3793-49d0-b57b-9a501510a53a",//Mary
        "efb1c095-a866-4c5e-9653-161be5823574",//Great Sandy Straits
        "fd80c3f5-952a-476d-87b0-651fd44e8303"//Tin Can Inlet & Snapper Creek
    };
    public final String[] rivernames= {"Fitzroy River",
        "Calliope River and anabranch",
        "Boyne River and South Trees Inlet",
        "Baffle Creek",
        "Kolan River",
        "Burnett River",
        "Burrum River",
        "Isis River",
        "Gregory River",
        "Mary River",
        "Great Sandy Straits & Hervey Bay",
        "Tin Can Inlet & Snapper Creek"
    };
     public final String[] parameters= {
        "pH (Unit)",
        "Dissolved Oxygen concentration (mg/L)",
        "Temperature (deg C)",
        "Total Nitrogen as N (mg/L)",
        "Total Phosphorus as P (mg/L)",
        "Salinity (PSU)",
        "Turbidity (NTU)"
    };
    public final String[] sightcordinate={"",""}; 
}
