package com.qut.spc.weather;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.qut.spc.postcode.PostcodeUtil;

public class DailySunProvider {
	
	private static Map<String,Double> locationToSunHours=new HashMap<String, Double>(){
		{
			put("4000", 7.5);
			put("5000",6.75);
			put("2000",7.33);
			put("3000",5.58);
			put("2900",7.33);
			put("6000",7.92);



		}};

	
	
	public static double getDailySunByPostcode(String location) throws IllegalArgumentException {
		if(PostcodeUtil.validatePostcode(location)){
			String postcode =PostcodeUtil.transformPostcode(location);
			if(locationToSunHours.containsKey(postcode))
				return locationToSunHours.get(location);			
		}
		
		return average(locationToSunHours.values());
	}



	private static double average(Collection<Double> values) {
		double sum=0;
		for(Double d:values)
			sum+=d;
		return sum/values.size();
	}

}
