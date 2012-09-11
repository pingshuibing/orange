package com.qut.spc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.qut.spc.R.id;

import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class MainActivity extends Activity  {

	EditText MinPric,MaxPrice,OutText;
	Button Button_BetPrice;
    @Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       Button_BetPrice=(Button) findViewById(R.id.GetPricesButton);
       MinPric=(EditText)findViewById(R.id.TextMinPrice);
       MaxPrice=(EditText)findViewById(R.id.TextMaxPrice);
       OutText=(EditText) findViewById(R.id.outText);
       final Intent intent=new Intent("com.qut.spc");
       intent.setClass(this, PanelView.class);
       Button_BetPrice.setOnClickListener(new View.OnClickListener() {
   	    public void onClick(View v) {
   	        // Do something in response to button click
   	    	// the following code displays the XML in the box
   	    	// I commented it to test starting a new activity when the button is clicked
   	    //	String PanelsData=GetPanelPrices(MinPric.getText().toString(),MaxPrice.getText().toString());
   	    //	OutText.setText(PanelsData);
   	    	
   	    	// this code is to get an XML doc from the XML sstring
   	    	//Document doc=XMLfromString(PanelsData);
   	    	
   	    	// starting the activity to show the panels list
   	    	// I assume we want them clickable.. other wise., they can be displayed as list
   	    	startActivity(intent);
   	    	
   	    }
   	});
        
    }
    
    private String GetPanelPrices(String priceMin,String priceMax)
    {
    	// the actual send of the information is commented to use later
    	 //return JSONGETRequest("http://solarpowercalc.appspot.com/component?priceMin="+priceMin+"&priceMax="+priceMax);
    	 return RestfulRequest("http://solarpowercalc.appspot.com/panel");
    }
    
   private String RestfulRequest(String ServiceRequest)
   {
	   String Output="error";
	   try {
		   
			URL url = new URL(ServiceRequest);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			
			

// this code is to check for the response health later
//			if (conn.getResponseCode() != HttpURLConnection.) {
//				throw new RuntimeException("Failed : HTTP error code : "
//						+ conn.getResponseCode());
//			}
//	 
			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
	 
			String output;
			Output="";
			while ((output = br.readLine()) != null) {
				Output+=output;
			}
	 
			conn.disconnect();
	 
		  } catch (MalformedURLException e) {
	 
			e.printStackTrace();
	 
		  } catch (IOException e) {
	 
			e.printStackTrace();
	 
		  }
	   return Output;
   }
   
   
   private Document XMLfromString(String xml){

		Document doc = null;

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        try {

			DocumentBuilder db = dbf.newDocumentBuilder();

			InputSource is = new InputSource();
		        is.setCharacterStream(new StringReader(xml));
		        doc = db.parse(is); 

			} catch (ParserConfigurationException e) {
				System.out.println("XML parse error: " + e.getMessage());
				return null;
			} catch (SAXException e) {
				System.out.println("Wrong XML file structure: " + e.getMessage());
	            return null;
			} catch (IOException e) {
				System.out.println("I/O exeption: " + e.getMessage());
				return null;
			}

	        return doc;

		}

	public void onSuccess() {
		// TODO Auto-generated method stub
		
	}
}
