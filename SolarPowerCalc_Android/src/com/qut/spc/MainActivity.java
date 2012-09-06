package com.qut.spc;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.qut.spc.R.id;

import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.os.Bundle;
import android.app.Activity;
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
       Button_BetPrice.setOnClickListener(new View.OnClickListener() {
   	    public void onClick(View v) {
   	        // Do something in response to button click
   	    	OutText.setText(GetPanelPrices(MinPric.getText().toString(),MaxPrice.getText().toString()));
   	    }
   	});
        
    }
    
    private String GetPanelPrices(String priceMin,String priceMax)
    {
    	// the actual send of the information is commented to use later
    	 //return JSONGETRequest("http://solarpowercalc.appspot.com/component?priceMin="+priceMin+"&priceMax="+priceMax);
    	 return JSONGETRequest("http://solarpowercalc.appspot.com/panel");
    }
    
   private String JSONGETRequest(String ServiceRequest)
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
			Output="Output from Server .... \n";
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

	public void onSuccess() {
		// TODO Auto-generated method stub
		
	}
}
